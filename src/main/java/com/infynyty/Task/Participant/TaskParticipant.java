package com.infynyty.Task.Participant;

/**
 * Represents a participant of a task.
 */
public interface TaskParticipant {

    /**
     * Returns true if this participant is equal to the other participant. It has to be guaranteed that during a {@link com.infynyty.Task.Task.RunningTask}
     * after any change was made, it holds that:
     * 1. {@code oldParticipant.equals(newParticipant) == true}
     * 2. {@code newParticipant.equals(other) == false} for all participants of the {@link com.infynyty.Task.Task.Task} other than newParticipant
     * This guarantees that a task participant is only ever mapped to a single {@link com.infynyty.Task.Task.RunningTask}.
     * @param other The other participant.
     * @return True if and only if this participant is equal to the other participant.
     */
    boolean equals(TaskParticipant other);
}
