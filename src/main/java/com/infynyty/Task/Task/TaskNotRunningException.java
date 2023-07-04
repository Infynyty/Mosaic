package com.infynyty.Task.Task;

import com.infynyty.Task.Participant.TaskParticipant;
import lombok.Getter;

@Getter
public class TaskNotRunningException extends RuntimeException {
    private final TaskParticipant player;
    private final TaskSupervisor<?, ?> taskSupervisor;

    public TaskNotRunningException(TaskParticipant player, TaskSupervisor<?, ?> taskSupervisor) {
        super("Task " + taskSupervisor + " is not running for " + player);
        this.player = player;
        this.taskSupervisor = taskSupervisor;
    }
}
