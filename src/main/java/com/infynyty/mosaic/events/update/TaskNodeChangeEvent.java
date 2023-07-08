package com.infynyty.mosaic.events.update;

import com.infynyty.mosaic.events.TaskUpdateEvent;
import com.infynyty.mosaic.graph.TaskNode;
import com.infynyty.mosaic.tasks.Task;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@Getter
public class TaskNodeChangeEvent extends TaskUpdateEvent {
    @NotNull
    private final TaskNode previous;
    @NotNull
    private final TaskNode next;
    public TaskNodeChangeEvent(@NotNull final Task<?, ?> task, @NotNull final TaskNode previous, @NotNull final TaskNode next) {
        super(task);
        this.previous = previous;
        this.next = next;
    }
}
