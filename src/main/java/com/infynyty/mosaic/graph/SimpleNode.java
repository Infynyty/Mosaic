package com.infynyty.mosaic.graph;

import com.infynyty.mosaic.events.EventHandler;
import com.infynyty.mosaic.events.TaskEvent;
import com.infynyty.mosaic.events.update.TaskNodeRepeatEvent;
import org.jetbrains.annotations.NotNull;

public class SimpleNode implements TaskNode {
    private final String name;
    private final Runnable onEnter;
    private TaskEdgeResolver edgeResolver;

    public SimpleNode(String name, Runnable onEnter) {
        TaskEvent.addEventListener(this);
        this.name = name;
        this.onEnter = onEnter;
    }

    @Override
    public @NotNull TaskEdgeResolver getEdgeResolver() {
        return edgeResolver;
    }

    @Override
    public boolean isTerminalNode() {
        return edgeResolver == null;
    }

    @Override
    public void setEdgeResolver(@NotNull TaskEdgeResolver edgeResolver) {
        this.edgeResolver = edgeResolver;
    }

    @EventHandler
    public void onTaskEvent(TaskNodeRepeatEvent event) {
        if (event.getNode() == this) {
            onEnter.run();
        }
    }
}
