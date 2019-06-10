package com.lhh.my.jedis;

/**
 * Copyright (C), 2019-2019
 * FileName: Pool
 * Author:   s·D·bs
 * Date:     2019/6/10 10:21
 * Description: 连接池契约
 * Motto: 0.45%
 */
public interface Pool<T> {

    /**
     * 初始化连接池
     *
     * @param maxTotal      最大连接数
     * @param maxWaitMillis 最大等待时间
     */
    public void init(int maxTotal, long maxWaitMillis);

    /**
     * 获取连接
     *
     * @return 返回jedis对象
     */
    public MyJedis getResource() throws Exception;

    /**
     * 释放连接
     */
    public void release(T t);
}
