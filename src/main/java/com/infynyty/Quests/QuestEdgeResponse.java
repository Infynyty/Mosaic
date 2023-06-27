package com.infynyty.Quests;

import com.infynyty.Quests.Quest.QuestNotRunning;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface QuestEdgeResponse {
    @NotNull QuestEdgeResponseType getResponseType();

    @NotNull QuestNode getQuestNode() throws QuestNotRunning;


    enum QuestEdgeResponseType {
        NOT_APPLICABLE,
        CHANGE_NODE,
        REPEAT_NODE
    }
}
