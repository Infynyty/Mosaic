package com.infynyty.mosaic.events;

import lombok.NonNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is the superclass for all task events. It provides methods to add event handlers for a given event.
 * Events may be handled in a non-deterministic order.
 */
public abstract sealed class TaskEvent permits TaskActionEvent, TaskUpdateEvent {
}
