package fakepanshi.config;

import com.alibaba.fastjson.JSON;

import fakepanshi.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;


/**
 * Copyright (C), 2019-2019
 * FileName: SwaggerConfig
 * Author:   s·D·bs
 * Date:     2019/5/31 16:20
 * Description: HttpAspect请求拦截
 * Motto: 0.45%
 */
@Aspect
@Component
@Slf4j
public class HttpAspect {

    private static final StringBuilder sb = new StringBuilder();
    private static long l = 0;
    private String apiUrl;

    /**
     * @Before 在方法执行之前执行
     */
    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void log() {

    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        l = System.currentTimeMillis();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();

        try {

            sb.append("time_local=").append(Utils.getTimeZoneDatetime()).append(",");

            //接口地址
            if (Utils.isNotNull(request.getRequestURI())) {
                this.apiUrl = request.getRequestURI();
                sb.append("api_url=").append(request.getRequestURI()).append(",");
            }


            //接口请求方式
            if (Utils.isNotNull(request.getMethod())) {
                sb.append("method=").append(request.getMethod().toUpperCase()).append(",");
            }

            //类名与函数名
            if (Utils.isNotNull(joinPoint.getSignature().getDeclaringTypeName()) && Utils.isNotNull(joinPoint.getSignature().getName())) {
                sb.append("class_method=").append(joinPoint.getSignature().getDeclaringTypeName()).append(".").append(joinPoint.getSignature().getName()).append(",");

            }

            //请求参数
            final Map<String, Object> map = Utils.reqParamToGenericMap(request);

            if (map.containsKey("file")) {
                map.put("file", map.get("file").toString());
            }
            if (Utils.isNotNull(map)) {
                sb.append("map=").append(map).append(",");
            }
        } catch (Exception e) {
            log.error("HttpAspect-Error: {}, user_id={},api_url={}", e, Objects.requireNonNull(request).getRequestURI());
        }
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void doAfter() {
        log.info("apiUrl:[{}]调用完毕！", apiUrl);
    }

    @AfterReturning(returning = "object", pointcut = "log()")
    public void doAfterReturning(Object object) {
        try {
            sb.append("response=").append(JSON.toJSONString(object));
            final long l = System.currentTimeMillis() - HttpAspect.l;
            sb.append(",\"use_time\"=").append(l);
            sb.append("]");
            log.info("standard_api_log={}", sb);
            sb.delete(0, sb.length());
        } catch (Exception e) {
            log.error("HttpAspect-doAfterReturning-Error: {}", e);
        }
    }


}
