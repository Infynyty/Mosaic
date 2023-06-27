package com.infynyty.Quests.Events;

import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

public abstract class QuestEvent {
    private static final List<EventHandler> handlers = new ArrayList<>();

    public static void addHandler(@NonNull final EventHandler handler) {
        handlers.add(handler);
    }

    public void call() {
        handlers.forEach(handler -> handler.handleEvent(this));
    }
}
