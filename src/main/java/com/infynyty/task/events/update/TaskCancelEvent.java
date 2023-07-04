package com.infynyty.task.events.update;

import com.infynyty.task.events.TaskUpdateEvent;
import com.infynyty.task.tasks.Task;
import org.jetbrains.annotations.NotNull;

public class TaskCancelEvent extends TaskUpdateEvent {
    public TaskCancelEvent(@NotNull Task<?> task) {
        super(task);
    }
}
