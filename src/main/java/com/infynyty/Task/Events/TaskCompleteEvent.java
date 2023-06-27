package com.infynyty.Task.Events;

import com.infynyty.Task.Task.RunningTask;
import com.infynyty.Task.Graph.TaskNode;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@Getter
public class TaskCompleteEvent<Q extends RunningTask<?>> extends TaskUpdateEvent<Q> {
    @NotNull
    private final TaskNode finishNode;
    public TaskCompleteEvent(@NotNull final Q quest, @NotNull TaskNode finishNode) {
        super(quest);
        this.finishNode = finishNode;
    }
}
