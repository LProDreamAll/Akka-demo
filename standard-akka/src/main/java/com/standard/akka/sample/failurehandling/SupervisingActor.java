package com.standard.akka.sample.failurehandling;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;

/**
 * Copyright (C), 2019-2019
 * FileName: SupervisingActor
 * Author:   s·D·bs
 * Date:     2019/5/25 13:37
 * Description: 失败处理策略
 * Motto: 0.45%
 */
public class SupervisingActor extends AbstractActor {

    static Props props() {
        return Props.create(SupervisingActor.class, SupervisingActor::new);
    }

    ActorRef child = getContext().actorOf(SupervisedActor.props(), "supervised-actor");

    @Override
    public Receive createReceive() {

        return receiveBuilder()
                .matchEquals(
                        "failChild",
                        f -> {
                            System.out.println(child);
                            child.tell("fail", getSelf());
                        })
                .build();
    }
}
