package com.infynyty.task.tasks;

import com.infynyty.task.participant.TaskParticipant;
import lombok.Getter;

@Getter
public class TaskAlreadyRunningException extends RuntimeException {
    private final TaskParticipant player;
    private final TaskSupervisor<?, ?> taskSupervisor;

    public TaskAlreadyRunningException(TaskParticipant player, TaskSupervisor<?, ?> taskSupervisor) {
        super("Task " + taskSupervisor + " is already running for " + player);
        this.player = player;
        this.taskSupervisor = taskSupervisor;
    }
}
