package com.lhh.my.jedis;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Copyright (C), 2019-2019
 * FileName: MyJedisTest
 * Author:   s·D·bs
 * Date:     2019/6/10 10:04
 * Description: MyJedis测试
 * Motto: 0.45%
 */
@Slf4j
public class MyJedisTest {

    private MyJedis client = new MyJedis("127.0.0.1", 6379);


    @Test
    public void set(){
        client.set("fantj","fantj");
        String result = client.get("fantj");
        log.info("result:[{}]",result);
    }
    @Test
    public void setNx(){
        client.set("fantj","fantj","NX","EX",10000);
        String result = client.get("fantj");
        log.info("result:[{}]",result);
    }
    @Test
    public void append(){
//        client.append("fantj","-2019");
        String fantj = client.get("fantj");
        log.info("fantj:[{}]",fantj);
    }
    @Test
    public void testChar(){
        System.out.println((char)42);
        System.out.println(((char)36));
    }

}
