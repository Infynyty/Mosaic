package com.infynyty.task.events.update;

import com.infynyty.task.events.TaskUpdateEvent;
import com.infynyty.task.graph.TaskNode;
import com.infynyty.task.tasks.Task;
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
