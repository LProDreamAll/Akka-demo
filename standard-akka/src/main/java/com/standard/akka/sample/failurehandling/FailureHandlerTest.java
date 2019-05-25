package com.standard.akka.sample.failurehandling;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

/**
 * Copyright (C), 2019-2019
 * FileName: FailureHandlerTest
 * Author:   s·D·bs
 * Date:     2019/5/25 13:40
 * Description: 失败处理
 * Motto: 0.45%
 */
public class FailureHandlerTest {

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("FailureHandlerSystem");
        System.out.println(system);
        ActorRef supervisingActor = system.actorOf(SupervisingActor.props(), "supervising-actor");
        supervisingActor.tell("failChild", ActorRef.noSender());
    }

}
