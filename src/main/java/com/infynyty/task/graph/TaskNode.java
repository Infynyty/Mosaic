package com.infynyty.task.graph;

import org.jetbrains.annotations.NotNull;

public interface TaskNode {
    boolean hasEdgeResolver();
    @NotNull TaskEdgeResolver getEdgeResolver() throws NoEdgeResolverException;
    void setEdgeResolver(@NotNull final TaskEdgeResolver edge);
}
