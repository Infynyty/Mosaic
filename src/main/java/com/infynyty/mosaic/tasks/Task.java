package com.infynyty.mosaic.tasks;

import com.infynyty.mosaic.events.EventHandler;
import com.infynyty.mosaic.events.TaskActionEvent;
import com.infynyty.mosaic.events.TaskEvent;
import com.infynyty.mosaic.events.update.*;
import com.infynyty.mosaic.graph.TaskEdgeResponse;
import com.infynyty.mosaic.graph.TaskNode;
import com.infynyty.mosaic.participant.TaskParticipant;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

/**
 * This class represents an active instance of {@link TaskSupervisor}. It contains information about {@link TaskParticipant who}
 * is concerned by this task and which state they are in. It also updates the state according to occurring {@link TaskEvent events}.
 * @param <P> The type of {@link TaskParticipant} this task should contain.
 */
@Getter
public class Task<P extends TaskParticipant, S extends TaskSupervisor<P, ?>> {

    @NotNull
    private final P participant;
    @NotNull
    private final S taskSupervisor;
    @NotNull
    private TaskState state;
    @NotNull
    private TaskNode currentNode;

    /**
     * Initializes a new running task. This sets its {@link TaskState state} to {@link TaskState#INITIALIZED}. To start
     * it use {@link #start()}.
     * @param participant The participant for this running task.
     * @param taskSupervisor The task that created this running task.
     */
    public Task(@NotNull final P participant, @NotNull final S taskSupervisor) {
        this.participant = participant;
        this.taskSupervisor = taskSupervisor;
        this.currentNode = taskSupervisor.getStartNode();
        this.state = TaskState.INITIALIZED;
    }

    /**
     * Starts this running task, i.e. sets its {@link TaskState} to {@link TaskState#RUNNING}. The current node of this
     * running task can now be changed by the corresponding {@link TaskEvent events}. This also triggers a
     * {@link TaskStartEvent}.
     */
    public void start() {
        EventHandler.getInstance().callEvent(new TaskStartEvent(this));
        this.state = TaskState.RUNNING;
        if (!currentNode.isTerminalNode()) {
            EventHandler.getInstance().addHandler(TaskActionEvent.class, this::handleEvent);
        } else {
            handleTaskCompletion();
        }
    }

    /**
     * Cancels a running task, i.e. sets its {@link TaskState} to {@link TaskState#CANCELLED}. The current node can no longer
     * be changed by any events. This also triggers a {@link TaskCancelEvent}.
     */
    public void cancel() {
        EventHandler.getInstance().removeHandler(TaskActionEvent.class, this::handleEvent);
        final TaskCancelEvent cancelEvent = new TaskCancelEvent(this);
        EventHandler.getInstance().callEvent(cancelEvent);
        this.state = TaskState.CANCELLED;
    }

    /**
     * Delegates an event to the currently active node and its corresponding edges.
     * @param taskEvent The event to be handled.
     */
    public void handleEvent(@NotNull final TaskEvent taskEvent) {
        if (!(taskEvent instanceof TaskActionEvent taskActionEvent)) return;
        final TaskEdgeResponse response = currentNode.getEdgeResolver().resolveEdge().apply(taskActionEvent);
        if (response.getResponseType() == TaskEdgeResponse.Type.CHANGE_NODE) {
            final TaskNodeChangeEvent nodeChangeEvent = new TaskNodeChangeEvent(this, this.currentNode, response.getTaskNode());
            this.currentNode = response.getTaskNode();
            EventHandler.getInstance().callEvent(nodeChangeEvent);
            if (currentNode.isTerminalNode()) handleTaskCompletion();
        } else if (response.getResponseType() == TaskEdgeResponse.Type.REPEAT_NODE) {
            final TaskNodeRepeatEvent nodeRepeatEvent = new TaskNodeRepeatEvent(this, this.currentNode);
            EventHandler.getInstance().callEvent(nodeRepeatEvent);
        }
    }

    private void handleTaskCompletion() {
        EventHandler.getInstance().removeHandler(TaskActionEvent.class, this::handleEvent);
        this.state = TaskState.COMPLETED;
        final TaskCompleteEvent completeEvent = new TaskCompleteEvent(this, this.getCurrentNode());
        EventHandler.getInstance().callEvent(completeEvent);
    }
}
