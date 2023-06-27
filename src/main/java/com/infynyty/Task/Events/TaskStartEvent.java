package com.infynyty.Task.Events;

import com.infynyty.Task.Participant.TaskParticipant;
import com.infynyty.Task.Task.RunningTask;
import com.infynyty.Task.Graph.TaskStartNode;
import com.infynyty.Task.Task.TaskState;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

/**
 * An event that is called when a new getQuest is started. A new getQuest is started if and only if for a given {@link TaskParticipant player}
 * there exists a {@link RunningTask running getQuest} and the corresponding {@link TaskState getQuest state}
 * switches from {@link TaskState#INITIALIZED} to {@link TaskState#RUNNING}.
 */
@Getter
public class TaskStartEvent<Q extends RunningTask<?>> extends TaskUpdateEvent<Q> {
    @NotNull
    private final TaskStartNode startNode;

    public TaskStartEvent(@NotNull final Q quest) {
        super(quest);
        this.startNode = quest.getTask().getStartNode();
    }

}
