package com.infynyty.Task.Task;

public enum TaskState {
    /**
     * A running task has been created, but is not yet running.
     */
    INITIALIZED,
    /**
     * A running task has been created and is currently running, but not yet finished.
     */
    RUNNING,
    /**
     * A running task has been created and completed.
     */
    COMPLETED,
    /**
     * A running task has been created and was cancelled at some point.
     */
    CANCELLED
}
