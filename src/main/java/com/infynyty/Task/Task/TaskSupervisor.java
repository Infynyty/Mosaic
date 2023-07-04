package com.infynyty.Task.Task;


import java.util.UUID;

import com.infynyty.Task.Graph.TaskNode;
import com.infynyty.Task.Participant.TaskParticipant;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

/**
 * Each object of this type represents a task. A Task consists of exactly one {@link TaskNode start node} and
 * optionally any number of {@link TaskNode other nodes}, which can be thought of as a directed graph.
 * This also implies that {@link TaskNode nodes} may have self-loops and multiple neighbours.
 * A task can generate a {@link Task running task} for a {@link TaskParticipant participant} by calling
 * {@link #initializeTask(TaskParticipant)}. At any time there may be at most one {@link Task running task}, given
 * a {@link TaskParticipant participant} and a task instance, to ensure that any {@link TaskParticipant participant} can
 * be mapped to a {@link TaskNode task node} and {@link TaskState state} unambiguously.
 */
public interface TaskSupervisor<P extends TaskParticipant, R extends Task<P>> {

    @NotNull TaskNode getStartNode();

    /**
     * Allows to safely check whether a task is running for a given {@link TaskParticipant participant}.
     * @param participant The entity for which the task is checked.
     * @return {@code True} if and only if a {@link Task running task} exists for the given {@link TaskParticipant participant}
     * and has the state {@link TaskState#RUNNING}.
     */
    default boolean isRunning(@NotNull final P participant) {
        try {
            return getTask(participant).getState() == TaskState.RUNNING;
        } catch (TaskNotRunningException taskNotRunningException) {
            return false;
        }
    }

    /**
     * Initializes a new task for a given {@link TaskParticipant participant}.
     * @param participant The entity for which the task is started.
     * @throws TaskAlreadyRunningException Since there may be at most one {@link Task running task} for a given {@link TaskParticipant participant}
     * and {@link TaskSupervisor task instance}, this exception is thrown if a {@link Task running task} already exists
     * for this {@link TaskParticipant participant}.
     */
    void initializeTask(@NotNull final P participant) throws TaskAlreadyRunningException;


    /**
     * Gets the task progress for a given {@link TaskParticipant participant}.
     * @param participant The participant for which the task progress is requested.
     * @return The task progress for the given {@link TaskParticipant participant}.
     * @throws TaskNotRunningException If the task is not currently running. Check with {@link #isRunning(TaskParticipant)} before calling this method.
     */
    @NotNull R getTask(@NotNull final P participant) throws TaskNotRunningException;

    /**
     * Removes the {@link R running task} for a given participant, if present. Prior to removing it, the method
     * should cancel the running task, so that there are no running tasks, that cannot be referenced using {@link #getTask(TaskParticipant)}.
     *
     * @param participant The participant for which the task should be cancelled.
     * @throws TaskNotRunningException If there is no running task for the participant.
     */
    void removeTask(@NotNull final P participant) throws TaskNotRunningException;
}
