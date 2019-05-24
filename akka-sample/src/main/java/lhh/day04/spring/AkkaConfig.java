package com.lhh.spring;

import akka.actor.ActorSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Copyright (C), 2019-2019
 * FileName: AkkaConfig
 * Author:   s·D·bs
 * Date:     2019/5/23 15:42
 * Description: Akka配置类
 * Motto: 0.45%
 */
@Configuration
public class AkkaConfig {

    private final ApplicationContext context;

    @Autowired
    public AkkaConfig(ApplicationContext context) {
        this.context = context;
    }

    @Bean
    public ActorSystem createSystem() {
        ActorSystem system = ActorSystem.create("my-system");
        SpringExtProvider.getInstance().get(system).init(context);
        return system;
    }

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("ReactiveEnterprise");

    }
}
