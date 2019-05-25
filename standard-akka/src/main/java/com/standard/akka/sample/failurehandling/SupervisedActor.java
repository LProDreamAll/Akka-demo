package com.standard.akka.sample.failurehandling;

import akka.actor.AbstractActor;
import akka.actor.Props;

/**
 * Copyright (C), 2019-2019
 * FileName: SupervisedActor
 * Author:   s·D·bs
 * Date:     2019/5/25 13:38
 * Description: 策略actor
 * Motto: 0.45%
 */
public class SupervisedActor extends AbstractActor {

    static Props props() {
        return Props.create(SupervisedActor.class, SupervisedActor::new);
    }

    @Override
    public void preStart() {
        System.out.println("supervised actor started");
    }

    @Override
    public void postStop() {
        System.out.println("supervised actor stopped");
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .matchEquals(
                        "fail",
                        f -> {
                            System.out.println(f);
                            System.out.println("supervised actor fails now");
                            throw new Exception("I failed!");
                        })
                .build();
    }
}
