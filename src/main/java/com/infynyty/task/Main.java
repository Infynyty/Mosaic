package com.infynyty.task;

import java.util.Scanner;

import com.infynyty.task.events.EventHandler;
import com.infynyty.task.events.TaskEvent;
import com.infynyty.task.events.TypeEvent;
import com.infynyty.task.events.update.TaskStartEvent;
import com.infynyty.task.graph.SimpleEdgeResolver;
import com.infynyty.task.graph.SimpleNode;
import com.infynyty.task.graph.SimpleResponse;
import com.infynyty.task.graph.TaskEdgeResponse;
import com.infynyty.task.participant.SimpleParticipant;
import com.infynyty.task.tasks.SimpleTaskSupervisor;
import com.infynyty.task.tasks.TaskState;
import org.jetbrains.annotations.NotNull;

public class Main {
    public static void main(String[] args) {
        TaskEvent.addEventListener(new Main());
        final SimpleNode node1 = new SimpleNode("Try typing some text.");
        final SimpleNode node2 = new SimpleNode("You win.");

        final SimpleEdgeResolver edge1 = new SimpleEdgeResolver("Wrong text", taskActionEvent -> {
            if (!(taskActionEvent instanceof TypeEvent typeEvent)) return new SimpleResponse(node1, TaskEdgeResponse.Type.NOT_APPLICABLE);
            if (!(typeEvent.getText().equalsIgnoreCase("Hi"))) {
                System.out.println("Wrong text! Try 'hi'!");
                return new SimpleResponse(node1, TaskEdgeResponse.Type.REPEAT_NODE);
            }
            return new SimpleResponse(node2, TaskEdgeResponse.Type.CHANGE_NODE);
        });

        node1.setEdgeResolver(edge1);

        final SimpleTaskSupervisor task = new SimpleTaskSupervisor(node1);
        final SimpleParticipant participant = new SimpleParticipant();
        task.initializeTask(participant);
        while (task.getTask(participant).getState() != TaskState.COMPLETED) {
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