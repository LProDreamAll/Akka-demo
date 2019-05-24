package lhh.day03.watch;

import akka.actor.ActorRef;
import akka.actor.Terminated;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * Copyright (C), 2019-2019
 * FileName: WatchActor
 * Author:   s·D·bs
 * Date:     2019/5/23 9:43
 * Description: undetermined
 * Motto: 0.45%
 */
public class WatchActor extends UntypedActor {

    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    /**
     * @return WatchActor
     * @Author s·D·bs
     * @Description //监听一个actor
     * @Date 10:22 2019/5/23
     * @Param [actorRef]
     */
    public WatchActor(ActorRef actorRef) {
        getContext().watch(actorRef);
    }

    @Override
    public void onReceive(Object msg) throws Exception {
        if (msg instanceof Terminated) {
            log.error(((Terminated) msg).getActor().path() + " has Terminated. now shutdown the system");
            getContext().system().shutdown();
        } else {
            unhandled(msg);
        }
    }
}
