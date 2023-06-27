package com.infynyty.Quests.Quest;

import com.infynyty.Quests.Player.BukkitQuestPlayer;
import com.infynyty.Quests.Player.QuestPartakingEntity;
import com.infynyty.Quests.QuestStartNode;
import org.jetbrains.annotations.NotNull;



public class MinecraftQuest extends Quest<RunningQuest<BukkitQuestPlayer>> {

    /**
     * Creates a new getQuest. Any getQuest is required to have exactly one {@link QuestStartNode start node}.
     *
     * @param startNode  The single entry point of the getQuest.
     */
    public MinecraftQuest(@NotNull QuestStartNode startNode) {
        super(startNode);
    }

    @Override
    protected void initialize(@NotNull QuestPartakingEntity players) throws QuestAlreadyRunning {

    }

    @Override
    protected <E extends QuestPartakingEntity> RunningQuest<E> getQuestProgress(@NotNull E players) throws QuestNotRunning {
        return null;
    }

    @Override
    protected void cancel(@NotNull QuestPartakingEntity players) throws QuestNotRunning {

    }
}
