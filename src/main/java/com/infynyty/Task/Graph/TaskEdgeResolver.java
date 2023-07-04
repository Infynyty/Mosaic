package com.infynyty.Task.Graph;

import java.util.function.Function;

import com.infynyty.Task.Events.TaskActionEvent;
import org.jetbrains.annotations.NotNull;

public interface TaskEdgeResolver {
    @NotNull Function<TaskActionEvent, TaskEdgeResponse> resolveEdge();
}
