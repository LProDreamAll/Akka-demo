package com.standard.akka.practice;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Copyright (C), 2019-2019
 * FileName: SetRequest
 * Author:   s·D·bs
 * Date:     2019/5/29 10:10
 * Description: SetRequest消息体
 * Motto: 0.45%
 */
@Getter
@RequiredArgsConstructor(staticName = "of")
public class SetRequest {

    @NonNull
    private final String key;
    @NonNull
    private final Object value;

}
