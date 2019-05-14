/**
 * Copyright (C), 2019-2019
 * FileName: Message
 * Author:   s·D·bs
 * Date:     2019/5/14 22:07
 * Description: 封装传递的对象
 * Motto: 0.45%
 *
 * @create 2019/5/14
 * @since 1.0.0
 */


package com.lhh.day02;

import java.util.Collections;
import java.util.List;

public final class Message {//一般采用不可变对象
    private final int age;
    private final List<String> list;

    public Message(int age, List<String> list) {
        this.age = age;
        /**
         * 把普通list包装为不可变对象
         */
        this.list = Collections.unmodifiableList(list);
    }
    public int getAge() {
        return age;
    }

    public List<String> getList() {
        return list;
    }
}
