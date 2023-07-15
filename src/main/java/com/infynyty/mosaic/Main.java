package com.infynyty.mosaic;

import com.infynyty.mosaic.events.EventHandler;
import com.infynyty.mosaic.events.TaskEvent;
import com.infynyty.mosaic.events.TypeEvent;
import com.infynyty.mosaic.events.update.TaskStartEvent;
import com.infynyty.mosaic.graph.SimpleEdgeResolver;
import com.infynyty.mosaic.graph.SimpleNode;
import com.infynyty.mosaic.graph.SimpleResponse;
import com.infynyty.mosaic.graph.TaskEdgeResponse;
import com.infynyty.mosaic.participant.SimpleParticipant;
import com.infynyty.mosaic.tasks.SimpleTaskSupervisor;
import com.infynyty.mosaic.tasks.TaskState;
import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EventHandler.getInstance().addHandler(TypeEvent.class, Main::hi);
        final SimpleNode node1 = new SimpleNode("Try typing some text...", () -> System.out.println("Not again!"));
        final SimpleNode node2 = new SimpleNode("You win.", () -> System.out.println("You entered the node 2!"));

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
            EventHandler.getInstance().callEvent(new TypeEvent(res));
        }
    }

    private static void hi(TaskEvent event) {
        System.out.println("hi");
    }
}