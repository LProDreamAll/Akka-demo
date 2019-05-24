/**
 * Copyright (C), 2019-2019
 * FileName: HelloWorld
 * Author:   s·D·bs
 * Date:     2019/5/14 22:09
 * Description: HelloWorld
 * Motto: 0.45%
 *
 * @create 2019/5/14
 * @since 1.0.0
 */


package lhh.day02;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import com.alibaba.fastjson.JSONObject;

import java.util.Arrays;

public class HelloWorld  extends UntypedActor {

    ActorRef greeter;
    @Override
    public void preStart() {
        // create the greeter actor
        greeter = getContext().actorOf(Props.create(Greeter.class), "greeter");
        System.out.println("Greeter actor path：" + greeter.path());
        // tell it to perform the greeting
        greeter.tell(new Message(2, Arrays.asList("2", "dsf")), getSelf());
    }

    public void onReceive(Object msg) throws Exception {
        try {
            System.out.println("HelloWorld收到的数据为：" + JSONObject.toJSONString(msg));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
