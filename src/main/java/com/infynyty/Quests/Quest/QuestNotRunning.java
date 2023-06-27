package com.infynyty.Quests.Quest;

import com.infynyty.Quests.Player.QuestPartakingEntity;

public class QuestNotRunning extends RuntimeException {
    private final QuestPartakingEntity player;
    private final Quest quest;

    public QuestNotRunning(QuestPartakingEntity player, Quest quest) {
        super("Quest " + quest + " is not running for " + player);
        this.player = player;
        this.quest = quest;
    }
}
