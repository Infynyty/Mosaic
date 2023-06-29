package com.infynyty.Task.Participant;

import lombok.Getter;

@Getter
public class SimpleParticipant implements TaskParticipant {
    private final String name = "SimpleParticipant";


    @Override
    public boolean equals(TaskParticipant other) {
        return false;
    }
}
