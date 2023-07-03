package com.infynyty.Task.Events;

import lombok.Getter;

@Getter
public class TypeEvent extends TaskActionEvent {
    private final String text;

    public TypeEvent(String text) {
        this.text = text;
    }
}
