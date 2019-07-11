package com.lhh.myhttp.common.em;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Copyright (C), 2019-2019
 * FileName: ITreeConfig
 * Author:   s·D·bs
 * Date:     2019/6/13 20:56
 * Description: 添加配置文件，有缺省配置
 * <p>
 * Motto: 0.45%
 */
@Slf4j
public class MyConfig {

    //定义首页和404路由页面
    public static String INDEX_PAGE = "html/index.html";

    public static boolean INDEX_CHANGE = false;

    public static String NOT_FOUND_PAGE = "html/404_page.html";

    public static boolean NOT_FOUND_CHANGE = false;

    public static String UNKOWN_EXCEPTION_PAGE = "page/404_page.html";

    public static boolean UNKOWN_EXCEPTION_CHANGE = false;
    //默认端口
    public static int PORT = 8080;

    //配置文件路径 改成springboot注解直接获取
    private static final String DEFAULT_LOCALTION = "/config/itree-config.properties";


    private static final String INDEX_PAGE_KEY = "index.page";

    private static final String NOT_FOUND_PAGE_KEY = "not.found.page";

    private static final String UNKOWN_EXCEPTION_PAGE_KEY = "unkown.exception.page";

    private static final String PORT_KEY = "server.port";

    //公共应用类 为了获取当前项目下的配置文件
    public static Class APPLICATION_CLASS;

    /**
     * @return void
     * @Author s·D·bs
     * @Description 加载配置文件
     * @Date 9:34 2019/6/14
     * @Param []
     */
    public static void init() {

        Properties properties = new Properties();
        try {
            InputStream in = APPLICATION_CLASS.getResourceAsStream(DEFAULT_LOCALTION);
            if (in != null) {
                properties.load(in);
                if (properties.getProperty(INDEX_PAGE_KEY) != null) {
                    INDEX_PAGE = properties.getProperty(INDEX_PAGE_KEY);
                    INDEX_CHANGE = true;
                }
                if (properties.getProperty(NOT_FOUND_PAGE_KEY) != null) {
                    NOT_FOUND_PAGE = properties.getProperty(NOT_FOUND_PAGE_KEY);
                    NOT_FOUND_CHANGE = true;
                }

                if (properties.getProperty(UNKOWN_EXCEPTION_PAGE_KEY) != null) {
                    UNKOWN_EXCEPTION_PAGE = properties.getProperty(UNKOWN_EXCEPTION_PAGE_KEY);
                    UNKOWN_EXCEPTION_CHANGE = true;
                }

                if (properties.getProperty(PORT_KEY) != null) {
                    PORT = Integer.parseInt(properties.getProperty(PORT_KEY));
                }
            }
            log.info("[ITreeConfig] server初始化静态资源文件");
            log.info("[ITreeConfig] port:[{}] ,index_page:[{}] ,error_page:[{}]", PORT, INDEX_PAGE, UNKOWN_EXCEPTION_PAGE);
        } catch (IOException e) {
            log.error("[ITreeConfig]异常为:[{}]", e);
        }
    }
}
