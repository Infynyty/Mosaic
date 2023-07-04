package com.infynyty.Task.Graph;

import java.util.List;

import org.jetbrains.annotations.NotNull;

public class SimpleNode implements TaskNode {
    private final String name;
    private TaskEdgeResolver edgeResolver;

    public SimpleNode(String name) {
        this.name = name;
    }


    @Override
    public @NotNull List<TaskNode> getNext() {
        return null;
    }

    @Override
    public @NotNull List<TaskNode> getPrevious() {
        return null;
    }

    @Override
    public @NotNull TaskEdgeResolver getEdgeResolver() {
        return edgeResolver;
    }

    @Override
    public boolean hasEdgeResolver() {
        return edgeResolver == null;
    }

    @Override
    public void setEdgeResolver(@NotNull TaskEdgeResolver edgeResolver) {
        this.edgeResolver = edgeResolver;
    }
}
