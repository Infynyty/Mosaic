package com.infynyty.task.tasks;

import java.util.HashMap;
import java.util.Map;

import com.infynyty.task.graph.TaskNode;
import com.infynyty.task.participant.SimpleParticipant;
import com.infynyty.task.participant.TaskParticipant;
import org.jetbrains.annotations.NotNull;

public class SimpleTaskSupervisor implements TaskSupervisor<SimpleParticipant, Task<SimpleParticipant>> {
    private final Map<TaskParticipant, Task<SimpleParticipant>> runningTasks = new HashMap<>();
    private final TaskNode startNode;
    /**
     * Creates a new task. Any task is required to have exactly one {@link TaskNode start node}.
     *
     * @param startNode The single entry point of the task.
     */
    public SimpleTaskSupervisor(@NotNull TaskNode startNode) {
        this.startNode = startNode;
    }

    @Override
    public void initializeTask(@NotNull SimpleParticipant participant) throws TaskAlreadyRunningException {
        final Task<SimpleParticipant> task = new Task<>(participant, this);
        runningTasks.put(participant, task);
        task.start();
    }

    @Override
    public void removeTask(@NotNull SimpleParticipant participant) throws TaskNotRunningException {
        final Task<SimpleParticipant> task = getTask(participant);
        task.cancel();

    }

    @Override
    public @NotNull Task<SimpleParticipant> getTask(@NotNull SimpleParticipant participant) throws TaskNotRunningException {
        if (!runningTasks.containsKey(participant)) throw new TaskNotRunningException(participant, this);
        return runningTasks.get(participant);
    }

    @Override
    public @NotNull TaskNode getStartNode() {
        return startNode;
    }
}
