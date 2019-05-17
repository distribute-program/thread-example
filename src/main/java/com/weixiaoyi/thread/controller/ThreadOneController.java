package com.weixiaoyi.thread.controller;

import com.weixiaoyi.thread.ao.DeferredResultAo;
import com.weixiaoyi.thread.callable.DataControllerCallable;
import com.weixiaoyi.thread.service.ThreadOneService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/**
 * @author ：yuanLong Wei
 * @date ：Created in 2019/5/16 16:46
 * @description：
 * @modified By：
 * @version: 1.0
 */
@Slf4j
@RestController
public class ThreadOneController {

    /** 服务线程的service对象 */
    @Resource
    private ThreadOneService threadOneService;

    /** DeferredResult结果集的单例封装对象 */
    @Resource
    private DeferredResultAo deferredResultAo;

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss:SSS");

    /**
     * 睡十秒
     * yyyy-MM-dd HH-mm-
     *
     * 在请求同一个接口时，分为以下几种情况
     *
     *  1. 在同一浏览器，打开两个页面，同时请求一个接口，参数列表相同。 --- 这时的请求是串行的，要处理完第一个请求后才会执行第二个请求
     *  2. 在同一浏览器，打开两个页面，同时请求一个接口，参数列表不同。 --- 这时的请求是并行的
     *  3. 在不同浏览器，打开两个页面，同时请求一个接口，参数列表相同。 --- 这时的请求是并行的
     *
     * @return
     */
    @RequestMapping("sleepTenSecond")
    public String sleepTenSecond(String param) {
        log.info("sleepTenSecond 请求过来了，时间为：{}， 线程为：{}， 参数为：{}", dateFormat.format(new Date()), Thread.currentThread().getName(),param);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("请求执行完毕，参数为：{}",param);
        return "睡醒了";
    }

    /**
     * 此处调用service进行业务处理
     *
     * @return
     */
    @RequestMapping("findData")
    public String findData() {
        log.info("findData 请求过来了，时间为：{}， 线程为：{}", dateFormat.format(new Date()), Thread.currentThread().getName());
        String result = null;
        try {
            result = threadOneService.findData();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * controller利用线程处理
     *
     * 直接创建一个callable对象并进行返回  当callable中call方法执行完毕 会自动返回值
     *
     * springboot默认初始化200个主线程，最大的连接数是10000。  这个值可以在yml中配置
     * 主线程被大量占用会造成资源浪费与堵塞 所以尽可能的提前释放掉主线程
     * 主线程代码执行完成后会释放掉主线程
     * 当子线程有返回值了 再进行返回
     *
     * 线程释放掉了，但链接没有释放掉。
     * 如果请求数量超过链接数量，则后面的请求要等待之前的链接释放掉才可以请求到方法中
     *
     * @return
     */
    @RequestMapping("findData2")
    public Callable<String> findData2(String param) {
        log.info("主线程开启，参数为：{}， 线程为：{}",param, Thread.currentThread().getName());
        DataControllerCallable dataControllerCallable = new DataControllerCallable(threadOneService);
        log.info("主线程释放");
        return dataControllerCallable;
    }

    /**
     * 此种方式适合异步调用返回结果
     *
     * 原理：
     *  创建一个DeferredResult对象并且保存下来，保存到单例的存储对象DeferredResultAo中的map属性中
     *  将唯一标识和请求数据传入到service中，service进行业务处理
     *  或将消息发送到队列中，业务处理完毕后进行回调
     *  根据唯一标识获取这个请求的DeferredResult，将结果集DeferredResult.setResult()
     *  这时，controller才进行结果的返回
     *
     * @return
     */
    @RequestMapping("findData3")
    public DeferredResult<String> findData3() {
        log.info("主线程开启");

        // 生成请求的唯一标识
        String orderNumber = UUID.randomUUID().toString();
        // 创建结果对象 并且加入到结果集中
        DeferredResult<String> deferredResult = new DeferredResult<>();
        deferredResultAo.getResults().put(orderNumber,deferredResult);

        // 调用服务 进行异步处理
        threadOneService.findData3(orderNumber);

        log.info("主线程关闭");
        return deferredResult;
    }

}
