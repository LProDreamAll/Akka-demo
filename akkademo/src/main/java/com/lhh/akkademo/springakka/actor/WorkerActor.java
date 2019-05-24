package com.lhh.akkademo.springakka.actor;

import akka.actor.UntypedActor;
import com.lhh.akkademo.springakka.modle.Message;
import com.lhh.akkademo.springakka.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

/**
 * Copyright (C), 2019-2019
 * FileName: WorkerActor
 * Author:   s·D·bs
 * Date:     2019/5/24 14:16
 * Description: 工作actor
 * Motto: 0.45%
 */

@Component("workerActor")
@Scope("prototype") //每次调用产生一个新的实例.
public class WorkerActor extends UntypedActor {

    @Autowired
    private BusinessService businessService;

    final private CompletableFuture<Message> future;

    public WorkerActor(CompletableFuture<Message> future) {
        this.future = future;
    }

    @Override
    public void onReceive(Object message) throws Exception {
        businessService.perform(this + " " + message);
        if (message instanceof Message) {
            future.complete((Message) message);
        } else {
            unhandled(message);
        }

        getContext().stop(self());
    }

    public static class Request {

    }

    public static class Response {

    }
}
