package com.standard.akka.practice;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.Status;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Copyright (C), 2019-2019
 * FileName: JavaPongActor
 * Author:   s·D·bs
 * Date:     2019/5/29 10:37
 * Description: 简单实现ping pong
 * Motto: 0.45%
 */

public class JavaPingActor extends AbstractActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    static public Props props() {
        return Props.create(JavaPingActor.class, JavaPingActor::new);
    }

    @RequiredArgsConstructor(staticName = "of")
    static public class Entity {

        @NonNull
        public final String message;
    }

    @Override
    public void preStart() throws Exception {
        log.info("爸爸开始了！");
    }


    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Entity.class,
                        s -> {
                            if (s.message.equalsIgnoreCase("ping")) {
                                log.info("ping收到:[{}]", s);

                                getSelf().tell("pong", ActorRef.noSender());
                                log.info("getSelf()-hashcode:[{}]",getSelf().hashCode());
                            }
                        }
                ).matchEquals("pong", s -> {
                    log.info("pong收到:[{}]", s);
                })
                .build();
    }
}
