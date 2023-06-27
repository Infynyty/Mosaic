package com.infynyty.Task.Task;

import com.infynyty.Task.Player.TaskParticipant;

public class TaskNotRunning extends RuntimeException {
    private final TaskParticipant player;
    private final Task task;

    public TaskNotRunning(TaskParticipant player, Task task) {
        super("Quest " + task + " is not running for " + player);
        this.player = player;
        this.task = task;
    }
}
