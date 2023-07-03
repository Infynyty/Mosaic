package com.infynyty.Task;

import java.util.Scanner;

import com.infynyty.Task.Events.EventHandler;
import com.infynyty.Task.Events.TaskEvent;
import com.infynyty.Task.Events.TaskStartEvent;
import com.infynyty.Task.Events.TypeEvent;
import com.infynyty.Task.Graph.SimpleEdge;
import com.infynyty.Task.Graph.SimpleNode;
import com.infynyty.Task.Graph.SimpleResponse;
import com.infynyty.Task.Graph.TaskEdgeResponse;
import com.infynyty.Task.Participant.SimpleParticipant;
import com.infynyty.Task.Task.SimpleTask;
import com.infynyty.Task.Task.TaskState;
import org.jetbrains.annotations.NotNull;

public class Main {
    public static void main(String[] args) {
        TaskEvent.addEventListener(new Main());
        final SimpleNode node1 = new SimpleNode("Try typing some text.");
        final SimpleNode node2 = new SimpleNode("You win.");

        final SimpleEdge edge1 = new SimpleEdge("Wrong text", taskActionEvent -> {
            if (!(taskActionEvent instanceof TypeEvent typeEvent)) return new SimpleResponse(node1, TaskEdgeResponse.Type.NOT_APPLICABLE);
            if (!(typeEvent.getText().equalsIgnoreCase("Hi"))) return new SimpleResponse(node1, TaskEdgeResponse.Type.REPEAT_NODE);
            return new SimpleResponse(node2, TaskEdgeResponse.Type.CHANGE_NODE);
        });

        node1.addOutgoingEdge(edge1);

        final SimpleTask task = new SimpleTask(node1);
        final SimpleParticipant participant = new SimpleParticipant();
        task.initialize(participant);
        while (task.getTaskProgress(participant).getState() != TaskState.COMPLETED) {
            final Scanner scanner = new Scanner(System.in);
            System.out.print("Input something!");
            final String res = scanner.next();
            new TypeEvent(res).call();
        }
    }

    @EventHandler
    public void handleEvent(@NotNull TypeEvent event) {
        System.out.println("Simple event name: " + event.getText());
    }

    @EventHandler
    public void onFinish(TaskStartEvent event) {
        if (!(event.getTask().getParticipant() instanceof SimpleParticipant participant)) return;
        System.out.println("Participant " + participant.getName() + " started");
    }
}