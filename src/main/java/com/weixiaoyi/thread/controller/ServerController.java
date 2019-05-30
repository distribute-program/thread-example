package com.weixiaoyi.thread.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：yuanLong Wei
 * @date ：Created in 2019/5/30 14:33
 * @description：测试服务是否成功启动
 * @modified By：
 * @version: 1.0
 */
@RestController
@Slf4j
public class ServerController {

    /**
     * localhost:8004/server
     *
     * @return
     */
    @RequestMapping("server")
    public String server() {
        log.info("===========================请求过来了");
        return "访问成功";
    }

}
