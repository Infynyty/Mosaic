package com.infynyty.Task;

import com.infynyty.Task.Task.TaskNotRunning;
import org.jetbrains.annotations.NotNull;

public interface TaskEdgeResponse {
    @NotNull QuestEdgeResponseType getResponseType();

    @NotNull TaskNode getQuestNode() throws TaskNotRunning;


    enum QuestEdgeResponseType {
        NOT_APPLICABLE,
        CHANGE_NODE,
        REPEAT_NODE
    }
}
