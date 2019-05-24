package com.lhh.akkademo.springakka.di;

import akka.actor.AbstractExtensionId;
import akka.actor.ExtendedActorSystem;
import akka.actor.Extension;
import akka.actor.Props;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Copyright (C), 2019-2019
 * FileName: SpringExtension
 * Author:   s·D·bs
 * Date:     2019/5/24 17:28
 * Description: actor的扩展
 * 由于扩展是hook到Akka自身的，所以扩展的实现者需要保证自己扩展的线程安全性。
 * Motto: 0.45%
 */
@Component
public class SpringExtension extends AbstractExtensionId<SpringExtension.SpringExt> {

    public static final SpringExtension SPRING_EXTENSION_PROVIDER = new SpringExtension();

    /**
     * @return spring.akka.batter.config.SpringExtension.SpringExt
     * @Author s·D·bs
     * @Description //在注册 extension 的时候被调用。这个类应该是增加一个私有的构造函数，从而表明该类是一个单例。
     * @Date 17:33 2019/5/24
     * @Param [system]
     */
    @Override
    public SpringExt createExtension(ExtendedActorSystem system) {
        return new SpringExt();
    }

    public static class SpringExt implements Extension {

        private volatile ApplicationContext applicationContext;

        public void initialize(ApplicationContext applicationContext) {
            this.applicationContext = applicationContext;
        }

        /**
         * @return akka.actor.Props
         * @Author s·D·bs
         * @Description Props实例是actor的蓝图，也就是说Props用于获取actor。
         * 在这里进行一层抽象
         * @Date 17:31 2019/5/24
         * @Param [actorBeanName]
         */
        public Props props(String actorBeanName) {
            return Props.create(SpringActorProducer.class, applicationContext, actorBeanName);
        }
        /**
         * @return akka.actor.Props
         * @Author s·D·bs
         * @Description Props实例是actor的蓝图，也就是说Props用于获取actor。
         * 在这里进行一层抽象
         * @Date 17:31 2019/5/24
         * @Param [actorBeanName]
         */
        public Props props(String actorBeanName, Object... args) {
            return Props.create(SpringActorProducer.class, applicationContext, actorBeanName,args);
        }
    }
}
