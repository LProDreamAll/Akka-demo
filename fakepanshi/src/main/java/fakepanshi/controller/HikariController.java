package fakepanshi.controller;

import fakepanshi.entity.TableHikari;
import fakepanshi.mapper.ITableHikariMapper;
import fakepanshi.utils.Response;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Copyright (C), 2019-2019
 * FileName: PersonController
 * Author:   s·D·bs
 * Date:     2019/6/3 10:15
 * Description:
 * Motto: 0.45%
 */

@RestController
@RequestMapping("/th")
@Slf4j
public class HikariController {

    private static AtomicLong atomicLong = new AtomicLong(0);

    @Autowired
    private ITableHikariMapper iTableHikariMapper;

    @RequestMapping(value = "/get/data", method = RequestMethod.GET)
    public Response getAll() {
        return Response.of().success(iTableHikariMapper.getAll());
    }


    @RequestMapping(value = "/insert/data", method = RequestMethod.POST)
    public Response insert(@RequestBody @NonNull TableHikari tb) {
        try {
            long l = atomicLong.incrementAndGet();
            tb.setName(tb.getName() + l);
            tb.setContent(tb.getContent() + l);
            log.info("atomicLong:[{}]", l);
            int insert = iTableHikariMapper.insert(tb);
            log.info("insert:[{}]", insert);
            if (insert > 0) {
                return Response.of().success(insert);
            }
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return Response.of().failure();

    }


}
