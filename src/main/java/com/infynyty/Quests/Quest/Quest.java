package com.infynyty.Quests.Quest;


import com.infynyty.Quests.Player.QuestPartakingEntity;
import com.infynyty.Quests.QuestStartNode;
import com.infynyty.Quests.QuestState;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * Each object of this type represents a getQuest. A getQuest consists of exactly one {@link QuestStartNode start node} and
 * optionally any number of {@link com.infynyty.Quests.QuestNode getQuest nodes}, which can be thought of as a directed graph.
 * This also implies that {@link com.infynyty.Quests.QuestNode getQuest nodes} may have self-loops and multiple neighbours.
 * A getQuest can generate a {@link RunningQuest running getQuest} for a {@link QuestPartakingEntity player} by calling
 * {@link #initialize(QuestPartakingEntity)}. At any time there may be at most one {@link RunningQuest running getQuest}, given
 * a {@link QuestPartakingEntity player} and a getQuest instance, to ensure that any {@link QuestPartakingEntity player} can
 * be mapped to a {@link com.infynyty.Quests.QuestNode getQuest node} and {@link com.infynyty.Quests.QuestState getQuest state}
 * unambiguously.
 */
@Getter
@EqualsAndHashCode(of = "id")
public abstract class Quest<R extends RunningQuest<?>> {

    private final QuestStartNode startNode;

    private final UUID id = UUID.randomUUID();

    /**
     * Creates a new getQuest. Any getQuest is required to have exactly one {@link QuestStartNode start node}.
     *
     * @param startNode The single entry point of the getQuest.
     */
    public Quest(@NotNull final QuestStartNode startNode) {
        this.startNode = startNode;
    }


    /**
     * Allows to safely check whether a getQuest is running for a given {@link QuestPartakingEntity player}.
     * @param players The entity for which the getQuest is checked.
     * @return {@code True} if and only if a {@link RunningQuest running getQuest} exists for the given {@link QuestPartakingEntity player}
     * and has the state {@link QuestState#RUNNING}.
     */
    public final boolean isRunning(@NotNull final QuestPartakingEntity players) {
        try {
            return getQuestProgress(players).getState() == QuestState.RUNNING;
        } catch (QuestNotRunning questNotRunning) {
            return false;
        }
    }

    /**
     * Initializes a new Quest for a given {@link QuestPartakingEntity player}.
     * @param players The entity for which the getQuest is started.
     * @throws QuestAlreadyRunning Since there may be at most one {@link RunningQuest running getQuest} for a given {@link QuestPartakingEntity player}
     * and {@link Quest getQuest instance}, this exception is thrown if a {@link RunningQuest running getQuest} already exists
     * for this {@link QuestPartakingEntity player}.
     */
    protected abstract void initialize(@NotNull final QuestPartakingEntity players) throws QuestAlreadyRunning;


    /**
     * Gets the quest progress for a given {@link QuestPartakingEntity player}.
     * @param players The players for which the quest progress is requested.
     * @return The quest progress for the given {@link QuestPartakingEntity player}.
     * @param <E> The type of the {@link QuestPartakingEntity player}.
     * @throws QuestNotRunning If the quest is not currently running. Check with {@link #isRunning(QuestPartakingEntity)} before calling this method.
     */
    protected abstract <E extends QuestPartakingEntity> RunningQuest<E> getQuestProgress(@NotNull final E players) throws QuestNotRunning;
    protected abstract void cancel(@NotNull final QuestPartakingEntity players) throws QuestNotRunning;
}
