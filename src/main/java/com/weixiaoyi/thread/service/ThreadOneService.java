package com.weixiaoyi.thread.service;

import java.util.concurrent.ExecutionException;

/**
 * @author ：yuanLong Wei
 * @date ：Created in 2019/5/16 16:47
 * @description：
 * @modified By：
 * @version: 1.0
 */
public interface ThreadOneService {

    /**
     * 这里模拟多个业务同时进行
     * 如：在查库操作时，要同时查询多张表，一张一张的同步查询效率低下，可以使用线程进行异步查询，然后统一结果进行返回
     *
     * @return
     */
    String findData() throws ExecutionException, InterruptedException;

    /**
     * 模拟异步业务处理
     *
     * 这里开启线程来进行业务处理
     * 也就是说 业务处理与我主线程无关 不会耽搁主线程的释放
     *
     * @param orderNumber
     */
    void findData3(String orderNumber);

}
