package com.lhh.myhttp.util;//package util;
//
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import groovy.util.logging.Slf4j;
//import org.apache.commons.lang.time.FastDateFormat;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import sun.misc.BASE64Decoder;
//
//import javax.servlet.http.HttpServletRequest;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.util.Arrays;
//import java.util.Enumeration;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Copyright (C), 2019-2019
// * FileName: Utils
// * Author:   s·D·bs
// * Date:     2019/4/30 13:39
// * Description: 相关utils抽取工具类
// * Motto: 0.45%
// */
//@Slf4j
//public class Utils {
//
//    private static final Logger log = LoggerFactory.getLogger(Utils.class);
//    public static void main(String[] args) throws IOException {
//        String imgStr = "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\nRU5ErkJggg==";
////        GenerateImage(imgStr, "NEW.JPG");
//    }
//
//    /**
//     * @return boolean
//     * @Author s·D·bs
//     * @Description base64字符串转化成图片
//     * @Date 9:45 2019/5/8
//     * @Param [imgStr]
//     */
//
//    public static void GenerateImage(String imgStr, String imgFilePath) {
//        //对字节数组字符串进行Base64解码并生成图片
//        if (imgStr == null) //图像数据为空
//            return;
//        BASE64Decoder decoder = new BASE64Decoder();
//        try {
//            //Base64解码
//            byte[] b = decoder.decodeBuffer(imgStr);
//            for (int i = 0; i < b.length; ++i) {
//                if (b[i] < 0) {//调整异常数据
//                    b[i] += 256;
//                }
//            }
//            //生成jpeg图片
//            OutputStream out = new FileOutputStream(imgFilePath);
//            out.write(b);
//            out.flush();
//            out.close();
//        } catch (Exception e) {
//            log.error("GenerateImage error :{} ! ", e);
//        }
//    }
//    private static ObjectMapper mapper = new ObjectMapper();
//
//    /**
//     * @return java.lang.String
//     * @Author s·D·bs
//     * @Description //任意对象转换成JSON格式字符串
//     * @Date 16:59 2019/5/31
//     * @Param [o]
//     */
//    public static <T> String toJson(T o) {
//
//        if (o == null) {
//            return null;
//        }
//        try {
//            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
//            return mapper.writeValueAsString(o);
//        } catch (Exception e) {
//            log.error("toJson Error:[{}]", e);
//            return null;
//        }
//    }
//
//    /**
//     * @return java.lang.String
//     * @Author s·D·bs
//     * @Description //格式化json字符串
//     * @Date 16:58 2019/5/31
//     * @Param [jsonStr]
//     */
//    public static String formatJson(String jsonStr) {
//
//        int level = 0;
//        StringBuilder jsonFormatStr = new StringBuilder();
//        jsonFormatStr.append("\n");
//        for (int index = 0; index < jsonStr.length(); index++) {
//            char c = jsonStr.charAt(index);
//
//            //level大于0并且jsonForMatStr中的最后一个字符为\n,jsonForMatStr加入\t
//            if (level > 0 && '\n' == jsonFormatStr.charAt(jsonFormatStr.length() - 1)) {
//                jsonFormatStr.append(getLevelStr(level));
//            }
//            //遇到"{"和"["要增加空格和换行，遇到"}"和"]"要减少空格，以对应，遇到","要换行
//            switch (c) {
//                case '{':
//                case '[':
//                    jsonFormatStr.append(c).append("\n");
//                    level++;
//                    break;
//                case ',':
//                    jsonFormatStr.append(c).append("\n");
//                    break;
//                case '}':
//                case ']':
//                    jsonFormatStr.append("\n");
//                    level--;
//                    jsonFormatStr.append(getLevelStr(level));
//                    jsonFormatStr.append(c);
//                    break;
//                default:
//                    jsonFormatStr.append(c);
//                    break;
//            }
//        }
//        return jsonFormatStr.toString();
//    }
//
//    public static String getLevelStr(int level) {
//        StringBuilder levelStr = new StringBuilder();
//        for (int levelI = 0; levelI < level; levelI++) {
//            levelStr.append("\t");
//        }
//        return levelStr.toString();
//    }
//
//
//    /**
//     * @return java.lang.String
//     * @Author s·D·bs
//     * @Description //取格式化的系统当前时间，格式为：dd/MM/yyyy:HH:mm:ss Z
//     * @Date 16:57 2019/5/31
//     * @Param []
//     */
//    public static String getTimeZoneDatetime() {
//
//        FastDateFormat sdf = FastDateFormat.getInstance("dd/MM/yyyy:HH:mm:ss Z");
//        return sdf.format(System.currentTimeMillis());
//    }
//
//    /**
//     * @return java.util.Map<java.lang.String, java.lang.Object>
//     * @Author s·D·bs
//     * @Description //参数处理
//     * @Date 16:57 2019/5/31
//     * @Param [request]
//     */
//    public static Map<String, Object> reqParamToGenericMap(HttpServletRequest request) {
//        Map<String, Object> newMap = new HashMap<String, Object>();
//        Enumeration<?> names = request.getParameterNames();
//        while (names.hasMoreElements()) {
//            final String name = (String) names.nextElement();
//            newMap.put(name, getArraySubset(request.getParameterValues(name)));
//        }
//        return newMap;
//    }
//
//    private static String getArraySubset(String[] array) {
//        StringBuilder sb = new StringBuilder();
//        Arrays.asList(array).forEach(s -> sb.append(s).append(","));
//        sb.deleteCharAt(sb.lastIndexOf(","));
//        return sb.toString();
//    }
//
//    /**
//     * 验证任意的对象不为null并且不为空字符（仅支持基本数据类型）
//     *
//     * @param obj
//     * @return
//     * @author lizhongpeng 2018-08-13 上午10:19
//     */
//    public static boolean isNotNull(Object obj) {
//        return !isNull(obj);
//    }
//
//    /**
//     * 验证任意的对象为null或为空字符（仅支持基本数据类型）
//     *
//     * @param obj
//     * @return
//     * @author lizhongpeng 2018-08-13 上午10:19
//     */
//    public static boolean isNull(Object obj) {
//        if (null == obj)
//            return true;
//        if (obj instanceof String) {
//            if ("".equals(((String) obj).trim()))
//                return true;
//        }
//        return "".equals(obj);
//    }
//}
