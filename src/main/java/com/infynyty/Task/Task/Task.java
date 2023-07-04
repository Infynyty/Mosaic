package com.infynyty.Task.Task;

import com.infynyty.Task.Events.EventHandler;
import com.infynyty.Task.Events.TaskActionEvent;
import com.infynyty.Task.Events.TaskCancelEvent;
import com.infynyty.Task.Events.TaskCompleteEvent;
import com.infynyty.Task.Events.TaskEvent;
import com.infynyty.Task.Events.TaskNodeChangeEvent;
import com.infynyty.Task.Events.TaskNodeRepeatEvent;
import com.infynyty.Task.Events.TaskStartEvent;
import com.infynyty.Task.Graph.TaskEdgeResponse;
import com.infynyty.Task.Graph.TaskNode;
import com.infynyty.Task.Participant.TaskParticipant;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

/**
 * This class represents an active instance of {@link TaskSupervisor}. It contains information about {@link TaskParticipant who}
 * is concerned by this task and which state they are in. It also updates the state according to occurring {@link TaskEvent events}.
 * @param <P> The type of {@link TaskParticipant} this task should contain.
 */
@Getter
public class Task<P extends TaskParticipant> {

    @NotNull
    private final P participant;
    @NotNull
    private final TaskSupervisor<P, Task<P>> taskSupervisor;
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
    public Task(@NotNull final P participant, @NotNull final TaskSupervisor<P, Task<P>> taskSupervisor) {
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
        TaskEvent.addEventListener(this);
        new TaskStartEvent(this).call();
        this.state = TaskState.RUNNING;
    }

    /**
     * Cancels a running task, i.e. sets its {@link TaskState} to {@link TaskState#CANCELLED}. The current node can no longer
     * be changed by any events. This also triggers a {@link TaskCancelEvent}.
     */
    public void cancel() {
        TaskEvent.removeEventListener(this);
        new TaskCancelEvent(this).call();
        this.state = TaskState.CANCELLED;
    }

    /**
     * Delegates an event to the currently active node and its corresponding edges.
     * @param taskEvent The event to be handled.
     */
    @EventHandler
    public void handleEvent(@NotNull final TaskActionEvent taskEvent) {
        // handles the case where a task consists of a single node
        if (!currentNode.hasEdgeResolver()) handleTaskCompletion();

        final TaskEdgeResponse response = currentNode.getEdgeResolver().resolveEdge().apply(taskEvent);
        if (response.getResponseType() == TaskEdgeResponse.Type.CHANGE_NODE) {
            final TaskNodeChangeEvent nodeChangeEvent = new TaskNodeChangeEvent(this, this.currentNode, response.getTaskNode());
            this.currentNode = response.getTaskNode();
            nodeChangeEvent.call();
        } else if (response.getResponseType() == TaskEdgeResponse.Type.REPEAT_NODE) {
            final TaskNodeRepeatEvent nodeRepeatEvent = new TaskNodeRepeatEvent(this, this.currentNode);
            nodeRepeatEvent.call();
        }

        if (!currentNode.hasEdgeResolver()) handleTaskCompletion();
    }

    private void handleTaskCompletion() {
        TaskEvent.removeEventListener(this);
        this.state = TaskState.COMPLETED;
        final TaskCompleteEvent completeEvent = new TaskCompleteEvent(this, this.getCurrentNode());
        completeEvent.call();
    }
}
