package com.standard.akka.myactor.part3;

import akka.actor.AbstractActor;

/**
 * Copyright (C), 2019-2019
 * FileName: Child
 * Author:   s·D·bs
 * Date:     2019/5/28 16:13
 * Description:
 * Motto: 0.45%
 */
public class Child extends AbstractActor {

    int state = 0;

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(
                        Exception.class,
                        exception -> {
                            throw exception;
                        })
                .match(Integer.class, i -> state = i)
                .matchEquals("get", s -> getSender().tell(state, getSelf()))
                .build();
    }
}
