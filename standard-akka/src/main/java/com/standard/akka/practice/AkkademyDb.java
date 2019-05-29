package com.standard.akka.practice;

import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Copyright (C), 2019-2019
 * FileName: AkkademyDb
 * Author:   s·D·bs
 * Date:     2019/5/29 10:13
 * Description:
 * Motto: 0.45%
 */
public class AkkademyDb extends AbstractActor {

    protected final LoggingAdapter log = Logging.getLogger(context().system(), this);
    protected final Map<String, Object> map = Maps.newHashMap();

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(SetRequest.class, msg -> {
                    log.info("Received Set request: {}", msg);
                    map.put(msg.getKey(), msg.getValue());
                })
                .matchAny(o -> log.info("received unknown message: {}", o))
                .build();
    }
}
