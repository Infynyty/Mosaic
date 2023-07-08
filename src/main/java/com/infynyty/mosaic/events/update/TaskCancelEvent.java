package com.infynyty.mosaic.events.update;

import com.infynyty.mosaic.events.TaskUpdateEvent;
import com.infynyty.mosaic.tasks.Task;
import org.jetbrains.annotations.NotNull;

public class TaskCancelEvent extends TaskUpdateEvent {
    public TaskCancelEvent(@NotNull final Task<?, ?> task) {
        super(task);
    }
}
