package com.infynyty.Task.Graph;

import com.infynyty.Task.Task.TaskNotRunningException;
import org.jetbrains.annotations.NotNull;

public interface TaskEdgeResponse {
    @NotNull Type getResponseType();

    @NotNull TaskNode getTaskNode() throws TaskNotRunningException;


    enum Type {
        NOT_APPLICABLE,
        CHANGE_NODE,
        REPEAT_NODE
    }
}
