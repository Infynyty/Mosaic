package com.infynyty.Task.Events;

import org.jetbrains.annotations.NotNull;

/**
 * This interface is used to handle {@link TaskEvent task events}.
 */
public interface EventHandler {
    /**
     * Handles events. This method should not modify the event itself.
     * @param event The event to handle.
     */
    void handleEvent(@NotNull final TaskEvent event);
}
