package com.infynyty.mosaic.events;

import lombok.Getter;

@Getter
public class TypeEvent extends TaskActionEvent {
    private final String text;

    public TypeEvent(String text) {
        this.text = text;
    }
}
