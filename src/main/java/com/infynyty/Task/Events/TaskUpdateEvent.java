package com.infynyty.Task.Events;

import com.infynyty.Task.Task.RunningTask;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

/**
 * This class represents an event that occurs through a state change within a {@link com.infynyty.Task.Task.RunningTask task}.
 * As opposed to {@link TaskActionEvent} task update events will only be triggered from within a {@link RunningTask}.
 */
@Getter
public abstract non-sealed class TaskUpdateEvent extends TaskEvent {
    @NotNull
    private final RunningTask<?> task;

    public TaskUpdateEvent(@NotNull final RunningTask<?> task) {
        this.task = task;
    }

    public @NotNull RunningTask<?> getTask() {
        return task;
    }
}
