package com.infynyty.Task.Task;

import com.infynyty.Task.Events.*;
import com.infynyty.Task.Participant.TaskParticipant;
import com.infynyty.Task.Graph.TaskEdgeResponse;
import com.infynyty.Task.Graph.TaskNode;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * This class represents an active instance of {@link Task}. It contains information about {@link TaskParticipant who}
 * is concerned by this task and which state they are in. It also updates the state according to occurring {@link TaskEvent events}.
 * @param <E> The type of {@link TaskParticipant} this task should contain.
 */
@Getter
public abstract class RunningTask<E extends TaskParticipant> {

    @NotNull
    private final E participant;
    @NotNull
    private final Task<?> task;
    @NotNull
    private TaskState state;
    @NotNull
    private TaskNode node;

    /**
     * Initializes a new running task. This sets its {@link TaskState state} to {@link TaskState#INITIALIZED}. To start
     * it use {@link #start()}.
     * @param participant The participant for this running task.
     * @param task The task that created this running task.
     */
    protected RunningTask(@NotNull final E participant, @NotNull final Task<?> task) {
        this.participant = participant;
        this.task = task;
        this.node = task.getStartNode();
        this.state = TaskState.INITIALIZED;
    }

    /**
     * Starts this running task, i.e. sets its {@link TaskState} to {@link TaskState#RUNNING}. The current node of this
     * running task can now be changed by the corresponding {@link TaskEvent events}. This also triggers a
     * {@link TaskStartEvent}.
     */
    public void start() {
        this.state = TaskState.RUNNING;
        TaskEvent.addEventListener(this);
        new TaskStartEvent<>(this).call();
    }

    @EventHandler
    public void handleEvent(@NotNull final TaskActionEvent taskEvent) {
        if (node.getOutgoingEdges().size() == 0) {
            this.state = TaskState.COMPLETED;
            final TaskCompleteEvent<RunningTask<E>> test = new TaskCompleteEvent<>(this, this.getCurrentNode());
            test.call();
        }
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
            final TaskCompleteEvent<RunningTask<E>> test = new TaskCompleteEvent<>(this, this.getCurrentNode());
            test.call();
        }
    }

    protected abstract @NotNull TaskNode getCurrentNode();
    protected abstract void cancel();

}
