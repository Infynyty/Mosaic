package com.infynyty.task.graph;

import org.jetbrains.annotations.NotNull;

public class SimpleNode implements TaskNode {
    private final String name;
    private TaskEdgeResolver edgeResolver;

    public SimpleNode(String name) {
        this.name = name;
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
}
