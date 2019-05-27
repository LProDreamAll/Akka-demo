package com.standard.akka.sample.current;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * Copyright (C), 2019-2019
 * FileName: MyActor
 * Author:   s·D·bs
 * Date:     2019/5/27 11:33
 * Description:
 * Motto: 0.45%
 */
public class MyActor extends AbstractActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(
                String.class,
                s -> {
                    log.info("Received String message: {}", s);
                })
                .matchAny(o -> log.info("received unknown message"))
                .build();
    }

    static class DemoActor extends AbstractActor {

        private final Integer magicNumber;

        static Props props(Integer magicNumber) {
            // You need to specify the actual type of the returned actor
            // since Java 8 lambdas have some runtime type information erased
            return Props.create(DemoActor.class, () -> new DemoActor(magicNumber));
        }

        public DemoActor(Integer magicNumber) {
            this.magicNumber = magicNumber;
        }

        @Override
        public Receive createReceive() {
            return receiveBuilder()
                    .match(
                            Integer.class,
                            i -> {
                                getSender().tell(i + magicNumber, getSelf());
                            })
                    .build();
        }



    }
    static class SomeOtherActor extends AbstractActor {
        // Props(new DemoActor(42)) would not be safe
        ActorRef demoActor = getContext().actorOf(DemoActor.props(42), "demo");

        @Override
        public Receive createReceive() {
            return null;
        }
        // ...
    }
}

