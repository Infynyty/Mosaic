package com.infynyty.Task.Task;

import java.util.HashMap;
import java.util.Map;

import com.infynyty.Task.Graph.TaskNode;
import com.infynyty.Task.Participant.SimpleParticipant;
import com.infynyty.Task.Participant.TaskParticipant;
import org.jetbrains.annotations.NotNull;

public class SimpleTask extends Task<RunningTask<SimpleParticipant>> {
    private final Map<TaskParticipant, RunningTask<SimpleParticipant>> runningTasks = new HashMap<>();
    /**
     * Creates a new task. Any task is required to have exactly one {@link TaskNode start node}.
     *
     * @param startNode The single entry point of the task.
     */
    public SimpleTask(@NotNull TaskNode startNode) {
        super(startNode);
    }

    @Override
    public void initialize(@NotNull TaskParticipant participant) throws TaskAlreadyRunning {
        final RunningTask<SimpleParticipant> runningTask = new RunningTask<>((SimpleParticipant) participant, this);
        runningTasks.put(participant, runningTask);
        runningTask.start();
    }

    @Override
    protected void remove(@NotNull TaskParticipant participant) throws TaskNotRunning {
        final RunningTask<SimpleParticipant> runningTask = getTaskProgress(participant);
        runningTask.cancel();

    }

    @Override
    public @NotNull RunningTask<SimpleParticipant> getTaskProgress(@NotNull TaskParticipant participant) throws TaskNotRunning {
        if (!runningTasks.containsKey(participant)) throw new TaskNotRunning(participant, this);
        return runningTasks.get(participant);
    }
}
