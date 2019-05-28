package com.standard.akka.myactor.part2;

import akka.actor.AbstractActor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright (C), 2019-2019
 * FileName: HotSwapActor
 * Author:   s·D·bs
 * Date:     2019/5/28 11:42
 * Description: 使用become以下方法来热交换Actor行为 替换
 * Motto: 0.45%
 */
@Slf4j
public class HotSwapActor extends AbstractActor {


    private AbstractActor.Receive angry;
    private AbstractActor.Receive happy;

    public HotSwapActor() {
        angry = receiveBuilder()
                .matchEquals("foo",
                        s -> {
                            getSender().tell("I am already angry?", getSelf());
                            log.info("s :[{}]", s);
                        })
                .matchEquals(
                        "bar",
                        s -> {
                            getContext().become(happy);
                        })
                .build();
        happy =
                receiveBuilder()
                        .matchEquals(
                                "bar",
                                s -> {
                                    getSender().tell("I am already happy :-)", getSelf());
                                })
                        .matchEquals(
                                "foo",
                                s -> {
                                    getContext().become(angry);
                                })
                        .build();
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .matchEquals("foo", s -> getContext().become(angry))
                .matchEquals("bar", s -> getContext().become(happy))
                .build();
    }
}

