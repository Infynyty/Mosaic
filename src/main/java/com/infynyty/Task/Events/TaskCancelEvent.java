package com.infynyty.Task.Events;

import com.infynyty.Task.Task.Task;
import org.jetbrains.annotations.NotNull;

public class TaskCancelEvent extends TaskUpdateEvent {
    public TaskCancelEvent(@NotNull Task<?> task) {
        super(task);
    }
}
