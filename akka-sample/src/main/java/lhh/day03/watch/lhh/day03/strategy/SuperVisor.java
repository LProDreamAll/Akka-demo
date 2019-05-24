package lhh.day03.watch.lhh.day03.strategy;

import akka.actor.OneForOneStrategy;
import akka.actor.Props;
import akka.actor.SupervisorStrategy;
import akka.actor.UntypedActor;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

/**
 * Copyright (C), 2019-2019
 * FileName: SuperVisor
 * Author:   s·D·bs
 * Date:     2019/5/23 10:47
 * Description: 监督者和他的监督策略
 * akka监督策略有两种：
 * <p>
 * OneForOneStrategy
 * 只对出问题的子actor进行处理. 这是默认策略
 * AllForOneStrategy
 * 对子actor以及他的所有兄弟actor进行处理
 * Motto: 0.45%
 */

public class SuperVisor extends UntypedActor {

    /**
     * @return akka.actor.SupervisorStrategy
     * @Author s·D·bs
     * @Description //配置自己的监督策略
     * @Date 10:49 2019/5/23
     * @Param []
     */
    @Override
    public SupervisorStrategy supervisorStrategy() {
        //一分钟内重试3次，超过则kill掉actor
        return new OneForOneStrategy(3, Duration.create(1, TimeUnit.MINUTES),
                (throwable) -> {
                    if (throwable instanceof ArithmeticException) {
                        //ArithmeticException是出现异常的运算条件时，抛出此异常。例如，一个整数“除以零”时，抛出此类的一个实例。
                        System.out.println("meet ArithmeticException ,just resume.");
                        return SupervisorStrategy.resume();//继续; 重新开始; 恢复职位;
                    } else if (throwable instanceof NullPointerException) {
                        System.out.println("meet NullPointerException , restart.");
                        return SupervisorStrategy.restart();
                    } else if (throwable instanceof IllegalArgumentException) {
                        System.out.println("meet IllegalArgumentException ,stop.");
                        return SupervisorStrategy.stop();
                    } else {
                        System.out.println("escalate.");
                        //使逐步升级; 使逐步上升; 乘自动梯上升;也就是交给更上层的actor处理。抛出异常
                        return SupervisorStrategy.escalate();
                    }
                }
        );

    }

    public void onReceive(Object message) throws Exception {
        if (message instanceof Props) {
            getContext().actorOf((Props) message, "restartActor");
        } else {
            //actor 调用失败时候调用
            unhandled(message);
        }
    }
}
