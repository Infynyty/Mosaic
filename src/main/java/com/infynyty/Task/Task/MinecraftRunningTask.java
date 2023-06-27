package com.infynyty.Task.Task;

import com.infynyty.Task.Player.BukkitQuestPlayer;
import com.infynyty.Task.TaskNode;
import org.jetbrains.annotations.NotNull;

public class MinecraftRunningTask extends RunningTask<BukkitQuestPlayer> {
    public MinecraftRunningTask(@NotNull BukkitQuestPlayer player, @NotNull Task<MinecraftRunningTask> task) {
        super(player, task);
    }

    @Override
    public TaskNode getNode() {
        return null;
    }

    @Override
    protected void cancel() {

    }
}
