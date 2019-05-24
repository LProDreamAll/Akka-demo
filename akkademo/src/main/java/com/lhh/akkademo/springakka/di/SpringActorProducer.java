package com.lhh.akkademo.springakka.di;

import akka.actor.Actor;
import akka.actor.IndirectActorProducer;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationContext;

/**
 * Copyright (C), 2019-2019
 * FileName: SpringActorProducer
 * Author:   s·D·bs
 * Date:     2019/5/24 14:18
 * Description: 生产actor
 * Motto: 0.45%
 */

@AllArgsConstructor
public class SpringActorProducer implements IndirectActorProducer {
    final private ApplicationContext applicationContext;
    final private String actorBeanName;
    final private Object[] args;

    @Override
    public Actor produce() {
        if (args == null) {
            return (Actor) applicationContext.getBean(actorBeanName);
        } else {
            return (Actor) applicationContext.getBean(actorBeanName, args);
        }
    }

    @Override
    public Class<? extends Actor> actorClass() {
        return (Class<? extends Actor>) applicationContext.getType(actorBeanName);
    }
}
