package com.standard.akka.sample.example.part1;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

import java.io.IOException;

/**
 * Copyright (C), 2019-2019
 * FileName: ActorHierarchyExperiments
 * Author:   s·D·bs
 * Date:     2019/5/25 11:45
 * Description: Actor[akka://testSystem/user/first-actor#624891780]
 * 默认路径格式  akka://testSystem/user/
 * Motto: 0.45%
 */
public class ActorHierarchyExperiments {

    /**
     * First: Actor[akka://testSystem/user/first-actor#-2068173602]
     * Second: Actor[akka://testSystem/user/first-actor/second-actor#-613732405]
     */
    public static void main(String[] args) throws IOException {
        ActorSystem system = ActorSystem.create("testSystem");
        ActorRef firstRef = system.actorOf(PrintMyActorRefActor.props(), "first-actor");
        System.out.println("First: " + firstRef);
        firstRef.tell("print it", ActorRef.noSender());

        System.out.println(">>> Press ENTER to exit <<<");
        try {
            System.in.read();
        } finally {
            system.terminate();
        }
    }
}
