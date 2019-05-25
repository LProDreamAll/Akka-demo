package com.standard.akka.sample.lifecycle;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

/**
 * Copyright (C), 2019-2019
 * FileName: LifecycleClass
 * Author:   s·D·bs
 * Date:     2019/5/25 13:34
 * Description: 生命周期的使用
 * Motto: 0.45%
 */
public class LifecycleTest {

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("lifecycleSystem");
        ActorRef first = system.actorOf(StartStopActor1.props(), "first");
        first.tell("stop", ActorRef.noSender());
    }

}
