package lhh.day03.watch.lhh.day03.futureakka;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * Copyright (C), 2019-2019
 * FileName: WorkerActor
 * Author:   s·D·bs
 * Date:     2019/5/23 16:50
 * Description: Worker
 * Motto: 0.45%
 */
public class WorkerActor extends UntypedActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    @Override
    public void onReceive(Object message) throws Exception {
        log.info("akka.future.WorkerActor.onReceive:" + message);
        if (message instanceof Integer) {
            Thread.sleep(1000);
            int i = Integer.parseInt(message.toString());
            getSender().tell(i * i, getSelf());
        } else {
            unhandled(message);
        }

    }
}
