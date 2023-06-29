package com.infynyty.Task.Events;

import lombok.NonNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * This class is the base class for all task events. It provides methods to add event handlers for a given event.
 */
public abstract class TaskEvent {
    private static final Map<Class<? extends TaskEvent>, List<ListenerContainer>> handlers = new HashMap<>();

    /**
     * Adds an event handler for this event, that will be triggered when {@link #call()} is called.
     * @param listener The event handler to add.
     */
    public static void addEventListener(@NonNull final Object listener) {
        for (Method method : listener.getClass().getMethods()) {
            if (!method.isAnnotationPresent(EventHandler.class)) continue;
            final Class<?> eventClass = method.getParameterTypes()[0];
            if (!TaskEvent.class.isAssignableFrom(eventClass)) continue;
            if (!handlers.containsKey(eventClass)) {
                handlers.put((Class<? extends TaskEvent>) eventClass, new ArrayList<>(List.of(new ListenerContainer(listener, method))));
            } else {
                handlers.get(eventClass).add(new ListenerContainer(listener, method));
            }
        }
    }

    /**
     * Triggers an event. This will cause it to be handled by all registered event handlers.
     */
    public void call() {
        for (final Class<?> clazz : handlers.keySet()) {
            if (!clazz.isAssignableFrom(this.getClass())) continue;
            if (handlers.get(clazz) == null) continue;
            for (final ListenerContainer container : handlers.get(clazz)) {
                try {
                    container.method.invoke(container.listener, this);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private record ListenerContainer(Object listener, Method method) {

    }
}
