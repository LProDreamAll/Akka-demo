package lhh.day03.watch;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

import static lhh.day03.watch.MyWork.Msg.CLOSE;
import static lhh.day03.watch.MyWork.Msg.DONE;
import static lhh.day03.watch.MyWork.Msg.WORKING;

/**
 * Copyright (C), 2019-2019
 * FileName: MyWork
 * Author:   s·D·bs
 * Date:     2019/5/23 9:42
 * Description: undetermined
 * Motto: 0.45%
 */
public class MyWork extends UntypedActor {

    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    public static enum Msg {
        WORKING, DONE, CLOSE;
    }

    @Override
    public void preStart() throws Exception {
        log.info("myWork pre starting");
    }

    @Override
    public void postStop() throws Exception {
        log.info("myWork post Stopping");
    }

    @Override
    public void onReceive(Object msg) throws Exception {
        try {
            if (WORKING.equals(msg)) {
                log.info("i am  working");
            } else if (DONE.equals(msg)) {
                log.info("stop working");
            } else if (CLOSE.equals(msg)) {
                log.info("stop close");
                getSender().tell(CLOSE, getSelf());
                getContext().stop(getSelf());
            } else {
                unhandled(msg);
            }
        } catch (Exception e) {
            log.error("onReceive error :{}", e);
        }
    }
}
