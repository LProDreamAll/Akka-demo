package com.lhh.akkademo.springakka.di;

import akka.actor.Actor;
import akka.actor.IndirectActorProducer;
import org.springframework.context.ApplicationContext;

/**
 * Copyright (C), 2019-2019
 * FileName: SpringActorProducer
 * Author:   s·D·bs
 * Date:     2019/5/24 17:18
 * Description: SpringActorProducer的功能就是从ApplicationContext中以 Spring Bean 的形式通过名称来创建 actors。
 * Motto: 0.45%
 */
public class SpringActorProducer implements IndirectActorProducer {

    /**
     * IndirectActorProducer 定义了一个actor创建策略类，它偏离了通常的默认值，只是反射地实例化了 actor 子类。
     * 它可以用于允许依赖注入框架来确定实际的 actor 类以及如何实例化它。
     */

    final private ApplicationContext applicationContext;
    final private String actorBeanName;
    final private Object[] args;

    public SpringActorProducer(ApplicationContext applicationContext, String actorBeanName, Object... args) {
        this.applicationContext = applicationContext;
        this.actorBeanName = actorBeanName;
        this.args = args;
    }

    /**
     * @return akka.actor.Actor
     * @Author s·D·bs
     * @Description //这个工厂方法必须在每次调用时生成一个新的actor实例。不允许多次返回同一个实例。
     * @Date 17:23 2019/5/24
     * @Param []
     */
    @Override
    public Actor produce() {
        if (args == null) {
            return (Actor) applicationContext.getBean(actorBeanName);
        } else {
            return (Actor) applicationContext.getBean(actorBeanName, args);
        }
    }

    /**
     * @return java.lang.Class<? extends akka.actor.Actor>
     * @Author s·D·bs
     * @Description 该方法被道具用来确定将要创建的参与者的类型。这意味着将创建这个IndirectActorProducer的实例，
     * 以便在调用Props.actorClass时调用该方法;应该注意的是，这样的调用可以在实际的actor实例化之前的actor设置期间执行，
     * 并且为调用actorClass而创建的实例并不一定要在以后重用以生成actor。
     * @Date 17:25 2019/5/24
     * @Param []
     */
    @Override
    public Class<? extends Actor> actorClass() {
        return (Class<? extends Actor>) applicationContext.getType(actorBeanName);
    }
}
