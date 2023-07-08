package com.infynyty.mosaic.events;

import com.infynyty.mosaic.tasks.Task;

/**
 * Represents an event that may trigger a {@link Task task} to change state. In contrast
 * to {@link TaskUpdateEvent task update events}, these events are not triggered by a task itself, but by an external
 * source. These events are application specific and all events that meet the above requirements should extend this class.
 * Events may be handled in a non-deterministic order.
 */
public abstract non-sealed class TaskActionEvent extends TaskEvent {

}
