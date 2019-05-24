//package com.lhh.akkademo.springakka.config;
//
//import akka.actor.ActorRef;
//import akka.actor.ActorSystem;
//import akka.pattern.Patterns;
//import akka.util.Timeout;
//import com.lhh.akkademo.springakka.actor.WorkerActor;
//import com.lhh.akkademo.springakka.di.SpringExtension;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//import scala.concurrent.Await;
//import scala.concurrent.Future;
//import scala.concurrent.duration.Duration;
//import scala.concurrent.duration.FiniteDuration;
//
//import java.util.concurrent.TimeUnit;
//
///**
// * Copyright (C), 2019-2019
// * FileName: Runner
// * Author:   s·D·bs
// * Date:     2019/5/24 14:20
// * Description: spring配置文件依赖类
// * Motto: 0.45%
// */
//
//@Component
//@Slf4j
//public class Runner implements CommandLineRunner {
//
//    @Autowired
//    private ActorSystem actorSystem;
//
//    @Autowired
//    private SpringExtension springExtension;
//
//    @Override
//    public void run(String... args) throws Exception {
//        try {
//            ActorRef workerActor = actorSystem.actorOf(springExtension.props("workerActor"), "worker-actor");
//            workerActor.tell(new WorkerActor.Request(), null);
//            workerActor.tell(new WorkerActor.Request(), null);
//            workerActor.tell(new WorkerActor.Request(), null);
//            FiniteDuration duration = FiniteDuration.create(1, TimeUnit.SECONDS);
//            Future<Object> awaitable = Patterns.ask(workerActor, new WorkerActor.Response(), Timeout.durationToTimeout(duration));
//            log.info("Response: " + Await.result(awaitable, duration));
//        } finally {
//            actorSystem.terminate();
//            Await.result(actorSystem.whenTerminated(), Duration.Inf());
//        }
//    }
//}
