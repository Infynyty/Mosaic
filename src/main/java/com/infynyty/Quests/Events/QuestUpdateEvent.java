package com.infynyty.Quests.Events;

import com.infynyty.Quests.Quest.RunningQuest;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@Getter
public abstract class QuestUpdateEvent<Q extends RunningQuest<?>> extends QuestEvent {
    @NotNull
    private final Q quest;

    public QuestUpdateEvent(@NotNull final Q quest) {
        this.quest = quest;
    }
}
