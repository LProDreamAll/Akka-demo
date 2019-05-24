package lhh.day03.watch;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.PoisonPill;
import akka.actor.Props;
import com.typesafe.config.ConfigFactory;


/**
 * Copyright (C), 2019-2019
 * FileName: test
 * Author:   s·D·bs
 * Date:     2019/5/23 9:41
 * Description:
 * Motto: 0.45%
 */
public class WatchTest {

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("hello", ConfigFactory.load("akka.config"));
        ActorRef myWork = system.actorOf(Props.create(MyWork.class), "MyWork");
        //设置watchActor监控myWork 执行这个方法才进入lhh.day03.MyWork.preStart的方法
        ActorRef watchActor = system.actorOf(Props.create(WatchActor.class, myWork), "WatchActor");
        myWork.tell(MyWork.Msg.WORKING, ActorRef.noSender());//调用lhh.day03.MyWork.onReceive方法
        myWork.tell(MyWork.Msg.DONE, ActorRef.noSender());
        //中断myWork 所有方法运行完毕才进入的MyWork类中
        myWork.tell(PoisonPill.getInstance(), ActorRef.noSender()); //执行MyWork方法
    }

}
