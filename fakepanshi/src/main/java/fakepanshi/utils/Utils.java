package fakepanshi.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.FastDateFormat;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (C), 2019-2019
 * FileName: Utils
 * Author:   s·D·bs
 * Date:     2019/5/31 16:29
 * Description:
 * Motto: 0.45%
 */
@Slf4j
public class Utils {

    private static ObjectMapper mapper = new ObjectMapper();

    /**
     * @return java.lang.String
     * @Author s·D·bs
     * @Description //任意对象转换成JSON格式字符串
     * @Date 16:59 2019/5/31
     * @Param [o]
     */
    public static <T> String toJson(T o) {

        if (o == null) {
            return null;
        }
        try {
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            return mapper.writeValueAsString(o);
        } catch (Exception e) {
            log.error("toJson Error:[{}]", e);
            return null;
        }
    }

    /**
     * @return java.lang.String
     * @Author s·D·bs
     * @Description //格式化json字符串
     * @Date 16:58 2019/5/31
     * @Param [jsonStr]
     */
    public static String formatJson(String jsonStr) {

        int level = 0;
        StringBuilder jsonFormatStr = new StringBuilder();
        jsonFormatStr.append("\n");
        for (int index = 0; index < jsonStr.length(); index++) {
            char c = jsonStr.charAt(index);

            //level大于0并且jsonForMatStr中的最后一个字符为\n,jsonForMatStr加入\t
            if (level > 0 && '\n' == jsonFormatStr.charAt(jsonFormatStr.length() - 1)) {
                jsonFormatStr.append(getLevelStr(level));
            }
            //遇到"{"和"["要增加空格和换行，遇到"}"和"]"要减少空格，以对应，遇到","要换行
            switch (c) {
                case '{':
                case '[':
                    jsonFormatStr.append(c).append("\n");
                    level++;
                    break;
                case ',':
                    jsonFormatStr.append(c).append("\n");
                    break;
                case '}':
                case ']':
                    jsonFormatStr.append("\n");
                    level--;
                    jsonFormatStr.append(getLevelStr(level));
                    jsonFormatStr.append(c);
                    break;
                default:
                    jsonFormatStr.append(c);
                    break;
            }
        }
        return jsonFormatStr.toString();
    }

    public static String getLevelStr(int level) {
        StringBuilder levelStr = new StringBuilder();
        for (int levelI = 0; levelI < level; levelI++) {
            levelStr.append("\t");
        }
        return levelStr.toString();
    }


    /**
     * @return java.lang.String
     * @Author s·D·bs
     * @Description //取格式化的系统当前时间，格式为：dd/MM/yyyy:HH:mm:ss Z
     * @Date 16:57 2019/5/31
     * @Param []
     */
    public static String getTimeZoneDatetime() {

        FastDateFormat sdf = FastDateFormat.getInstance("dd/MM/yyyy:HH:mm:ss Z");
        return sdf.format(System.currentTimeMillis());
    }

    /**
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @Author s·D·bs
     * @Description //参数处理
     * @Date 16:57 2019/5/31
     * @Param [request]
     */
    public static Map<String, Object> reqParamToGenericMap(HttpServletRequest request) {
        Map<String, Object> newMap = new HashMap<String, Object>();
        Enumeration<?> names = request.getParameterNames();
        while (names.hasMoreElements()) {
            final String name = (String) names.nextElement();
            newMap.put(name, getArraySubset(request.getParameterValues(name)));
        }
        return newMap;
    }

    private static String getArraySubset(String[] array) {
        StringBuilder sb = new StringBuilder();
        Arrays.asList(array).forEach(s -> sb.append(s).append(","));
        sb.deleteCharAt(sb.lastIndexOf(","));
        return sb.toString();
    }

    /**
     * 验证任意的对象不为null并且不为空字符（仅支持基本数据类型）
     *
     * @param obj
     * @return
     * @author lizhongpeng 2018-08-13 上午10:19
     */
    public static boolean isNotNull(Object obj) {
        return !isNull(obj);
    }

    /**
     * 验证任意的对象为null或为空字符（仅支持基本数据类型）
     *
     * @param obj
     * @return
     * @author lizhongpeng 2018-08-13 上午10:19
     */
    public static boolean isNull(Object obj) {
        if (null == obj)
            return true;
        if (obj instanceof String) {
            if ("".equals(((String) obj).trim()))
                return true;
        }
        return "".equals(obj);
    }

}
