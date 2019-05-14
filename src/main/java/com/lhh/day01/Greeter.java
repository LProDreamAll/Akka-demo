/**
 * Copyright (C), 2019-2019
 * FileName: Greeter
 * Author:   s·D·bs
 * Date:     2019/5/14 21:21
 * Description:
 * Motto: 0.45%
 *
 * @create 2019/5/14
 * @since 1.0.0
 */


package com.lhh.day01;

import akka.actor.UntypedActor;

public class Greeter extends UntypedActor {

    public void onReceive(Object msg) throws Exception {
        if (msg == Msg.GREET) {
            System.out.println("Hello World!");
            Thread.sleep(1000);
            getSender().tell(Msg.DONE, getSelf());
        } else
            unhandled(msg);
    }


    public enum Msg {
        GREET, DONE
    }
}
