package com.lhh.akkademo.springakka.service;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.lhh.akkademo.springakka.di.SpringExtension;
import com.lhh.akkademo.springakka.modle.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Copyright (C), 2019-2019
 * FileName: CompletableFutureService
 * Author:   s·D·bs
 * Date:     2019/5/24 14:59
 * Description: Complete
 * Motto: 0.45%
 */
@Service
@Slf4j
public class CompletableFutureService {

    @Autowired
    private ActorSystem actorSystem;
    @Autowired
    private SpringExtension springExtension;

    private static final Long DEFERRED_RESULT_TIMEOUT = 1000L;

    private AtomicLong id = new AtomicLong(0);

    public CompletableFuture<Message> get(String payload, Long id) {
        CompletableFuture<Message> future = new CompletableFuture<>();
        ActorRef workerActor = actorSystem.actorOf(springExtension.props("workerActor", future), "worker-actor");
        workerActor.tell(new Message(payload, id, workerActor.hashCode()), null);
        return future;
    }

    public DeferredResult<Message> getAsyncNonBlocking() {
        DeferredResult<Message> deferred = new DeferredResult<>(DEFERRED_RESULT_TIMEOUT);
        log.info(" CompletableFutureService id:{},url:{}", id, "async-non-blocking" + id);
        CompletableFuture<Message> future = this.get("async-non-blocking" + id, id.getAndIncrement());
        future.whenComplete((result, error) -> {
            if (error != null) {
                deferred.setErrorResult(error);
            } else {
                deferred.setResult(result);
            }
        });
        return deferred;
    }
}
