package com.infynyty.Task.Task;

import com.infynyty.Task.Events.TaskEvent;
import com.infynyty.Task.Graph.SimpleNode;
import com.infynyty.Task.Graph.TaskNode;
import com.infynyty.Task.Participant.SimpleParticipant;
import org.jetbrains.annotations.NotNull;

public class SimpleRunningTask extends RunningTask<SimpleParticipant> {
    private SimpleNode currentNode;
    /**
     * Initializes a new running task. This sets its {@link TaskState state} to {@link TaskState#INITIALIZED}. To start
     * it use {@link #start()}.
     *
     * @param participant The participant for this running task.
     * @param task        The task that created this running task.
     */
    protected SimpleRunningTask(@NotNull SimpleParticipant participant, @NotNull Task<SimpleRunningTask> task) {
        super(participant, task);
    }

    @Override
    protected @NotNull TaskNode getCurrentNode() {
        return null;
    }

    @Override
    protected void cancel() {

    }
}
