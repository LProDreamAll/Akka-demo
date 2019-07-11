package com.lhh.myhttp.common.em;

import lombok.Getter;


/**
 * Copyright (C), 2019-2019
 * FileName: ITreeConfig
 * Author:   s·D·bs
 * Date:     2019/6/13 20:56
 * Description: 添加配置文件，有缺省配置
 * <p>
 * Motto: 0.45%
 */
@Getter
public enum PageMappingEnum {

    INDEX_PAGE(200, MyConfig.INDEX_PAGE),
    NOT_FOUND_PAGE(404, MyConfig.NOT_FOUND_PAGE),
    UNKOWN_EXCEPTION_PAGE(500, MyConfig.UNKOWN_EXCEPTION_PAGE);

    private int code;

    private String path;

    PageMappingEnum(int code, String path) {
        this.code = code;
        this.path = path;
    }

    public static String getPath(int code) {
        for (PageMappingEnum pageMappingEnum : PageMappingEnum.values()) {
            if (pageMappingEnum.getCode() == code) {
                return pageMappingEnum.getPath();
            }
        }
        return null;
    }
}
