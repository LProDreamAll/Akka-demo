package com.standard.akka.sample.example.part2;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

import java.io.IOException;

/**
 * Copyright (C), 2019-2019
 * FileName: IotSupervisorTest
 * Author:   s·D·bs
 * Date:     2019/5/27 14:25
 * Description: IotSupervisor的测试类
 * Motto: 0.45%
 */
public class IotSupervisorTest {

    public static void main(String[] args) throws IOException {
        ActorSystem system = ActorSystem.create("iot-system");
        try {
            // Create top level supervisor
            ActorRef supervisor = system.actorOf(IotSupervisor.props(), "iot-supervisor");
            System.out.println("Press ENTER to exit the system");
            System.in.read();
        } finally {
            system.terminate();
        }

    }

}
