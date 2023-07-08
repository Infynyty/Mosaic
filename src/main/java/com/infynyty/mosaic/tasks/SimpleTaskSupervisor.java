package com.infynyty.mosaic.tasks;

import com.infynyty.mosaic.graph.TaskNode;
import com.infynyty.mosaic.participant.SimpleParticipant;
import com.infynyty.mosaic.participant.TaskParticipant;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class SimpleTaskSupervisor implements TaskSupervisor<SimpleParticipant, Task<SimpleParticipant, SimpleTaskSupervisor>> {
    private final Map<TaskParticipant, Task<SimpleParticipant, SimpleTaskSupervisor>> runningTasks = new HashMap<>();
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
        final Task<SimpleParticipant, SimpleTaskSupervisor> task = new Task<>(participant, this);
        runningTasks.put(participant, task);
        task.start();
    }

    @Override
    public void removeTask(@NotNull SimpleParticipant participant) throws TaskNotRunningException {
        final Task<SimpleParticipant, SimpleTaskSupervisor> task = getTask(participant);
        task.cancel();

    }

    @Override
    public @NotNull Task<SimpleParticipant, SimpleTaskSupervisor> getTask(@NotNull SimpleParticipant participant) throws TaskNotRunningException {
        if (!runningTasks.containsKey(participant)) throw new TaskNotRunningException(participant, this);
        return runningTasks.get(participant);
    }

    @Override
    public @NotNull TaskNode getStartNode() {
        return startNode;
    }
}
