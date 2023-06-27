package com.infynyty.Task.Task;

import com.infynyty.Task.Events.EventHandler;
import com.infynyty.Task.Events.TaskCompleteEvent;
import com.infynyty.Task.Events.TaskEvent;
import com.infynyty.Task.Events.TaskStartEvent;
import com.infynyty.Task.Player.TaskParticipant;
import com.infynyty.Task.TaskEdgeResponse;
import com.infynyty.Task.TaskNode;
import com.infynyty.Task.TaskState;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

@Getter
public abstract class RunningTask<E extends TaskParticipant> implements EventHandler {

    @NotNull
    private final E player;
    @NotNull
    private final Task<?> task;
    @NotNull
    private TaskState state;
    @NotNull
    private TaskNode node;

    protected RunningTask(@NotNull final E player, @NotNull final Task<?> task) {
        this.player = player;
        this.task = task;
        this.node = task.getStartNode();
        this.state = TaskState.INITIALIZED;
    }

    public void start() {
        this.state = TaskState.RUNNING;
        TaskEvent.addHandler(this);
        new TaskStartEvent<>(this).call();
    }

    public void handleEvent(@NotNull final TaskEvent taskEvent) {
        Optional<TaskNode> nextNode = node.getOutgoingEdges()
                .stream()
                .map(questEdge -> questEdge.handleEvent(taskEvent))
                .filter(taskEdgeResponse -> taskEdgeResponse.getResponseType() == TaskEdgeResponse.QuestEdgeResponseType.CHANGE_NODE)
                .map(TaskEdgeResponse::getQuestNode)
                .findAny();
        if (nextNode.isEmpty()) return;
        this.node = nextNode.get();
        if (node.getOutgoingEdges().size() == 0) {
            this.state = TaskState.COMPLETED;
            final TaskCompleteEvent<RunningTask<E>> test = new TaskCompleteEvent<>(this);
            test.call();
        }
    }

    protected abstract TaskNode getNode();
    protected abstract void cancel();

}
