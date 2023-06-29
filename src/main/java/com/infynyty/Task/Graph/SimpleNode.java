package com.infynyty.Task.Graph;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SimpleNode implements TaskNode {
    private final String name;
    private final List<TaskEdge> outgoingEdges = new ArrayList<>();

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
    public @NotNull List<TaskEdge> getOutgoingEdges() {
        return outgoingEdges;
    }

    @Override
    public void addOutgoingEdge(@NotNull TaskEdge edge) {
        outgoingEdges.add(edge);
    }

    @Override
    public void removeOutgoingEdge(@NotNull TaskEdge edge) {

    }
}
