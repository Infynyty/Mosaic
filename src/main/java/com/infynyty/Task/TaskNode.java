package com.infynyty.Task;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface TaskNode {
    @NotNull List<TaskNode> getNext();
    @NotNull List<TaskNode> getPrevious();
    @NotNull List<TaskEdge> getOutgoingEdges();
    void addOutgoingEdge(@NotNull TaskEdge edge);
    void removeOutgoingEdge(@NotNull TaskEdge edge);
}
