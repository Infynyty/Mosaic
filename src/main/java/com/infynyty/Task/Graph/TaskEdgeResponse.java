package com.infynyty.Task.Graph;

import com.infynyty.Task.Task.TaskNotRunning;
import org.jetbrains.annotations.NotNull;

public interface TaskEdgeResponse {
    @NotNull Type getResponseType();

    @NotNull TaskNode getQuestNode() throws TaskNotRunning;


    enum Type {
        NOT_APPLICABLE,
        CHANGE_NODE,
        REPEAT_NODE
    }
}
