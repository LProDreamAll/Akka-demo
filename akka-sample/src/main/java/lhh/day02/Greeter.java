package lhh.day02;

import akka.actor.UntypedActor;
import com.alibaba.fastjson.JSONObject;

/**
 * Copyright (C), 2019-2019
 * FileName: Greeter
 * Author:   s·D·bs
 * Date:     2019/5/14 22:08
 * Description: wenhou
 * Motto: 0.45%
 *
 * @create 2019/5/14
 * @since 1.0.0
 */
public class Greeter extends UntypedActor {

    public void onReceive(Object msg) throws Exception {
        try {
            System.out.println("Greeter收到的数据为：" + JSONObject.toJSONString(msg));
            getSender().tell("Greeter工作完成。", getSelf());//给发送至发送信息.
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
