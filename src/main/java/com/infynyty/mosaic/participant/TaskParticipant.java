package com.infynyty.mosaic.participant;

import com.infynyty.mosaic.tasks.Task;
import com.infynyty.mosaic.tasks.TaskSupervisor;

/**
 * Represents a participant of a task. It has to be guaranteed that during a {@link Task}
 * after any change was made, it holds that:
 * 1. {@code oldParticipant.equals(newParticipant) == true}
 * 2. {@code newParticipant.equals(other) == false} for all participants of the {@link TaskSupervisor} other than newParticipant
 * This guarantees that a task participant is only ever mapped to a single {@link Task}.
 */
public interface TaskParticipant {
}
