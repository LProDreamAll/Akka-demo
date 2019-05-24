package lhh.day03.Inbox;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Inbox;
import akka.actor.Props;
import akka.actor.Terminated;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.typesafe.config.ConfigFactory;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

/**
 * Copyright (C), 2019-2019
 * FileName: InboxTest
 * Author:   s·D·bs
 * Date:     2019/5/23 14:20
 * Description: inbox消息收件箱来给某个actor发消息，并且可以进行交互。
 * Motto: 0.45%
 */
public class InboxTest extends UntypedActor {


    private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    public enum Msg {
        WORKING, DONE, CLOSE;
    }

    @Override
    public void onReceive(Object o) {
        if (o == Msg.WORKING) {
            log.info("i am working.");
        } else if (o == Msg.DONE) {
            log.info("i am done");
        } else if (o == Msg.CLOSE) {
            log.info("i am close.");
            getSender().tell(Msg.CLOSE, getSelf());//告诉消息发送者我要关闭了。
            getContext().stop(getSelf());//关闭自己
        } else {
            unhandled(o);
        }
    }

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("inbox", ConfigFactory.load("akka.conf"));
        ActorRef inboxTest = system.actorOf(Props.create(InboxTest.class), "InboxTest");
        //akka自带的邮件盒子
        Inbox inbox = Inbox.create(system);
        //监听一个actor
        inbox.watch(inboxTest);
        //通过inbox来发送消息
        inbox.send(inboxTest, Msg.WORKING);
        inbox.send(inboxTest, Msg.DONE);
        inbox.send(inboxTest, Msg.CLOSE);
        while (true) {
            Object receive = inbox.receive(Duration.create(1, TimeUnit.SECONDS));
            if (receive == Msg.CLOSE) {//收到的inbox的消息
                System.out.println("inboxTextActor is closing");
            } else if (receive instanceof Terminated) {//中断 ，和线程一个概念
                System.out.println("inboxTextActor is closed");
                system.shutdown();
                break;
            } else {
                System.out.println("receive:" + receive);
            }
        }

    }
}
