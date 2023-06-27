package com.infynyty.Quests.Events;

import com.infynyty.Quests.Quest.RunningQuest;
import org.jetbrains.annotations.NotNull;

public class QuestNodeChangeEvent<Q extends RunningQuest<?>> extends QuestUpdateEvent<Q>{
    public QuestNodeChangeEvent(@NotNull final Q quest) {
        super(quest);
    }
}
