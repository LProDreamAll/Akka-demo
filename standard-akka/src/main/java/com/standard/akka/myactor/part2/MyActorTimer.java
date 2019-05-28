package com.standard.akka.myactor.part2;

import akka.actor.AbstractActorWithTimers;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

/**
 * Copyright (C), 2019-2019
 * FileName: MyActorTimer
 * Author:   s·D·bs
 * Date:     2019/5/28 11:33
 * Description: 定时器，预定消息
 * 消息可以通过使用安排在稍后发送调度直接，但在演员调度定期或单消息本身时，它的更方便，更安全地使用名为定时器支持。
 * 当重新启动actor并由计时器处理时，计划消息的生命周期可能难以管理。
 * Motto: 0.45%
 */
@Slf4j
public class MyActorTimer extends AbstractActorWithTimers {

    private static Object TICK_KEY = "TickKey";

    private static final class FirstTick {

    }

    private static final class Tick {

    }

    public MyActorTimer() {
        getTimers().startSingleTimer(TICK_KEY, new FirstTick(), Duration.ofMillis(500));
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(
                FirstTick.class,
                message -> {
                    getTimers().startPeriodicTimer(TICK_KEY, new Tick(), Duration.ofSeconds(1));
                })
                .match(Tick.class,
                        message -> {
                            log.info("I am MyActorTimer Tick Content :[{}]", message);
                        })
                .build();
    }
}
