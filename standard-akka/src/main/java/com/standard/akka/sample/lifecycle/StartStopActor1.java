package com.standard.akka.sample.lifecycle;

import akka.actor.AbstractActor;
import akka.actor.Props;

/**
 * Copyright (C), 2019-2019
 * FileName: StartStopActor1
 * Author:   s·D·bs
 * Date:     2019/5/25 13:30
 * Description: 使用actor自带的钩子函数进行停止actor
 * Motto: 0.45%
 */
public class StartStopActor1 extends AbstractActor {

    static Props props() {
        return Props.create(StartStopActor1.class, StartStopActor1::new);
    }


    @Override
    public void preStart() throws Exception {
        System.out.println("first started");
        getContext().actorOf(StartStopActor2.props(), "second");
    }

    @Override
    public void postStop() throws Exception {
        System.out.println("first stopped");
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .matchEquals(
                        "stop",
                        actor -> {
                            getContext().stop(getSelf());
                        })
                .build();
    }
}
