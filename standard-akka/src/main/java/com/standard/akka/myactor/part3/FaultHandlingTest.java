package com.standard.akka.myactor.part3;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.Terminated;
import akka.testkit.TestProbe;
import akka.testkit.javadsl.TestKit;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import scala.concurrent.Await;

import static akka.pattern.Patterns.ask;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Copyright (C), 2019-2019
 * FileName: FaultHandlingTest
 * Author:   s·D·bs
 * Date:     2019/5/28 16:21
 * Description:
 * Motto: 0.45%
 */
public class FaultHandlingTest {

    static ActorSystem system;

    scala.concurrent.duration.Duration timeout =
            scala.concurrent.duration.Duration.create(5, SECONDS);

    @Before
    public void start() {
        system = ActorSystem.create("FaultHandlingTest");
    }

    @After
    public void cleanup() {
        TestKit.shutdownActorSystem(system);
        system = null;
    }

    @Test
    public void test_Child() throws Exception {
        Props superprops = Props.create(Supervisor.class);
        ActorRef supervisor = system.actorOf(superprops, "supervisor");
        ActorRef child = (ActorRef) Await.result(ask(supervisor, Props.create(Child.class), 5000), timeout);
        final TestProbe probe = new TestProbe(system);

//        child.tell(42, ActorRef.noSender());
//        assert Await.result(ask(child, "get", 5000), timeout).equals(42);
//        child.tell(new ArithmeticException(), ActorRef.noSender());
//        assert Await.result(ask(child, "get", 5000), timeout).equals(42);
//
//        child.tell(new NullPointerException(), ActorRef.noSender());
//        assert Await.result(ask(child, "get", 5000), timeout).equals(0);

        child = (ActorRef) Await.result(ask(supervisor, Props.create(Child.class), 5000), timeout);
        probe.watch(child);
        assert Await.result(ask(child, "get", 5000), timeout).equals(0);
        child.tell(new Exception(), ActorRef.noSender());//父级actor直接报错
        probe.expectMsgClass(Terminated.class);

    }

    @Test

    public void test_parent() throws Exception {
        Props superprops = Props.create(Supervisor.Supervisor2.class);
        ActorRef supervisor = system.actorOf(superprops);
        ActorRef child = (ActorRef) Await.result(ask(supervisor, Props.create(Child.class), 5000), timeout);
        child.tell(23, ActorRef.noSender());
        assert Await.result(ask(child, "get", 5000), timeout).equals(23);
        child.tell(new Exception(), ActorRef.noSender());
        assert Await.result(ask(child, "get", 5000), timeout).equals(0);
    }
}
