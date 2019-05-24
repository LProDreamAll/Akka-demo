

package lhh.day03.watch.lhh.day01;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

/**
 * Copyright (C), 2019-2019
 * FileName: HelloWorld
 * Author:   s·D·bs
 * Date:     2019/5/14 21:19
 * Description: actor测试
 * Motto: 0.45%
 *
 * @create 2019/5/14
 * @since 1.0.0
 */

public class HelloWorld extends UntypedActor {

    @Override
    public void preStart() throws Exception {
        //发送前做的事情
        System.out.println("发送前");
        //调用Greeter发送greeter
        final ActorRef greeter = getContext().actorOf(Props.create(Greeter.class), "greeter");
        greeter.tell(Greeter.Msg.GREET, getSelf());
    }

    public void onReceive(Object msg) throws Exception {
        if (msg == Greeter.Msg.DONE) {
            System.out.println("停止");
            // when the greeter is done, stop this actor and with it the application
            getContext().stop(getSelf());
        } else
            unhandled(msg);
    }
}
