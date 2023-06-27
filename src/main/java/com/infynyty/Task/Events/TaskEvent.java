package com.infynyty.Task.Events;

import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is the base class for all task events. It provides methods to add event handlers for a given event.
 */
public abstract class TaskEvent {
    private static final List<EventHandler> handlers = new ArrayList<>();

    /**
     * Adds an event handler for this event, that will be triggered when {@link #call()} is called.
     * @param handler The event handler to add.
     */
    public static void addHandler(@NonNull final EventHandler handler) {
        handlers.add(handler);
    }

    /**
     * Triggers an event. This will cause it to be handled by all registered event handlers.
     */
    public void call() {
        handlers.forEach(handler -> handler.handleEvent(this));
    }
}
