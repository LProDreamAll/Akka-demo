package lhh.day03.agent;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Inbox;
import akka.actor.Props;
import akka.actor.Terminated;
import akka.actor.UntypedActor;
import akka.agent.Agent;
import akka.dispatch.ExecutionContexts;
import akka.dispatch.Futures;
import akka.dispatch.Mapper;
import akka.dispatch.OnComplete;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.typesafe.config.ConfigFactory;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;


/**
 * Copyright (C), 2019-2019
 * FileName: AgentTest
 * Author:   s·D·bs
 * Date:     2019/5/23 17:39
 * Description: AgentTest
 * 我们知道在JDK中，编写多线程代码时要谨慎处理临界区的数据，可以加锁或者使用JDK自带的CAS库：atomic相关包。
 * <p>
 * 那么在akka中怎么处理呢？akka给我们提供了一个agent。可以使用agent来实现共享变量的安全处理。
 * <p>
 * 下面示例为10个actor并发累加countAgent变量。每个累加10000次。如果正确的话，最终结果应该是10W。
 * <p>
 * Motto: 0.45%
 */
public class AgentTest extends UntypedActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);
    public static Agent<Integer> countAgent = Agent.create(0, ExecutionContexts.global());
    public static ConcurrentLinkedQueue<Future<Integer>> queue = new ConcurrentLinkedQueue<>();
    public static CountDownLatch latch = new CountDownLatch(10);


    @Override
    public void onReceive(Object o) throws Exception {
        if (o instanceof Integer) {
            for (int i = 0; i < 10000; i++) {
                Future<Integer> future = countAgent.alter(new Mapper<Integer, Integer>() {
                    @Override
                    public Integer apply(Integer parameter) {
                        return parameter + 1;
                    }
                });
                queue.add(future);
            }
            //完成任务，关闭自己
            getContext().stop(getSelf());
        } else {
            unhandled(o);
        }
    }

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("inbox", ConfigFactory.load("akka.conf"));
        ActorRef[] actorRefs = new ActorRef[10];
        for (int i = 0; i < 10; i++) {
            actorRefs[i] = system.actorOf(Props.create(AgentTest.class), "AgentTest" + i);
        }

        Inbox inbox = Inbox.create(system);
        for (ActorRef ref : actorRefs) {
            inbox.send(ref, 1);
            inbox.watch(ref);
        }

        System.out.println("countAgent 1:" + countAgent.get());
        //等待所有actor执行完毕
        int closeCount = 0;
        while (true) {
            Object o = inbox.receive(Duration.create(1, TimeUnit.SECONDS));
            if (o instanceof Terminated) {
                closeCount++;
                if (closeCount == actorRefs.length) {
                    break;
                }
            } else {
                System.out.println("o:" + o);
            }
        }

        System.out.println("countAgent 2:" + countAgent.get());

        //等待所有累加线程完成
        Futures.sequence(queue, system.dispatcher()).onComplete(new OnComplete<Iterable<Integer>>() {
            @Override
            public void onComplete(Throwable throwable, Iterable<Integer> integers) {
                System.out.println("countAgent 3:" + countAgent.get());
                system.shutdown();
            }
        }, system.dispatcher());

    }
}
