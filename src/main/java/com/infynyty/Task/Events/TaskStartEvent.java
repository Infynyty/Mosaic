package com.infynyty.Task.Events;

import com.infynyty.Task.Player.TaskParticipant;
import com.infynyty.Task.Task.RunningTask;
import com.infynyty.Task.TaskState;
import org.jetbrains.annotations.NotNull;

/**
 * An event that is called when a new getQuest is started. A new getQuest is started if and only if for a given {@link TaskParticipant player}
 * there exists a {@link RunningTask running getQuest} and the corresponding {@link TaskState getQuest state}
 * switches from {@link TaskState#INITIALIZED} to {@link TaskState#RUNNING}.
 */
public class TaskStartEvent<Q extends RunningTask<?>> extends TaskUpdateEvent<Q> {
    public TaskStartEvent(@NotNull final Q quest) {
        super(quest);
    }

}
