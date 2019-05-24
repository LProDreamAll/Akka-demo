/**
 * Copyright (C), 2019-2019
 * FileName: Test02
 * Author:   s·D·bs
 * Date:     2019/5/14 22:10
 * Description: 测试
 * Motto: 0.45%
 *
 * @create 2019/5/14
 * @since 1.0.0
 */


package lhh.day03.watch.lhh.day02;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.ConfigFactory;

public class Test02 {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("Hello", ConfigFactory.load("akka.config"));
        ActorRef a = system.actorOf(Props.create(HelloWorld.class), "helloWorld");
        System.out.println(a.path());
    }
}
