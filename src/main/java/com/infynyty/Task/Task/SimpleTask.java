package com.infynyty.Task.Task;

import com.infynyty.Task.Graph.TaskNode;
import com.infynyty.Task.Participant.SimpleParticipant;
import com.infynyty.Task.Participant.TaskParticipant;
import org.jetbrains.annotations.NotNull;

public class SimpleTask extends Task<SimpleRunningTask> {
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
        final SimpleRunningTask runningTask = new SimpleRunningTask((SimpleParticipant) participant, this);
        runningTask.start();
    }

    @Override
    protected void cancel(@NotNull TaskParticipant participant) throws TaskNotRunning {

    }

    @Override
    public SimpleRunningTask getTaskProgress(@NotNull TaskParticipant participant) throws TaskNotRunning {
        return null;
    }
}
