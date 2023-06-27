package com.infynyty.Quests.Quest;

import com.infynyty.Quests.Player.QuestPartakingEntity;
import lombok.Getter;

@Getter
public class QuestAlreadyRunning extends RuntimeException {
    private final QuestPartakingEntity player;
    private final Quest quest;

    public QuestAlreadyRunning(QuestPartakingEntity player, Quest quest) {
        super("Quest " + quest + " is already running for " + player);
        this.player = player;
        this.quest = quest;
    }
}
