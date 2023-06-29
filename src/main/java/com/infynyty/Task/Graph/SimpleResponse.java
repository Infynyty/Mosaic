package com.infynyty.Task.Graph;

import com.infynyty.Task.Task.TaskNotRunning;
import org.jetbrains.annotations.NotNull;

public class SimpleResponse implements TaskEdgeResponse {
    private final SimpleNode next;
    private final QuestEdgeResponseType type;

    public SimpleResponse(SimpleNode next, QuestEdgeResponseType type) {
        this.next = next;
        this.type = type;
    }


    @Override
    public @NotNull QuestEdgeResponseType getResponseType() {
        return type;
    }

    @Override
    public @NotNull TaskNode getQuestNode() throws TaskNotRunning {
        return next;
    }
}
