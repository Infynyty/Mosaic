package com.infynyty.Task.Events;

import com.infynyty.Task.Task.RunningTask;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@Getter
public abstract class TaskUpdateEvent<Q extends RunningTask<?>> extends TaskEvent {
    @NotNull
    private final Q quest;

    public TaskUpdateEvent(@NotNull final Q quest) {
        this.quest = quest;
    }
}
