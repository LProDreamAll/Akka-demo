package com.lhh.akkademo.spring5akka.config;

import akka.actor.Actor;
import akka.actor.IndirectActorProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;

/**
 * Copyright (C), 2019-2019
 * FileName: SpringActorProducer
 * Author:   s·D·bs
 * Date:     2019/5/28 10:53
 * Description: SpringActorProducer
 * Motto: 0.45%
 */
@RequiredArgsConstructor(staticName = "of")
public class SpringActorProducer implements IndirectActorProducer {

    final ApplicationContext applicationContext;
    final String actorBeanName;

    @Override
    public Actor produce() {
        return (Actor) applicationContext.getBean(actorBeanName);
    }

    @Override
    public Class<? extends Actor> actorClass() {
        return (Class<? extends Actor>) applicationContext.getType(actorBeanName);
    }
}
