package com.infynyty.mosaic.events;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class EventHandler {

    private final Map<Class<? extends TaskEvent>, EventNode> events = new HashMap<>();

    private static EventHandler instance;


    private EventHandler() {}

    public static EventHandler getInstance() {
        if (instance == null) {
            instance = new EventHandler();
        }
        return instance;
    }

    public void addHandler(@NotNull final Class<? extends TaskEvent> event, @NotNull final Consumer<TaskEvent> handler) {
        if (!events.containsKey(event)) {
            registerEvent(event, null);
        }
        events.get(event).addHandler(handler);
    }

    public void removeHandler(@NotNull final Class<? extends TaskEvent> event, @NotNull final Consumer<TaskEvent> handler) {
        if (!events.containsKey(event)) return;
        events.get(event).removeHandler(handler);
    }

    public void callEvent(@NotNull final TaskEvent event) {
        if (!events.containsKey(event.getClass())) {
            registerEvent(event.getClass(), null);
        }
        EventNode node = events.get(event.getClass());
        while (node != null) {
            node.handlers.forEach(handler -> handler.accept(event));
            node = node.parent;
        }
    }

    private void registerEvent(@NotNull final Class<? extends TaskEvent> event, @Nullable final EventNode child) {
        if (events.containsKey(event)) { //node already exists, just add child
            if (child != null) {
                events.get(event).addChild(child);
            }
            return;
        }
        final EventNode node = new EventNode();
        if (child != null) {
            node.addChild(child);
        }
        if (event != TaskEvent.class) {
            registerEvent((Class<? extends TaskEvent>) event.getSuperclass(), node);
        }
        events.put(event, node);
    }

    private static class EventNode {
        @Nullable
        private EventNode parent;

        private final List<Consumer<TaskEvent>> handlers = new ArrayList<>();

        private void addChild(EventNode child) {
            child.parent = this;
        }

        private void addHandler(Consumer<TaskEvent> handler) {
            handlers.add(handler);
        }

        private void removeHandler(Consumer<TaskEvent> handler) {
            handlers.remove(handler);
        }
    }
}