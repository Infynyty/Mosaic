package com.infynyty.Quests.Quest;

import com.infynyty.Quests.Events.EventHandler;
import com.infynyty.Quests.Events.QuestCompleteEvent;
import com.infynyty.Quests.Events.QuestEvent;
import com.infynyty.Quests.Events.QuestStartEvent;
import com.infynyty.Quests.Player.QuestPartakingEntity;
import com.infynyty.Quests.QuestEdgeResponse;
import com.infynyty.Quests.QuestNode;
import com.infynyty.Quests.QuestState;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.function.Function;

@Getter
public abstract class RunningQuest<E extends QuestPartakingEntity> implements EventHandler {

    @NotNull
    private final E player;
    @NotNull
    private final Quest<?> quest;
    @NotNull
    private QuestState state;
    @NotNull
    private QuestNode node;

    protected RunningQuest(@NotNull final E player, @NotNull final Quest<?> quest) {
        this.player = player;
        this.quest = quest;
        this.node = quest.getStartNode();
        this.state = QuestState.INITIALIZED;
    }

    public void start() {
        this.state = QuestState.RUNNING;
        QuestEvent.addHandler(this);
        new QuestStartEvent<>(this).call();
    }

    public void handleEvent(@NotNull final QuestEvent questEvent) {
        Optional<QuestNode> nextNode = node.getOutgoingEdges()
                .stream()
                .map(questEdge -> questEdge.handleEvent(questEvent))
                .filter(questEdgeResponse -> questEdgeResponse.getResponseType() == QuestEdgeResponse.QuestEdgeResponseType.CHANGE_NODE)
                .map(QuestEdgeResponse::getQuestNode)
                .findAny();
        if (nextNode.isEmpty()) return;
        this.node = nextNode.get();
        if (node.getOutgoingEdges().size() == 0) {
            this.state = QuestState.COMPLETED;
            final QuestCompleteEvent<RunningQuest<E>> test = new QuestCompleteEvent<>(this);
            test.call();
        }
    }

    protected abstract QuestNode getNode();
    protected abstract void cancel();

}
