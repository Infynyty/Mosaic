package com.infynyty.task.graph;

import java.util.function.Function;

import com.infynyty.task.events.TaskActionEvent;
import org.jetbrains.annotations.NotNull;

/**
 * This interface is intended to decide the next state of a {@link com.infynyty.task.participant.TaskParticipant}, given
 * an {@link com.infynyty.task.events.TaskEvent}. An edge resolver was chosen over a task edge, so that for any given
 * event there will always only be a single edge response, guaranteeing a deterministic state for all
 * {@link com.infynyty.task.participant.TaskParticipant participants}.
 */
public interface TaskEdgeResolver {
    /**
     * For any given event, this will return a {@link TaskEdgeResponse}. Note that the type of event needs to be checked.
     *
     * @return An edge response. If the event is not applicable return a response where {@link TaskEdgeResponse#getTaskNode()}
     * returns the current node and of type {@link TaskEdgeResponse.Type#NOT_APPLICABLE}.
     */
    @NotNull Function<TaskActionEvent, TaskEdgeResponse> resolveEdge();
}
