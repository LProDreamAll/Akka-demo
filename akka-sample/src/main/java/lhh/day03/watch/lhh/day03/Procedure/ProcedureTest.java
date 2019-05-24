package lhh.day03.watch.lhh.day03.Procedure;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.PoisonPill;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.Procedure;
import com.typesafe.config.ConfigFactory;

/**
 * Copyright (C), 2019-2019
 * FileName: ProcedureTest
 * Author:   s·D·bs
 * Date:     2019/5/23 14:48
 * Description: actor的内置状态
 * <p>
 * 婴儿有两种不同的状态，开心和生气，婴儿有个特点就是好玩，永远不会累，所以让其睡觉婴儿就会生气，让他继续玩就会很高兴。
 * Motto: 0.45%
 */
public class ProcedureTest extends UntypedActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    private void happy(Object o) {
        log.info("i am happy! " + o);
        if (o == Msg.PLAY) {
            getSender().tell("i am alrady happy!!", getSelf());
            log.info("i am alrady happy!!");
        } else if (o == Msg.SLEEP) {
            log.info("i do not like sleep!");
            getContext().become(anger);
        } else {
            unhandled(o);
        }
    }

    private void anger(Object o) {
        log.info("i am angray! " + o);
        if (o == Msg.SLEEP) {
            getSender().tell("i am alrady angray!!", getSelf());
            log.info("i am alrady angray!!");
        } else if (o == Msg.PLAY) {
            log.info("i like play.");
            getContext().become(happy);
        } else {
            unhandled(o);
        }
    }

    public enum Msg {
        PLAY, SLEEP;
    }

    private Procedure<Object> happy = this::happy;

    private Procedure<Object> anger = this::anger;

    @Override
    public void onReceive(Object o) throws Exception {
        log.info("onReceive msg: " + o);
        if (o == Msg.SLEEP) {
            getContext().become(anger);
        } else if (o == Msg.PLAY) {
            getContext().become(happy);
        } else {
            unhandled(o);
        }
    }


    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("strategy", ConfigFactory.load("akka.config"));
        ActorRef procedureTest = system.actorOf(Props.create(ProcedureTest.class), "ProcedureTest");

        procedureTest.tell(Msg.PLAY, ActorRef.noSender());
        procedureTest.tell(Msg.SLEEP, ActorRef.noSender());
        procedureTest.tell(Msg.PLAY, ActorRef.noSender());
        procedureTest.tell(Msg.PLAY, ActorRef.noSender());
        procedureTest.tell(PoisonPill.getInstance(), ActorRef.noSender());

    }
}
