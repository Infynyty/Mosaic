package com.infynyty.Task.Task;

import com.infynyty.Task.Participant.TaskParticipant;
import lombok.Getter;

@Getter
public class TaskAlreadyRunning extends RuntimeException {
    private final TaskParticipant player;
    private final Task<?> task;

    public TaskAlreadyRunning(TaskParticipant player, Task<?> task) {
        super("Quest " + task + " is already running for " + player);
        this.player = player;
        this.task = task;
    }
}
