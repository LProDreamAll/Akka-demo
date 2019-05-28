package com.lhh.akkademo.spring5akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.util.Timeout;
import com.lhh.akkademo.spring5akka.actor.CountingActor.Count;
import com.lhh.akkademo.spring5akka.actor.CountingActor.Get;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.FiniteDuration;

import java.util.concurrent.TimeUnit;

import static akka.pattern.Patterns.ask;
import static com.lhh.akkademo.spring5akka.config.SpringExtension.SpringExtProvider;
import static org.junit.Assert.assertEquals;

/**
 * Copyright (C), 2019-2019
 * FileName: CountingActorTest
 * Author:   s·D·bs
 * Date:     2019/5/28 11:07
 * Description:
 * Motto: 0.45%
 */
@Slf4j
public class CountingActorTest {


    @Test
    public void testSpring() throws Exception {
        AnnotationConfigApplicationContext cx = new AnnotationConfigApplicationContext();
        cx.scan("com.lhh.akkademo.spring5akka");
        cx.refresh();
        ActorSystem system = cx.getBean(ActorSystem.class);
        ActorRef counter = system.actorOf(
                SpringExtProvider.get(system).props("CountingActor"), "counter");
        log.info("counter:[{}]", counter);

        // tell it to count three times
        counter.tell(new Count(), null);
        counter.tell(new Count(), null);
        counter.tell(new Count(), null);

        FiniteDuration duration = FiniteDuration.create(3, TimeUnit.SECONDS);
        Future<Object> result = ask(counter, new Get(),
                Timeout.durationToTimeout(duration));
        assertEquals(3, Await.result(result, duration));

        // shut down the actor system
        system.shutdown();
        system.awaitTermination();
    }
}
