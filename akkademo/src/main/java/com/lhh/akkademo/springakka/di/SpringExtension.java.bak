package com.lhh.akkademo.springakka.di;

import akka.actor.Extension;
import akka.actor.Props;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Copyright (C), 2019-2019
 * FileName: SpringExtension
 * Author:   s·D·bs
 * Date:     2019/5/24 14:18
 * Description: spring的扩展
 * Motto: 0.45%
 */
@Component //需要引入spring的BeanFactory中
public class SpringExtension implements Extension {

    private ApplicationContext applicationContext;

    public void initialize(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * @return akka.actor.Props
     * @Author s·D·bs
     * @Description //Props是一个ActorRef配置对象，它是不可变的，所以它是线程安全的，并且完全可共享。
     * @Date 14:24 2019/5/24
     * @Param [actorBeanName]
     */
    public Props props(String actorBeanName) {

        return Props.create(SpringActorProducer.class, applicationContext, actorBeanName);
    }

    public Props props(String actorBeanName, Object... args) {
        return Props.create(SpringActorProducer.class, applicationContext, actorBeanName, args);
    }
}
