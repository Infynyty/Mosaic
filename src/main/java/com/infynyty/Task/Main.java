package com.infynyty.Task;

import com.infynyty.Task.Events.*;
import com.infynyty.Task.Graph.SimpleEdge;
import com.infynyty.Task.Graph.SimpleNode;
import com.infynyty.Task.Participant.SimpleParticipant;
import com.infynyty.Task.Task.SimpleRunningTask;
import com.infynyty.Task.Task.SimpleTask;
import org.jetbrains.annotations.NotNull;

import static java.lang.Thread.sleep;

public class Main implements Listener {
    public static void main(String[] args) throws InterruptedException {
        TaskEvent.addListener(new Main());
        final SimpleNode node1 = new SimpleNode("node1");
        final SimpleNode node2 = new SimpleNode("node2");
        final SimpleNode node3 = new SimpleNode("node3");

        final SimpleEdge edge1 = new SimpleEdge(node2, "edge1");
        final SimpleEdge edge2 = new SimpleEdge(node3, "edge2");

        node1.addOutgoingEdge(edge1);
        node2.addOutgoingEdge(edge2);

        final SimpleEvent event = new SimpleEvent("event");

        final SimpleTask task = new SimpleTask(node1);
        final SimpleParticipant participant = new SimpleParticipant();
        task.initialize(participant);
        event.call();
        sleep(1000*2);
        event.call();
    }

    @EventHandler
    public void handleEvent(@NotNull SimpleEvent event) {
        System.out.println("Simple event name: " + event.getName());
    }

    @EventHandler
    public void onFinish(TaskCompleteEvent event) {
        SimpleParticipant participant = (SimpleParticipant) event.<SimpleRunningTask>getTask().getParticipant();
        System.out.println("Participant " + participant.getName() + " started");
    }
}