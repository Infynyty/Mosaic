package com.infynyty.Quests.Events;

import com.infynyty.Quests.Quest.RunningQuest;
import org.jetbrains.annotations.NotNull;

public class QuestCompleteEvent<Q extends RunningQuest<?>> extends QuestUpdateEvent<Q> {
    public QuestCompleteEvent(@NotNull Q quest) {
        super(quest);
    }
}
