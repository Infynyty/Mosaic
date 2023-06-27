package com.infynyty.Task.Events;

import com.infynyty.Task.Task.RunningTask;
import org.jetbrains.annotations.NotNull;

public class TaskCompleteEvent<Q extends RunningTask<?>> extends TaskUpdateEvent<Q> {
    public TaskCompleteEvent(@NotNull Q quest) {
        super(quest);
    }
}
