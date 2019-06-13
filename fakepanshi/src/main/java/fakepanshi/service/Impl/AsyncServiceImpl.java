package fakepanshi.service.Impl;

import fakepanshi.service.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Copyright (C), 2019-2019
 * FileName: AsyncServiceImpl
 * Author:   s·D·bs
 * Date:     2019/6/11 10:04
 * Description:
 * Motto: 0.45%
 */
@Service
@Slf4j
public class AsyncServiceImpl implements AsyncService {

    @Override
    @Async("asyncServiceExecutor")
    public void executeAsync() {
        log.info("start executeAsync");
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("end executeAsync");
    }
}
