package com.standard.akka.sample.example.part1;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;

/**
 * Copyright (C), 2019-2019
 * FileName: PrintMyActorRefActor
 * Author:   s·D·bs
 * Date:     2019/5/25 11:44
 * Description:
 * Motto: 0.45%
 */
public class PrintMyActorRefActor extends AbstractActor {

    static Props props() {
        return Props.create(PrintMyActorRefActor.class, PrintMyActorRefActor::new);
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .matchEquals(
                        "print it",
                        p -> {
                            ActorRef secondRef = getContext().actorOf(Props.empty(), "second-actor");
                            System.out.println("Second: " + secondRef);
                        })
                .build();
    }


}
