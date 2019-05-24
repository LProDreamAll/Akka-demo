package com.lhh.spring;

import akka.actor.AbstractExtensionId;
import akka.actor.ExtendedActorSystem;

/**
 * Copyright (C), 2019-2019
 * FileName: SpringExtProvider
 * Author:   s·D·bs
 * Date:     2019/5/23 15:52
 * Description: 我们可以在ActorSystem范围内创建并查找SpringExt
 * Motto: 0.45%
 */
public class SpringExtProvider extends AbstractExtensionId<SpringExt> {

    private static SpringExtProvider provider = new SpringExtProvider();

    public static SpringExtProvider getInstance() {
        return provider;
    }

    @Override
    public SpringExt createExtension(ExtendedActorSystem system) {
        return new SpringExt();
    }


}
