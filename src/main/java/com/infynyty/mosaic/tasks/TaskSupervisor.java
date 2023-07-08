package com.infynyty.mosaic.tasks;


import com.infynyty.mosaic.graph.TaskNode;
import com.infynyty.mosaic.participant.TaskParticipant;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Each object of this type represents a task supervisor. A supervisor is responsible for one task consisting
 * of exactly one {@link TaskNode start node} and optionally any number of {@link TaskNode other nodes}, which can be
 * thought of as a directed graph. This also implies that {@link TaskNode nodes} may have self-loops and multiple neighbours.
 * A task supervisor can generate a {@link Task task instance} for a {@link P participant} by calling
 * {@link #initializeTask(P)}. At any time there may be at most one {@link Task task instance}, given
 * a {@link P participant} and a task instance, to ensure that any {@link P participant} can
 * be mapped to a {@link TaskNode task node} and {@link TaskState state} unambiguously.
 *
 * @param <P> The type of participant this supervisor should handle.
 * @param <T> The type of task instances this supervisor manages.
 */
public interface TaskSupervisor<P extends TaskParticipant, T extends Task<P, ?>> {

    /**
     * @return The single starting node of the managed task.
     */
    @NotNull TaskNode getStartNode();

    /**
     * Allows to safely check whether a task is running for a given {@link P participant}.
     * @param participant The entity for which the task is checked.
     * @return {@code True} if and only if a {@link Task running task} exists for the given {@link P participant}
     * and has the state {@link TaskState#RUNNING}.
     */
    default boolean isRunning(@NotNull final P participant) {
        final Task<P, ?> task = this.getTask(participant);
        return task != null && task.getState() == TaskState.RUNNING;
    }

    /**
     * Initializes a new task for a given {@link P participant}.
     * @param participant The entity for which the task is started.
     * @throws TaskAlreadyRunningException Since there may be at most one {@link Task running task} for a given {@link TaskParticipant participant}
     * and {@link TaskSupervisor task instance}, this exception is thrown if a {@link Task running task} already exists
     * for this {@link P participant}.
     */
    void initializeTask(@NotNull final P participant) throws TaskAlreadyRunningException;


    /**
     * Gets the task progress for a given {@link TaskParticipant participant}.
     * @param participant The participant for which the task progress is requested.
     * @return The task progress for the given {@link TaskParticipant participant} or {@code null}, if there is none.
     */
    @Nullable T getTask(@NotNull final P participant);

    /**
     * Removes the {@link T running task} for a given participant, if present. Prior to removing it, the method
     * should cancel the running task, so that there are no running tasks, that cannot be referenced using {@link #getTask(TaskParticipant)}.
     *
     * @param participant The participant for which the task should be cancelled.
     * @throws TaskNotRunningException If there is no running task for the participant.
     */
    void removeTask(@NotNull final P participant) throws TaskNotRunningException;
}
