package com.infynyty.Task.Events;

public class SimpleEvent extends TaskActionEvent {
    private final String name;

    public SimpleEvent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
