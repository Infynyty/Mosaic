package com.infynyty.Task.Graph;

import java.util.function.Function;

import com.infynyty.Task.Events.TaskActionEvent;
import org.jetbrains.annotations.NotNull;

public class SimpleEdge implements TaskEdge {
    private final String name;
    private final Function<TaskActionEvent, TaskEdgeResponse> eventHandler;

    public SimpleEdge(String name, Function<TaskActionEvent, TaskEdgeResponse> eventHandler) {
        this.name = name;
        this.eventHandler = eventHandler;
    }


    @Override
    public @NotNull Function<TaskActionEvent, TaskEdgeResponse> getEventHandler() {
        return eventHandler;
    }
}
