package com.infynyty.Task.Events;

import com.infynyty.Task.Task.RunningTask;
import com.infynyty.Task.Graph.TaskNode;
import org.jetbrains.annotations.NotNull;

public class TaskNodeChangeEvent<Q extends RunningTask<?>> extends TaskUpdateEvent<Q> {
    @NotNull
    private final TaskNode previous;
    @NotNull
    private final TaskNode next;
    public TaskNodeChangeEvent(@NotNull final Q quest, @NotNull TaskNode previous, @NotNull TaskNode next) {
        super(quest);
        this.previous = previous;
        this.next = next;
    }
}
