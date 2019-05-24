package com.lhh.spring;

import akka.actor.AbstractActor;
import akka.actor.IllegalActorStateException;
import akka.actor.UntypedActor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import scala.PartialFunction;
import scala.runtime.BoxedUnit;

/**
 * Copyright (C), 2019-2019
 * FileName: TestActor
 * Author:   s·D·bs
 * Date:     2019/5/23 15:56
 * Description: 来验证集成是否成功
 * Motto: 0.45%
 */

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TestActor extends UntypedActor {

    @Override
    public void onReceive(Object message) throws Exception {
        System.out.println("接受到消息：" + message);
    }
}
