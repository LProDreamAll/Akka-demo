package com.lhh.myhttp.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Copyright (C), 2019-2019
 * FileName: Filter
 * Author:   s·D·bs
 * Date:     2019/6/14 9:38
 * Description:
 * Motto: 0.45%
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Filter {

    String name() default "";

    int order() default 0;
}
