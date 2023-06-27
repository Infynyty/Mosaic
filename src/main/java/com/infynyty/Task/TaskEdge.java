package com.infynyty.Task;

import com.infynyty.Task.Events.TaskEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface TaskEdge {
    TaskEdgeResponse handleEvent(@NotNull final TaskEvent event);
    List<TaskEvent> getAwaitedEvents();
}
