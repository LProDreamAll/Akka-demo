package com.lhh.akkademo.spring5akka.config;

import akka.actor.ActorSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static com.lhh.akkademo.spring5akka.config.SpringExtension.SpringExtProvider;
/**
 * Copyright (C), 2019-2019
 * FileName: AppConfig
 * Author:   s·D·bs
 * Date:     2019/5/28 10:52
 * Description: java配置文件
 * Motto: 0.45%
 */

@Configuration
public class AppConfig {

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public ActorSystem actorSystem() {
        ActorSystem system = ActorSystem.create("AkkaJavaSpring");
        // initialize the application context in the Akka Spring Extension
        SpringExtProvider.get(system).initialize(applicationContext);
        return system;
    }
}
