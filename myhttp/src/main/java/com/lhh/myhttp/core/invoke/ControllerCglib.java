package com.lhh.myhttp.core.invoke;

import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Copyright (C), 2019-2019
 * FileName: ControllerCglib
 * Author:   s·D·bs
 * Date:     2019/6/14 10:17
 * Description:
 * Motto: 0.45%
 */
@Slf4j
public class ControllerCglib implements MethodInterceptor {

    private Object target;

    /**
     * @return java.lang.Object
     * @Author s·D·bs
     * @Description 使用 Cglib
     * @Date 13:10 2019/6/14
     * @Param [target]
     */
    public Object getTarget(Object target) {

        this.target = target;
        Enhancer enhancer = new Enhancer();
        //设置当前的代理对象
        enhancer.setSuperclass(this.target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        preHandle(methodProxy);
        Object value = methodProxy.invokeSuper(o, objects);
        afterHandle(value);
        return value;
    }

    private void preHandle(MethodProxy methodProxy) {
        log.info("代理开始：[{}]", methodProxy.toString());
    }

    private void afterHandle(Object value) {
        log.info("生成的代理对象：[{}]", value.toString());
    }
}
