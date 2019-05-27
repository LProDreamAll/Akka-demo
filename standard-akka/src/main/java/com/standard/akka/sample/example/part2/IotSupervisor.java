package com.standard.akka.sample.example.part2;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * Copyright (C), 2019-2019
 * FileName: IotSupervisor
 * Author:   s·D·bs
 * Date:     2019/5/27 14:23
 * Description:
 * Motto: 0.45%
 */
public class IotSupervisor extends AbstractActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    public static Props props() {
        return Props.create(IotSupervisor.class, IotSupervisor::new);
    }

    @Override
    public void preStart() {
        log.info("IoT Application started");
    }

    @Override
    public void postStop() {
        log.info("IoT Application stopped");
    }

    /**
     * @return akka.actor.AbstractActor.Receive
     * @Author s·D·bs
     * @Description //并不需要处理任何内容
     * @Date 14:24 2019/5/27
     * @Param []
     */
    @Override
    public Receive createReceive() {
        return receiveBuilder().build();
    }
}
