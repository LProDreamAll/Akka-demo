package com.lhh.myhttp.common.annotation;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Copyright (C), 2019-2019
 * FileName: ControllerMapping
 * Author:   s·D·bs
 * Date:     2019/6/13 20:56
 * Description:  用于url判断
 * <p>
 * Motto: 0.45%
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE) //Class, interface (including annotation type), or enum declaration
public @interface ControllerMapping {
    String url() default "";
}
