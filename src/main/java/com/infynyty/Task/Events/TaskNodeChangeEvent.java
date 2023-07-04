package com.infynyty.Task.Events;

import com.infynyty.Task.Graph.TaskNode;
import com.infynyty.Task.Task.Task;
import org.jetbrains.annotations.NotNull;

public class TaskNodeChangeEvent extends TaskUpdateEvent {
    @NotNull
    private final TaskNode previous;
    @NotNull
    private final TaskNode next;
    public TaskNodeChangeEvent(@NotNull final Task<?> task, @NotNull TaskNode previous, @NotNull TaskNode next) {
        super(task);
        this.previous = previous;
        this.next = next;
    }
}
