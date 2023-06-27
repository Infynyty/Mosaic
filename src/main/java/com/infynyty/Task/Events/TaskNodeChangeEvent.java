package com.infynyty.Task.Events;

import com.infynyty.Task.Task.RunningTask;
import org.jetbrains.annotations.NotNull;

public class TaskNodeChangeEvent<Q extends RunningTask<?>> extends TaskUpdateEvent<Q> {
    public TaskNodeChangeEvent(@NotNull final Q quest) {
        super(quest);
    }
}
