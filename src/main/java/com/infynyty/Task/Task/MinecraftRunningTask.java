package com.infynyty.Task.Task;

import com.infynyty.Task.Participant.BukkitQuestPlayer;
import com.infynyty.Task.Graph.TaskNode;
import org.jetbrains.annotations.NotNull;

public class MinecraftRunningTask extends RunningTask<BukkitQuestPlayer> {
    public MinecraftRunningTask(@NotNull BukkitQuestPlayer player, @NotNull Task<MinecraftRunningTask> task) {
        super(player, task);
    }

    @Override
    public TaskNode getCurrentNode() {
        return null;
    }

    @Override
    protected void cancel() {

    }
}
