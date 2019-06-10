package com.lhh.my.jedis;

/**
 * Copyright (C), 2019-2019
 * FileName: MyJedis
 * Author:   s·D·bs
 * Date:     2019/6/10 10:02
 * Description:
 * Motto: 0.45%
 */
public class MyJedis extends Client {


    public MyJedis(String host, int port) {
        super(host, port);
    }

    @Override
    public String set(String key, String value) {
        return super.set(key, value);
    }

    @Override
    public String get(String key) {
        return super.get(key);
    }

    @Override
    public void set(String key, String value, String nx, String ex, int i) {
        super.set(key, value, nx, ex, i);
    }

    @Override
    public void append(String key, String value) {
        super.append(key, value);
    }
}
