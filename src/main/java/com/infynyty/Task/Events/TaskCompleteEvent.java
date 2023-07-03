package com.infynyty.Task.Events;

import com.infynyty.Task.Graph.TaskNode;
import com.infynyty.Task.Task.RunningTask;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@Getter
public class TaskCompleteEvent extends TaskUpdateEvent {
    @NotNull
    private final TaskNode finishNode;

    public TaskCompleteEvent(@NotNull RunningTask<?> task, @NotNull TaskNode finishNode) {
        super(task);
        this.finishNode = finishNode;
    }
}
