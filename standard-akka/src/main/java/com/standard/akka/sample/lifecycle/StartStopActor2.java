package com.standard.akka.sample.lifecycle;

import akka.actor.AbstractActor;
import akka.actor.Props;

/**
 * Copyright (C), 2019-2019
 * FileName: StartStopActor2
 * Author:   s·D·bs
 * Date:     2019/5/25 13:32
 * Description:
 * Motto: 0.45%
 */
public class StartStopActor2 extends AbstractActor {

    static Props props() {
        return Props.create(StartStopActor2.class, StartStopActor2::new);
    }

    @Override
    public void preStart() throws Exception {
        System.out.println("second started");
    }

    @Override
    public void postStop() {
        System.out.println("second stopped");
    }


    @Override
    public Receive createReceive() {
        return receiveBuilder().build();
    }
}
