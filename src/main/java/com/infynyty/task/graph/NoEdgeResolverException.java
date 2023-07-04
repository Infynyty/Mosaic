package com.infynyty.task.graph;

import lombok.Getter;

@Getter
public class NoEdgeResolverException extends RuntimeException {
    private final TaskNode node;

    public NoEdgeResolverException(TaskNode node) {
        super("An edge resolver was queried, but is not present for the following node: " + node + " . This may indicate" +
            "that the node is a completion node, or that it was incorrectly configured.");
        this.node = node;
    }
}
