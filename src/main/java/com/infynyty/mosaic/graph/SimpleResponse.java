package com.infynyty.mosaic.graph;

import org.jetbrains.annotations.NotNull;

public class SimpleResponse implements TaskEdgeResponse {
    private final SimpleNode next;
    private final Type type;

    public SimpleResponse(SimpleNode next, Type type) {
        this.next = next;
        this.type = type;
    }


    @Override
    public @NotNull TaskEdgeResponse.Type getResponseType() {
        return type;
    }

    @Override
    public @NotNull TaskNode getTaskNode() {
        return next;
    }
}
