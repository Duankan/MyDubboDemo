package com.github.Duan.Consumer.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author duankang
 * @func 异步
 * @date 2019-05-12
 * @desc 解决大量数据导出的异步处理
 */
@Component
public class AsyncTask {
    @Async
    public void testAsync() {
        long time = System.currentTimeMillis();
        System.err.println(Thread.currentThread().getId());
        try {
            Thread.sleep(1000 * 6);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.err.println("\nasyn total time:" + (System.currentTimeMillis() - time));
    }
}
