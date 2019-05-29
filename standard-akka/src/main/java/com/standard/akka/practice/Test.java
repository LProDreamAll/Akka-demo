package com.standard.akka.practice;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright (C), 2019-2019
 * FileName: Test
 * Author:   s·D·bs
 * Date:     2019/5/29 14:33
 * Description:
 * Motto: 0.45%
 */
@Slf4j
public class Test {

    public static void main(String[] args) {
        final ActorSystem system = ActorSystem.create("PingSystem");
        final ActorRef javaPingActor =
                system.actorOf(JavaPingActor.props(), "JavaPingActor");
        log.info("javaPingActor-hashcode:[{}]",javaPingActor.hashCode());
        javaPingActor.tell(JavaPingActor.Entity.of("ping"),ActorRef.noSender());
    }

}
