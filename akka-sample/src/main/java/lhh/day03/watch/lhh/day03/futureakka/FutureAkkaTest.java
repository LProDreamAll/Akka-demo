package lhh.day03.watch.lhh.day03.futureakka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.PoisonPill;
import akka.actor.Props;
import akka.pattern.Patterns;
import com.typesafe.config.ConfigFactory;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

/**
 * Copyright (C), 2019-2019
 * FileName: FutureAkkaTest
 * Author:   s·D·bs
 * Date:     2019/5/23 16:51
 * Description: FutureAkkaTest
 * Motto: 0.45%
 */
public class FutureAkkaTest {

    public static void main(String[] args) throws Exception {
        ActorSystem system = ActorSystem.create("strategy", ConfigFactory.load("akka.config"));
        ActorRef printActor = system.actorOf(Props.create(PrintActor.class), "PrintActor");
        ActorRef workerActor = system.actorOf(Props.create(WorkerActor.class), "WorkerActor");

        //等待着future返回
        Future<Object> future  = Patterns.ask(workerActor, 5, 1000);
        int result = (int) Await.result(future, Duration.create(3, TimeUnit.SECONDS));
        System.out.println("result:" + result);

        //不等待返回值，直接重定向到其他actor，有返回值来的时候将会重定向到printActor
        Future<Object> future1 = Patterns.ask(workerActor, 8, 1000);
        Patterns.pipe(future1, system.dispatcher()).to(printActor);
        workerActor.tell(PoisonPill.getInstance(), ActorRef.noSender());
    }
}
