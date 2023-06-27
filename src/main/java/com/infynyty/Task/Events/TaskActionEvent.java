package com.infynyty.Task.Events;

/**
 * Represents an event that may trigger a {@link com.infynyty.Task.Task.RunningTask task} to change state. In contrast
 * to {@link TaskUpdateEvent task update events}, these events are not triggered by a task itself, but by an external
 * source.
 */
public abstract class TaskActionEvent extends TaskEvent {

}
