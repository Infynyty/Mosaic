package com.infynyty.Task.Events;

import com.infynyty.Task.Task.RunningTask;
import org.jetbrains.annotations.NotNull;

public class TaskCancelEvent<Q extends RunningTask<?>> extends TaskUpdateEvent<Q> {
    public TaskCancelEvent(@NotNull Q quest) {
        super(quest);
    }
}
