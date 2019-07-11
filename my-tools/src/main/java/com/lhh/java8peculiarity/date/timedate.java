package com.lhh.java8peculiarity.date;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.LocalDate;
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
public class timedate {


    @Test
    public void test_date1() {

// 取当前日期：
        LocalDate today = LocalDate.now(); // -> 2014-12-24
// 根据年月日取日期，12月就是12：
        LocalDate crischristmas = LocalDate.of(2014, 12, 25); // -> 2014-12-25
        LocalDate endOfFeb = LocalDate.parse("2014-02-28"); // 严格按照ISO yyyy-MM-dd验证，02写成2都不行，当然也有一个重载方法允许自己定义格式
        // 取本月第1天：
        LocalDate firstDayOfThisMonth = today.with(TemporalAdjusters.firstDayOfMonth()); // 2014-12-01
        println(firstDayOfThisMonth);
        // 取本月第2天：
        LocalDate secondDayOfThisMonth = today.withDayOfMonth(2); // 2019-07-02
        log.info("secondDayOfThisMonth:[{}]", secondDayOfThisMonth);
    }

    private static void println(Object o) {
        System.out.println(o);
    }

    public static void main(String[] args) {


    }

}
