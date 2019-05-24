package lhh.day03.watch.lhh.day03.strategy;

import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.ConfigFactory;

/**
 * Copyright (C), 2019-2019
 * FileName: StrategyTest
 * Author:   s·D·bs
 * Date:     2019/5/23 10:58
 * Description: 测试
 * actorSelection方便的选择actor进行消息投递，其支持通配符匹配getContext().actorSelection("/user/worker_*")
 * 直接进行消息投递
 * Motto: 0.45%
 */


public class StrategyTest {

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("strategy", ConfigFactory.load("akka.config"));
        ActorRef superVisor = system.actorOf(Props.create(SuperVisor.class), "SuperVisor");
        superVisor.tell(Props.create(RestartActor.class), ActorRef.noSender());
        //这是akka的路径。restartActor是在SuperVisor中创建的。
        ActorSelection actorSelection = system.actorSelection("akka://strategy/user/SuperVisor/restartActor");
        for (int i = 0; i < 100; i++) {
            actorSelection.tell(RestartActor.Msg.RESTART, ActorRef.noSender());
        }
    }

}
