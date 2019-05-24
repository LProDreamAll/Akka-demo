package com.lhh.akkademo.springakka.controller;

import com.lhh.akkademo.springakka.modle.Message;
import com.lhh.akkademo.springakka.service.CompletableFutureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Copyright (C), 2019-2019
 * FileName: DeferredResultController
 * Author:   s·D·bs
 * Date:     2019/5/24 15:02
 * Description: ControllerTest类
 * Motto: 0.45%
 */
@RestController
@Slf4j
public class DeferredResultController {


    @Autowired
    private CompletableFutureService completableFutureService;

    @RequestMapping("/async-non-blocking")
    public DeferredResult<Message> getAsyncNonBlocking() {
        DeferredResult<Message> asyncNonBlocking = null;
        try {
            asyncNonBlocking = completableFutureService.getAsyncNonBlocking();
        } catch (Exception e) {
            log.info("DeferredResultController getAsyncNonBlocking is error:{}", e);
        }
        return asyncNonBlocking;
    }
}
