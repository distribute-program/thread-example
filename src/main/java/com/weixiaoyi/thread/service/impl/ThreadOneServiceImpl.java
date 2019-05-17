package com.weixiaoyi.thread.service.impl;

import com.weixiaoyi.thread.ao.DeferredResultAo;
import com.weixiaoyi.thread.bo.FindDataBo;
import com.weixiaoyi.thread.callable.FindDataOneCallable;
import com.weixiaoyi.thread.callable.FindDataTwoCallable;
import com.weixiaoyi.thread.service.ThreadOneService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author ：yuanLong Wei
 * @date ：Created in 2019/5/16 16:48
 * @description：
 * @modified By：
 * @version: 1.0
 */
@Slf4j
@Service
public class ThreadOneServiceImpl implements ThreadOneService {

    @Resource
    private DeferredResultAo deferredResultAo;

    /**
     * @see ThreadOneService#findData()
     *
     * FutureTask执行原理：
     * 1. 执行Thread.start() 时候，会执行 Callable.call() 进行业务处理
     * 2. 调用 FutureTask.get() 时候会进行堵塞
     * 3. 利用 java.util.concurrent.locks.LockSupport.
     * () 进行阻塞
     * 4. 当执行完 Callable.call() 获取到返回结果后
     * 5. 会利用 java.util.concurrent.locks.LockSupport.unpark() 进行线程唤醒
     * 6. 线程唤醒后会继续执行 FutureTask.get() 返回结果值
     *
     */
    @Override
    public String findData() throws ExecutionException, InterruptedException {
        FutureTask<FindDataBo> oneFutureTask = new FutureTask<>(new FindDataOneCallable());
        FutureTask<FindDataBo> twoFutureTask1 = new FutureTask<>(new FindDataTwoCallable());

        new Thread(oneFutureTask).start();
        new Thread(twoFutureTask1).start();

        FindDataBo findDataBo = oneFutureTask.get();
        FindDataBo findDataBo1 = twoFutureTask1.get();

        ArrayList<FindDataBo> findDataBos = new ArrayList<>();
        findDataBos.add(findDataBo);
        findDataBos.add(findDataBo1);

        return findDataBos.toString();
    }

    /**
     * @see ThreadOneService#findData3(String)
     *
     */
    @Override
    @Async
    public void findData3(String orderNumber) {

        new Thread(() -> {
            try {
                log.info("开始执行任务");
                Thread.sleep(3000);
                log.info("任务执行完成");
                deferredResultAo.getResults().get(orderNumber).setResult("出现结果了");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

}
