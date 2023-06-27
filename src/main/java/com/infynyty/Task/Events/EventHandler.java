package com.infynyty.Task.Events;

import org.jetbrains.annotations.NotNull;

public interface EventHandler {
    void handleEvent(@NotNull final TaskEvent event);
}
