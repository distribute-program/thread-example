package com.weixiaoyi.thread.callable;

import com.weixiaoyi.thread.service.ThreadOneService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.Callable;

/**
 * @author ：yuanLong Wei
 * @date ：Created in 2019/5/16 17:12
 * @description：controller返回callable
 * @modified By：
 * @version: 1.0
 */
@AllArgsConstructor
@Slf4j
public class DataControllerCallable implements Callable<String> {

    /** service业务处理对象 */
    private ThreadOneService threadOneService;

    @Override
    public String call() throws Exception {
        log.info("子线程开启，线程为：{}", Thread.currentThread().getName());
        String data = threadOneService.findData();

        // 沉睡五秒
        Thread.sleep(5000);
        log.info("子线程释放");
        return data;
    }

}
