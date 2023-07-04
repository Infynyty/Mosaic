package com.infynyty.Task.Graph;

import java.util.function.Function;

import com.infynyty.Task.Events.TaskActionEvent;
import org.jetbrains.annotations.NotNull;

public class SimpleEdgeResolver implements TaskEdgeResolver {
    private final String name;
    private final Function<TaskActionEvent, TaskEdgeResponse> eventHandler;

    public SimpleEdgeResolver(String name, Function<TaskActionEvent, TaskEdgeResponse> eventHandler) {
        this.name = name;
        this.eventHandler = eventHandler;
    }


    @Override
    public @NotNull Function<TaskActionEvent, TaskEdgeResponse> resolveEdge() {
        return eventHandler;
    }
}
