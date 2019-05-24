package lhh.day01; /**
 * Copyright (C), 2019-2019
 * FileName: Test
 * Author:   s·D·bs
 * Date:     2019/5/14 21:24
 * Description: 测试类
 * Motto: 0.45%
 *
 * @create 2019/5/14
 * @since 1.0.0
 */




import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import lhh.day01.HelloWorld;

public class Test {
    public static void main(String[] args) {
//        akka.Main.main(new String[] { HelloWorld.class.getName() });
        ActorSystem system = ActorSystem.create("Hello");
        ActorRef a = system.actorOf(Props.create(HelloWorld.class), "helloWorld");
        System.out.println(a.path());
    }
}
