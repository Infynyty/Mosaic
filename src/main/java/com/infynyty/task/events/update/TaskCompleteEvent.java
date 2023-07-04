package com.infynyty.task.events.update;

import com.infynyty.task.events.TaskUpdateEvent;
import com.infynyty.task.graph.TaskNode;
import com.infynyty.task.tasks.Task;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@Getter
public class TaskCompleteEvent extends TaskUpdateEvent {
    @NotNull
    private final TaskNode finishNode;

    public TaskCompleteEvent(@NotNull Task<?> task, @NotNull TaskNode finishNode) {
        super(task);
        this.finishNode = finishNode;
    }
}
