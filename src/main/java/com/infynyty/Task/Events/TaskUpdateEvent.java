package com.infynyty.Task.Events;

import com.infynyty.Task.Task.RunningTask;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

/**
 * This class represents an event that occurs through a state change within a {@link com.infynyty.Task.Task.RunningTask task}.
 * As opposed to {@link TaskActionEvent} task update events will only be triggered from within a {@link RunningTask}.
 * @param <Q> The type of {@link RunningTask} this event will be called from.
 */
@Getter
public abstract class TaskUpdateEvent<Q extends RunningTask<?>> extends TaskEvent {
    @NotNull
    private final Q quest;

    public TaskUpdateEvent(@NotNull final Q quest) {
        this.quest = quest;
    }
}
