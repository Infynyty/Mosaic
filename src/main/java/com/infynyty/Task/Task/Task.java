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
 * A task can generate a {@link RunningTask running task} for a {@link TaskParticipant participant} by calling
 * {@link #initialize(TaskParticipant)}. At any time there may be at most one {@link RunningTask running task}, given
 * a {@link TaskParticipant participant} and a task instance, to ensure that any {@link TaskParticipant participant} can
 * be mapped to a {@link TaskNode task node} and {@link TaskState state} unambiguously.
 */
@Getter
@EqualsAndHashCode(of = "id")
public abstract class Task<R extends RunningTask<?>> {

    private final TaskNode startNode;

    private final UUID id = UUID.randomUUID();

    /**
     * Creates a new task. Any task is required to have exactly one {@link TaskNode start node}.
     *
     * @param startNode The single entry point of the task.
     */
    public Task(@NotNull final TaskNode startNode) {
        this.startNode = startNode;
    }


    /**
     * Allows to safely check whether a task is running for a given {@link TaskParticipant participant}.
     * @param participant The entity for which the task is checked.
     * @return {@code True} if and only if a {@link RunningTask running task} exists for the given {@link TaskParticipant participant}
     * and has the state {@link TaskState#RUNNING}.
     */
    public final boolean isRunning(@NotNull final TaskParticipant participant) {
        try {
            return getTaskProgress(participant).getState() == TaskState.RUNNING;
        } catch (TaskNotRunning taskNotRunning) {
            return false;
        }
    }

    /**
     * Initializes a new task for a given {@link TaskParticipant participant}.
     * @param participant The entity for which the task is started.
     * @throws TaskAlreadyRunning Since there may be at most one {@link RunningTask running task} for a given {@link TaskParticipant participant}
     * and {@link Task task instance}, this exception is thrown if a {@link RunningTask running task} already exists
     * for this {@link TaskParticipant participant}.
     */
    public abstract void initialize(@NotNull final TaskParticipant participant) throws TaskAlreadyRunning;


    /**
     * Gets the task progress for a given {@link TaskParticipant participant}.
     * @param participant The participant for which the task progress is requested.
     * @return The task progress for the given {@link TaskParticipant participant}.
     * @throws TaskNotRunning If the task is not currently running. Check with {@link #isRunning(TaskParticipant)} before calling this method.
     */
    public abstract @NotNull R getTaskProgress(@NotNull final TaskParticipant participant) throws TaskNotRunning;

    /**
     * Removes the {@link R running task} for a given participant, if present. Prior to removing it, the method
     * should cancel the running task, so that there are no running tasks, that cannot be referenced using {@link #getTaskProgress(TaskParticipant)}.
     *
     * @param participant The participant for which the task should be cancelled.
     * @throws TaskNotRunning If there is no running task for the participant.
     */
    protected abstract void remove(@NotNull final TaskParticipant participant) throws TaskNotRunning;
}
