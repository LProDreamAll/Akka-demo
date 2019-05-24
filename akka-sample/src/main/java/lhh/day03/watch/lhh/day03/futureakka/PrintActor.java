package lhh.day03.watch.lhh.day03.futureakka;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * Copyright (C), 2019-2019
 * FileName: PrintActor
 * Author:   s·D·bs
 * Date:     2019/5/23 16:50
 * Description: 日志打印
 * Motto: 0.45%
 */

public class PrintActor extends UntypedActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    @Override
    public void onReceive(Object message) throws Exception {
        log.info("akka.future.PrintActor.onReceive:" + message);
        if (message instanceof Integer) {
            log.info("print:" + message);
        } else {
            unhandled(message);
        }
    }
}
