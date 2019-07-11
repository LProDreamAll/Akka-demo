package com.lhh.myhttp.core.handle.response;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Copyright (C), 2019-2019
 * FileName: BaseResponse
 * Author:   s·D·bs
 * Date:     2019/6/14 9:56
 * Description: 返回的响应类型
 * Motto: 0.45%
 */
@Data
@AllArgsConstructor
public class BaseResponse {

    private int code;

    private Object data;
}
