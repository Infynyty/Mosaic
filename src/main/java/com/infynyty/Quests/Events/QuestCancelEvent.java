package com.infynyty.Quests.Events;

import com.infynyty.Quests.Quest.RunningQuest;
import org.jetbrains.annotations.NotNull;

public class QuestCancelEvent<Q extends RunningQuest<?>> extends QuestUpdateEvent<Q> {
    public QuestCancelEvent(@NotNull Q quest) {
        super(quest);
    }
}
