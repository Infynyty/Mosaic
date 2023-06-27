package com.infynyty.Quests;

import com.infynyty.Quests.Events.QuestEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Function;

public interface QuestEdge {
    QuestEdgeResponse handleEvent(@NotNull final QuestEvent event);
    List<QuestEvent> getAwaitedEvents();
}
