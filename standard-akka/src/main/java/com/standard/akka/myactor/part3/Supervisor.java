package com.standard.akka.myactor.part3;

import akka.actor.AbstractActor;
import akka.actor.OneForOneStrategy;
import akka.actor.Props;
import akka.actor.SupervisorStrategy;
import akka.japi.pf.DeciderBuilder;

import java.time.Duration;
import java.util.Optional;

/**
 * Copyright (C), 2019-2019
 * FileName: Supervisor
 * Author:   s·D·bs
 * Date:     2019/5/28 16:13
 * Description: 错误策略处理
 * Motto: 0.45%
 */
public class Supervisor extends AbstractActor {

    private static SupervisorStrategy strategy =
            new OneForOneStrategy(
                    10,
                    Duration.ofMinutes(1),
                    DeciderBuilder.match(ArithmeticException.class, e -> SupervisorStrategy.resume())
                            .match(NullPointerException.class, e -> SupervisorStrategy.restart())
                            .match(IllegalArgumentException.class, e -> SupervisorStrategy.stop())
                            .matchAny(o -> SupervisorStrategy.escalate())
                            .build());

    @Override
    public SupervisorStrategy supervisorStrategy() {
        return strategy;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(
                        Props.class,
                        props -> {
                            getSender().tell(getContext().actorOf(props), getSelf());
                        })
                .build();
    }



    static class Supervisor2 extends AbstractActor {

        private static SupervisorStrategy strategy =
                new OneForOneStrategy(
                        10,
                        Duration.ofMinutes(1),
                        DeciderBuilder.match(ArithmeticException.class, e -> SupervisorStrategy.resume())
                                .match(NullPointerException.class, e -> SupervisorStrategy.restart())
                                .match(IllegalArgumentException.class, e -> SupervisorStrategy.stop())
                                .matchAny(o -> SupervisorStrategy.escalate())
                                .build());

        @Override
        public SupervisorStrategy supervisorStrategy() {
            return strategy;
        }


        @Override
        public Receive createReceive() {
            return receiveBuilder()
                    .match(
                            Props.class,
                            props -> {
                                getSender().tell(getContext().actorOf(props), getSelf());
                            })
                    .build();
        }

        @Override
        public void preRestart(Throwable cause, Optional<Object> msg) {
            // do not kill all children, which is the default here
        }
    }
}
