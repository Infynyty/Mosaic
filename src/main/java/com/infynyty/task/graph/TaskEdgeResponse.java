package com.infynyty.task.graph;

import com.infynyty.task.tasks.TaskNotRunningException;
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
