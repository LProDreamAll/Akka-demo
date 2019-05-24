package lhh.day03.watch.lhh.day03.strategy;

import akka.actor.UntypedActor;
import scala.Option;

/**
 * Copyright (C), 2019-2019
 * FileName: RestartActor
 * Author:   s·D·bs
 * Date:     2019/5/23 10:56
 * Description: restartActor
 * Motto: 0.45%
 */
public class RestartActor extends UntypedActor {

    public enum Msg {
        DONE, RESTART;
    }

    @Override
    public void preStart() throws Exception {
        System.out.println("preStart    hashCode=" + this.hashCode());
    }

    @Override
    public void postStop() throws Exception {
        System.out.println("postStop    hashCode=" + this.hashCode());
    }

    @Override
    public void preRestart(Throwable reason, Option<Object> message) throws Exception {
        System.out.println("preRestart    hashCode=" + this.hashCode());
    }

    @Override
    public void postRestart(Throwable reason) throws Exception {
        super.postRestart(reason);
        System.out.println("postRestart    hashCode=" + this.hashCode());
    }

    public void onReceive(Object o) throws Exception {
        if (o == Msg.DONE) {
            getContext().stop(getSelf());
        } else if (o == Msg.RESTART) {
            // System.out.println(((Object) null).toString());//restart
            double a = 1 / 0;//会resume
        } else {
            unhandled(o);
        }

    }


}
