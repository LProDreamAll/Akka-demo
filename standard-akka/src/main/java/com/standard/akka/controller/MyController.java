package com.standard.akka.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Copyright (C), 2019-2019
 * FileName: MyController
 * Author:   s·D·bs
 * Date:     2019/5/25 10:09
 * Description: 测试接口
 * Motto: 0.45%
 */
@RestController
public class MyController {

    @RequestMapping(method = RequestMethod.GET, value = "/dear/sdbs")
    public String he() {
        return "I love y";
    }
}
