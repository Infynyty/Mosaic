package com.infynyty.Task.Graph;

import com.infynyty.Task.Events.TaskEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface TaskEdge {
    @NotNull TaskEdgeResponse handleEvent(@NotNull final TaskEvent event);
    @NotNull List<TaskEvent> getAwaitedEvents();
}
