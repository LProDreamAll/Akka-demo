package com.standard.akka.myactor.part1;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import lombok.AllArgsConstructor;

/**
 * Copyright (C), 2019-2019
 * FileName: Printer
 * Author:   s·D·bs
 * Date:     2019/5/25 11:21
 * Description: Printer
 * Motto: 0.45%
 */
public class Printer extends AbstractActor {

    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    static public Props props() {
        return Props.create(Printer.class, Printer::new);
    }

    @AllArgsConstructor
    static public class Greeting {

        public final String message;
    }

    public Printer() {
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Greeting.class, greeting -> {
                    log.info(greeting.message);
                }).build();
    }
}
