package com.infynyty.Quests.Events;

import com.infynyty.Quests.Quest.RunningQuest;
import org.jetbrains.annotations.NotNull;

public class QuestFinishEvent<Q extends RunningQuest<?>> extends QuestUpdateEvent<Q> {
    public QuestFinishEvent(@NotNull final Q quest) {
        super(quest);
    }
}
