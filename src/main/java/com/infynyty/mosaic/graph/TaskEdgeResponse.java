package com.infynyty.mosaic.graph;

import org.jetbrains.annotations.NotNull;

/**
 * The response returned by a {@link TaskEdgeResolver}. Indicates the {@link Type} of response and the next {@link TaskNode}.
 */
public interface TaskEdgeResponse {

    /**
     * @return The type of response, see {@link Type} for explanations.
     */
    @NotNull Type getResponseType();

    /**
     * @return The next task node, which might be identical to the current one.
     */
    @NotNull TaskNode getTaskNode();

    /**
     * Indicates what kind of response was made. This may imply information about the returned
     * {@link #getResponseType() node}.
     */
    enum Type {
        /**
         * The event that occurred was not relevant to the {@link TaskEdgeResolver} that responded.
         * {@link #getTaskNode()} will return the same node that was active before the event occurred.
         */
        NOT_APPLICABLE,
        /**
         * The event that occurred was relevant and should lead to a change in state. {@link #getTaskNode() The new node}
         * is guaranteed to be different from the old one.
         */
        CHANGE_NODE,
        /**
         * The event that occurred was relevant, but the current state should be repeated.
         * {@link #getTaskNode()} will return the same node that was active before the event occurred.
         */
        REPEAT_NODE
    }
}
