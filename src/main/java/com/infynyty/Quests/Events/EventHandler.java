package com.infynyty.Quests.Events;

import org.jetbrains.annotations.NotNull;

public interface EventHandler {
    void handleEvent(@NotNull final QuestEvent event);
}
