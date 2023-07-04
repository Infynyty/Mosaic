package com.infynyty.task.events.update;

import com.infynyty.task.events.TaskUpdateEvent;
import com.infynyty.task.graph.TaskNode;
import com.infynyty.task.tasks.Task;
import org.jetbrains.annotations.NotNull;

public class TaskNodeRepeatEvent extends TaskUpdateEvent {
    @NotNull
    private final TaskNode node;

    public TaskNodeRepeatEvent(@NotNull Task<?> task, @NotNull TaskNode node) {
        super(task);
        this.node = node;
    }
}
