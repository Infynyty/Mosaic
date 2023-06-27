package com.infynyty.Quests.Events;

import com.infynyty.Quests.Player.QuestPartakingEntity;
import com.infynyty.Quests.Quest.RunningQuest;
import org.jetbrains.annotations.NotNull;

/**
 * An event that is called when a new getQuest is started. A new getQuest is started if and only if for a given {@link QuestPartakingEntity player}
 * there exists a {@link RunningQuest running getQuest} and the corresponding {@link com.infynyty.Quests.QuestState getQuest state}
 * switches from {@link com.infynyty.Quests.QuestState#INITIALIZED} to {@link com.infynyty.Quests.QuestState#RUNNING}.
 */
public class QuestStartEvent<Q extends RunningQuest<?>> extends QuestUpdateEvent<Q> {
    public QuestStartEvent(@NotNull final Q quest) {
        super(quest);
    }

}
