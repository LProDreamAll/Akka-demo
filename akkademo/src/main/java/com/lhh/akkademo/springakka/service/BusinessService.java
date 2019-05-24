package com.lhh.akkademo.springakka.service;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Copyright (C), 2019-2019
 * FileName: BusinessService
 * Author:   s·D·bs
 * Date:     2019/5/24 14:18
 * Description: 业务service
 * Motto: 0.45%
 */
@Slf4j
@Service
public class BusinessService {

    public void perform(Object o) {
        log.info("Perform: {}", o);
    }

}
