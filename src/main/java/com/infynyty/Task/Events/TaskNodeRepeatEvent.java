package com.infynyty.Task.Events;

import com.infynyty.Task.Graph.TaskNode;
import com.infynyty.Task.Task.Task;
import org.jetbrains.annotations.NotNull;

public class TaskNodeRepeatEvent extends TaskUpdateEvent{
    @NotNull
    private final TaskNode node;

    public TaskNodeRepeatEvent(@NotNull Task<?> task, @NotNull TaskNode node) {
        super(task);
        this.node = node;
    }
}
