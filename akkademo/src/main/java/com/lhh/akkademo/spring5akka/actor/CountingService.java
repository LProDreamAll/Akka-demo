package com.lhh.akkademo.spring5akka.actor;

import javax.inject.Named;

/**
 * Copyright (C), 2019-2019
 * FileName: CountingService
 * Author:   s·D·bs
 * Date:     2019/5/28 11:03
 * Description:
 * Motto: 0.45%
 */

@Named("CountingService")
public class CountingService {

    public int increment(int count) {
        return count + 1;
    }
}
