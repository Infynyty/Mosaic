package com.infynyty.Task.Graph;

import com.infynyty.Task.Events.TaskEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SimpleEdge implements TaskEdge {
    private final String name;
    private final SimpleNode next;

    public SimpleEdge(SimpleNode next, String name) {
        this.name = name;
        this.next = next;
    }


    @Override
    public @NotNull TaskEdgeResponse handleEvent(@NotNull TaskEvent event) {
        System.out.println("Edge name: " + name);
        return new SimpleResponse(next, TaskEdgeResponse.QuestEdgeResponseType.CHANGE_NODE);
    }

    @Override
    public @NotNull List<TaskEvent> getAwaitedEvents() {
        return null;
    }
}
