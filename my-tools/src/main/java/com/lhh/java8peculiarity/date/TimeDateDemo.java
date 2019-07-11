package com.lhh.java8peculiarity.date;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;

/**
 * Copyright (C), 2019-2019
 * FileName: timedate
 * Author:   s·D·bs
 * Date:     2019/7/11 10:52
 * Description: 测试java8时间api
 * Motto: 0.45%
 */
@Slf4j
public class TimeDateDemo {


    @Test
    public void test_LocalDate() {

// 取当前日期：
        LocalDate today = LocalDate.now(); // -> 2014-12-24
// 根据年月日取日期，12月就是12：
        LocalDate crischristmas = LocalDate.of(2014, 12, 25); // -> 2014-12-25
        log.info("crischristmas:[{}]", crischristmas);

        LocalDate endOfFeb = LocalDate.parse("2014-02-28"); // 严格按照ISO yyyy-MM-dd验证，02写成2都不行，当然也有一个重载方法允许自己定义格式
        log.info("endOfFeb:[{}]", endOfFeb);

        // 取本月第1天：
        LocalDate firstDayOfThisMonth = today.with(TemporalAdjusters.firstDayOfMonth()); // 2014-12-01
        log.info("firstDayOfThisMonth:[{}]", firstDayOfThisMonth);
        // 取本月第2天：
        LocalDate secondDayOfThisMonth = today.withDayOfMonth(2); // 2019-07-02
        log.info("secondDayOfThisMonth:[{}]", secondDayOfThisMonth);
        LocalDate lastThisMonth = today.with(TemporalAdjusters.lastDayOfMonth());
        log.info("lastThisMonth:[{}]", lastThisMonth);
        //取到下一天
        LocalDate localDate = lastThisMonth.plusDays(1);
        log.info("localDate:[{}]", localDate);
        // 取2019年8月第一个周一，这个计算用Calendar要死掉很多脑细胞：
        LocalDate firstMondayOf2019 = LocalDate.parse("2019-08-01").with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY)); //
        log.info("firstMondayOf2019:[{}]", firstMondayOf2019);
    }

    @Test
    public void test_LocalTime() {
        LocalTime now = LocalTime.now(); // 11:09:09.240
        log.info("now:[{}]", now);
        LocalTime now2 = LocalTime.now().withNano(0); // 11:09:09
        log.info("now2:[{}]", now2);
        LocalTime zero = LocalTime.of(0, 0, 0); // 00:00:00
        log.info("zero:[{}]", zero);
        LocalTime mid = LocalTime.parse("12:00:00"); // 12:00:00
        log.info("mid:[{}]", mid);
    }


}
