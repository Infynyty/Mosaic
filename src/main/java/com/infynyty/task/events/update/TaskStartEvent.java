package com.infynyty.task.events.update;

import com.infynyty.task.events.TaskUpdateEvent;
import com.infynyty.task.graph.TaskNode;
import com.infynyty.task.participant.TaskParticipant;
import com.infynyty.task.tasks.Task;
import com.infynyty.task.tasks.TaskState;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

/**
 * An event that is called when a new getQuest is started. A new getQuest is started if and only if for a given {@link TaskParticipant player}
 * there exists a {@link Task running getQuest} and the corresponding {@link TaskState getQuest state}
 * switches from {@link TaskState#INITIALIZED} to {@link TaskState#RUNNING}.
 */
@Getter
public class TaskStartEvent extends TaskUpdateEvent {
    @NotNull
    private final TaskNode startNode;

    public TaskStartEvent(@NotNull final Task<?> task) {
        super(task);
        this.startNode = task.getTaskSupervisor().getStartNode();
    }

}
