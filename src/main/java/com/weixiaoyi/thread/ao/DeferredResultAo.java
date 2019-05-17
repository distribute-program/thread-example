package com.weixiaoyi.thread.ao;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;
import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ：yuanLong Wei
 * @date ：Created in 2019/5/16 18:13
 * @description：异步结果封装类
 * @modified By：
 * @version: 1.0
 */
@Data
@Component
public class DeferredResultAo implements Serializable {

    private static final long serialVersionUID = 6531440991691413158L;

    /** 结果集 key：请求的唯一标识， value：结果集 */
    private ConcurrentHashMap<String, DeferredResult<String>> results = new ConcurrentHashMap<>();

}
