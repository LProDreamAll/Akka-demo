package com.standard.akka.practice;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.testkit.TestActorRef;
import akka.util.Timeout;
import com.standard.akka.myactor.part1.Greeter;
import com.standard.akka.myactor.part1.Printer;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.FiniteDuration;

import static scala.compat.java8.FutureConverters.toJava;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

import static akka.pattern.Patterns.ask;
import static org.junit.Assert.assertEquals;


/**
 * Copyright (C), 2019-2019
 * FileName: AkkademyDbTest
 * Author:   s·D·bs
 * Date:     2019/5/29 10:16
 * Description: AkkademyDb测试
 * Motto: 0.45%
 */
@Slf4j
public class AkkademyDbTest {
    ActorSystem system = ActorSystem.create("system");

    /**
     * TestActorRef
     * TestActorRef 是通用的，有两个功能：首先，它提供的 Actor API
     * 是同步的，这样我们就不需要在测试中考虑并发的问题；其次，我们可以通过
     * TestActorRef 访问其背后的 Actor 对象。
     */
    @Test
    public void itShouldPlaceKeyValueFromSetMessageIntoMap() {
        TestActorRef<AkkademyDb> actorRef = TestActorRef.create(system, Props.create(AkkademyDb.class));
        actorRef.tell(SetRequest.of("key", "value"), ActorRef.noSender());
        AkkademyDb akkademyDb = actorRef.underlyingActor();
        log.info("akkademyDb map :[{}]", akkademyDb.map.get("key"));
        assertEquals(akkademyDb.map.get("key"), "value");
    }


}
