package com.infynyty.mosaic.events.update;

import com.infynyty.mosaic.events.TaskUpdateEvent;
import com.infynyty.mosaic.graph.TaskNode;
import com.infynyty.mosaic.tasks.Task;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@Getter
public class TaskCompleteEvent extends TaskUpdateEvent {
    @NotNull
    private final TaskNode finishNode;

    public TaskCompleteEvent(@NotNull final Task<?, ?> task, @NotNull final TaskNode finishNode) {
        super(task);
        this.finishNode = finishNode;
    }
}
