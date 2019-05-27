package com.standard.akka.sample.current;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.Terminated;

/**
 * Copyright (C), 2019-2019
 * FileName: WatchActor
 * Author:   s·D·bs
 * Date:     2019/5/27 11:47
 * Description: 监控
 * Motto: 0.45%
 */
public class WatchActor extends AbstractActor {

    ActorSystem system = ActorSystem.create("watchSystem");
    private final ActorRef child = getContext().actorOf(Props.empty(), "target");
    private ActorRef lastSender = system.deadLetters();

    public WatchActor() {
        getContext().watch(child);
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .matchEquals(
                        "kill",
                        s -> {
                            getContext().stop(child);
                            lastSender = getSender();
                        })
                .match(
                        Terminated.class,
                        t -> t.actor().equals(child),
                        t -> {
                            lastSender.tell("finished", getSelf());
                        })
                .build();
    }
}
