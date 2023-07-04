package com.infynyty.task.graph;

import java.util.function.Function;

import com.infynyty.task.events.TaskActionEvent;
import org.jetbrains.annotations.NotNull;

public interface TaskEdgeResolver {
    @NotNull Function<TaskActionEvent, TaskEdgeResponse> resolveEdge();
}
