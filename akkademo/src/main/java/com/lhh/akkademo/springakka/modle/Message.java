package com.lhh.akkademo.springakka.modle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * Copyright (C), 2019-2019
 * FileName: Message
 * Author:   s·D·bs
 * Date:     2019/5/24 14:58
 * Description: 信息实体类
 * Motto: 0.45%
 */
@AllArgsConstructor
@Data
@ToString
public class Message {

    final private String payload;
    final private long id;
    final private int hashCode;
}
