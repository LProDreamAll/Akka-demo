package fakepanshi.controller;

import fakepanshi.service.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Copyright (C), 2019-2019
 * FileName: HelloController
 * Author:   s·D·bs
 * Date:     2019/6/11 10:05
 * Description: 自定义spring线程池处理异步业务
 * Motto: 0.45%
 */
@RestController
@Slf4j
public class HelloController {


    @Autowired
    private AsyncService asyncService;

    @RequestMapping("/")
    public String submit() {
        log.info("start submit");
        //调用service层的任务
        asyncService.executeAsync();
        log.info("end submit");
        return "success";
    }
}
