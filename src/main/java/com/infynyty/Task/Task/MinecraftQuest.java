package com.infynyty.Task.Task;

import com.infynyty.Task.Participant.BukkitQuestPlayer;
import com.infynyty.Task.Participant.TaskParticipant;
import com.infynyty.Task.Graph.TaskStartNode;
import org.jetbrains.annotations.NotNull;



public class MinecraftQuest extends Task<RunningTask<BukkitQuestPlayer>> {

    /**
     * Creates a new getQuest. Any getQuest is required to have exactly one {@link TaskStartNode start node}.
     *
     * @param startNode  The single entry point of the getQuest.
     */
    public MinecraftQuest(@NotNull TaskStartNode startNode) {
        super(startNode);
    }

    @Override
    protected void initialize(@NotNull TaskParticipant players) throws TaskAlreadyRunning {

    }

    @Override
    protected <E extends TaskParticipant> RunningTask<E> getTaskProgress(@NotNull E players) throws TaskNotRunning {
        return null;
    }

    @Override
    protected void cancel(@NotNull TaskParticipant players) throws TaskNotRunning {

    }
}
