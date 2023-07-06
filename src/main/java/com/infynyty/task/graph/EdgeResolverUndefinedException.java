package com.infynyty.task.graph;

import lombok.Getter;

@Getter
public class EdgeResolverUndefinedException extends RuntimeException {
    private final TaskNode node;

    public EdgeResolverUndefinedException(TaskNode node) {
        super("An edge resolver was queried, but is not present for the following node: " + node + " . This may indicate" +
            "that the node is a completion node, or that it was incorrectly configured.");
        this.node = node;
    }
}
