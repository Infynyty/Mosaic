package com.infynyty.Task.Graph;

import java.util.List;

import org.jetbrains.annotations.NotNull;

public interface TaskNode {
    @NotNull List<TaskNode> getNext();
    @NotNull List<TaskNode> getPrevious();
    @NotNull TaskEdgeResolver getEdgeResolver() throws NoEdgeResolverException;
    boolean hasEdgeResolver();
    void setEdgeResolver(@NotNull final TaskEdgeResolver edge);
}
