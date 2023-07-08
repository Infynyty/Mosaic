package com.infynyty.mosaic.events.update;

import com.infynyty.mosaic.events.TaskUpdateEvent;
import com.infynyty.mosaic.graph.TaskNode;
import com.infynyty.mosaic.tasks.Task;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@Getter
public class TaskNodeRepeatEvent extends TaskUpdateEvent {
    @NotNull
    private final TaskNode node;

    public TaskNodeRepeatEvent(@NotNull final Task<?, ?> task, @NotNull final TaskNode node) {
        super(task);
        this.node = node;
    }
}
