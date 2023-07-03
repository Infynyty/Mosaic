package com.infynyty.Task.Task;

import java.util.Optional;

import com.infynyty.Task.Events.EventHandler;
import com.infynyty.Task.Events.TaskActionEvent;
import com.infynyty.Task.Events.TaskCancelEvent;
import com.infynyty.Task.Events.TaskCompleteEvent;
import com.infynyty.Task.Events.TaskEvent;
import com.infynyty.Task.Events.TaskStartEvent;
import com.infynyty.Task.Graph.TaskEdgeResponse;
import com.infynyty.Task.Graph.TaskNode;
import com.infynyty.Task.Participant.TaskParticipant;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

/**
 * This class represents an active instance of {@link Task}. It contains information about {@link TaskParticipant who}
 * is concerned by this task and which state they are in. It also updates the state according to occurring {@link TaskEvent events}.
 * @param <E> The type of {@link TaskParticipant} this task should contain.
 */
@Getter
public class RunningTask<E extends TaskParticipant> {

    @NotNull
    private final E participant;
    @NotNull
    private final Task<?> task;
    @NotNull
    private TaskState state;
    @NotNull
    private TaskNode currentNode;

    /**
     * Initializes a new running task. This sets its {@link TaskState state} to {@link TaskState#INITIALIZED}. To start
     * it use {@link #start()}.
     * @param participant The participant for this running task.
     * @param task The task that created this running task.
     */
    protected RunningTask(@NotNull final E participant, @NotNull final Task<?> task) {
        this.participant = participant;
        this.task = task;
        this.currentNode = task.getStartNode();
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
        new TaskStartEvent(this).call();
    }

    /**
     * Cancels a running task, i.e. sets its {@link TaskState} to {@link TaskState#CANCELLED}. The current node can no longer
     * be changed by any events. This also triggers a {@link TaskCancelEvent}.
     */
    public void cancel() {
        this.state = TaskState.CANCELLED;
        TaskEvent.removeEventListener(this);
        new TaskCancelEvent(this).call();
    }

    /**
     * Delegates an event to the currently active node and its corresponding edges.
     * @param taskEvent The event to be handled.
     */
    @EventHandler
    public void handleEvent(@NotNull final TaskActionEvent taskEvent) {
        if (currentNode.getOutgoingEdges().size() == 0) {
            this.state = TaskState.COMPLETED;
            final TaskCompleteEvent test = new TaskCompleteEvent(this, this.getCurrentNode());
            test.call();
        }
        Optional<TaskNode> nextNode = currentNode.getOutgoingEdges()
                .stream()
                .map(questEdge -> questEdge.getEventHandler().apply(taskEvent))
                .filter(taskEdgeResponse -> taskEdgeResponse.getResponseType() == TaskEdgeResponse.Type.CHANGE_NODE)
                .map(TaskEdgeResponse::getQuestNode)
                .findAny();
        if (nextNode.isEmpty()) return;
        this.currentNode = nextNode.get();
        if (currentNode.getOutgoingEdges().size() == 0) {
            this.state = TaskState.COMPLETED;
            final TaskCompleteEvent test = new TaskCompleteEvent(this, this.getCurrentNode());
            test.call();
        }
    }
}
