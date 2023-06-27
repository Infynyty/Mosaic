package com.infynyty.Quests.Quest;

import com.infynyty.Quests.Player.BukkitQuestPlayer;
import com.infynyty.Quests.QuestNode;
import org.jetbrains.annotations.NotNull;

public class MinecraftRunningQuest extends RunningQuest<BukkitQuestPlayer> {
    public MinecraftRunningQuest(@NotNull BukkitQuestPlayer player, @NotNull Quest<MinecraftRunningQuest> quest) {
        super(player, quest);
    }

    @Override
    public QuestNode getNode() {
        return null;
    }

    @Override
    protected void cancel() {

    }
}
