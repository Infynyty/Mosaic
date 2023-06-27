package com.infynyty.Task.Graph;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface TaskNode {
    @NotNull List<TaskNode> getNext();
    @NotNull List<TaskNode> getPrevious();
    @NotNull List<TaskEdge> getOutgoingEdges();
    void addOutgoingEdge(@NotNull final TaskEdge edge);
    void removeOutgoingEdge(@NotNull final TaskEdge edge);
}
