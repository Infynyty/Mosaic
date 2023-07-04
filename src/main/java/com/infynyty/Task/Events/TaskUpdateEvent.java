package com.infynyty.Task.Events;

import com.infynyty.Task.Task.Task;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

/**
 * This class represents an event that occurs through a state change within a {@link Task task}.
 * As opposed to {@link TaskActionEvent} task update events will only be triggered from within a {@link Task}.
 */
@Getter
public abstract non-sealed class TaskUpdateEvent extends TaskEvent {
    @NotNull
    private final Task<?> task;

    public TaskUpdateEvent(@NotNull final Task<?> task) {
        this.task = task;
    }

    public @NotNull Task<?> getTask() {
        return task;
    }
}
