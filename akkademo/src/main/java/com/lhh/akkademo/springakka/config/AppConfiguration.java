package com.lhh.akkademo.springakka.config;

import akka.actor.ActorSystem;
import com.lhh.akkademo.springakka.di.SpringExtension;
import com.typesafe.config.ConfigFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.typesafe.config.Config;

import static com.lhh.akkademo.springakka.di.SpringExtension.SPRING_EXTENSION_PROVIDER;

/**
 * Copyright (C), 2019-2019
 * FileName: AppConfiguration
 * Author:   s·D·bs
 * Date:     2019/5/24 14:19
 * Description: app的配置文件
 * Motto: 0.45%
 */

@Configuration
public class AppConfiguration {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private SpringExtension springExtension;

    @Bean
    public ActorSystem actorSystem() {
        ActorSystem actorSystem = ActorSystem.create("demo-actor-system", akkaConfiguration());
        SPRING_EXTENSION_PROVIDER.get(actorSystem).initialize(applicationContext);
//        springExtension.initialize(applicationContext);
        return actorSystem;
    }

    @Bean
    public Config akkaConfiguration() {
        return ConfigFactory.load();
    }
}
