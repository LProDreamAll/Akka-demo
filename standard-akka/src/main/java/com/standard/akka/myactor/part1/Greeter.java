package com.standard.akka.myactor.part1;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import com.standard.akka.myactor.part1.Printer.Greeting;
/**
 * Copyright (C), 2019-2019
 * FileName: Greeter
 * Author:   s·D·bs
 * Date:     2019/5/25 11:20
 * Description: Greeter
 * Motto: 0.45%
 */
@RequiredArgsConstructor
public class Greeter extends AbstractActor {
    @NonNull
    private final String message;
    @NonNull
    private final ActorRef printerActor;
    private String greeting = "";

    static public Props props(String message, ActorRef printerActor) {
        return Props.create(Greeter.class, () -> new Greeter(message, printerActor));
    }

    //#greeter-messages
    @AllArgsConstructor
    static public class WhoToGreet {
        public final String who;
    }

    @NoArgsConstructor
    static public class Greet {

    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(WhoToGreet.class, wtg -> {
                    this.greeting = message + ", " + wtg.who;
                })
                .match(Greet.class, x -> {
                    printerActor.tell(new Greeting(greeting), getSelf());
                })
                .build();
    }
}
