package com.infynyty.Task.Events;

import com.infynyty.Task.Task.RunningTask;
import org.jetbrains.annotations.NotNull;

public class TaskCancelEvent extends TaskUpdateEvent {
    public TaskCancelEvent(@NotNull RunningTask<?> task) {
        super(task);
    }
}
