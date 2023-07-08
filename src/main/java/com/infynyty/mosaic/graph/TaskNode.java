package com.infynyty.mosaic.graph;

import org.jetbrains.annotations.NotNull;

/**
 * Represents a node in a task graph that should provide information about the current state. Furthermore, whenever an
 * {@link com.infynyty.mosaic.events.TaskActionEvent event} occurs, the edge resolver of the class should respond accordingly.
 * A node will be considered a terminal node, if {@link #isTerminalNode()} returns {@code false}, resulting in a
 * {@link com.infynyty.mosaic.tasks.TaskState#COMPLETED completed task}.
 */
public interface TaskNode {
    /**
     * Returns whether a node is terminal, i.e. changing state to this node triggers the state of a task to switch to
     * {@link com.infynyty.mosaic.tasks.TaskState#COMPLETED}.
     *
     * @return {@code True}, if and only if the node is a terminal node.
     */
    boolean isTerminalNode();

    /**
     * @return Returns the edge resolver for this node, if it is present.
     * @throws EdgeResolverUndefinedException If and only if {@link #isTerminalNode()} returns {@code true}.
     */
    @NotNull TaskEdgeResolver getEdgeResolver() throws EdgeResolverUndefinedException;

    /**
     * @param edge The edge resolver for this node.
     */
    void setEdgeResolver(@NotNull final TaskEdgeResolver edge);
}
