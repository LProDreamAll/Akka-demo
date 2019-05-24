package lhh.day03.watch.lhh.spring;

import akka.actor.Actor;
import akka.actor.IndirectActorProducer;
import org.springframework.context.ApplicationContext;

/**
 * Copyright (C), 2019-2019
 * FileName: DIProducer
 * Author:   s·D·bs
 * Date:     2019/5/23 15:48
 * Description: 生产Actor
 * Motto: 0.45%
 */
public class DIProducer implements IndirectActorProducer {

    private ApplicationContext context;
    private String beanName;

    public DIProducer(ApplicationContext context, String beanName) {
        this.context = context;
        this.beanName = beanName;
    }

    @Override
    public Actor produce() {
        return (Actor) context.getBean(beanName);
    }

    @Override
    public Class<? extends Actor> actorClass() {
        return (Class<? extends Actor>) context.getType(beanName);
    }
}
