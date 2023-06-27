package com.infynyty.Task.Events;

import com.infynyty.Task.Task.RunningTask;
import org.jetbrains.annotations.NotNull;

public class TaskFinishEvent<Q extends RunningTask<?>> extends TaskUpdateEvent<Q> {
    public TaskFinishEvent(@NotNull final Q quest) {
        super(quest);
    }
}
