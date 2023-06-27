package com.infynyty.Quests;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface QuestNode {
    @NotNull List<QuestNode> getNext();
    @NotNull List<QuestNode> getPrevious();
    @NotNull List<QuestEdge> getOutgoingEdges();
    void addOutgoingEdge(@NotNull QuestEdge edge);
    void removeOutgoingEdge(@NotNull QuestEdge edge);
}
