package com.lhh.akkademo.spring5akka.actor;

import akka.actor.UntypedActor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Copyright (C), 2019-2019
 * FileName: CountingActor
 * Author:   s·D·bs
 * Date:     2019/5/28 10:59
 * Description:
 * Motto: 0.45%
 */
@Named("CountingActor")
@Scope("prototype")
@Slf4j
public class CountingActor extends UntypedActor {

    public static class Count {

    }

    public static class Get {

    }

    final CountingService countingService;

    @Inject
    public CountingActor(@Named("CountingService") CountingService countingService) {
        this.countingService = countingService;
    }

    private int count = 0;


    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof Count) {
            count = countingService.increment(count);
            log.info("count : [{}]", count);
        } else if (message instanceof Get) {
            getSender().tell(count, getSelf());
        } else {
            unhandled(message);
        }
    }
}
