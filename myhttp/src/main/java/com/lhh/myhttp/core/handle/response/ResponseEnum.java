package com.lhh.myhttp.core.handle.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Copyright (C), 2019-2019
 * FileName: ResponseEnum
 * Author:   s·D·bs
 * Date:     2019/6/14 9:57
 * Description:
 * Motto: 0.45%
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ResponseEnum {

    NO_PATH_MAPPING(404,"路径搜索不到");

    private Integer code;

    private String des;
}
