package com.weixiaoyi.thread.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.stereotype.Component;

/**
 * @author ：yuanLong Wei
 * @date ：Created in 2019/5/17 10:17
 * @description：初始化主线程和最大连接数
 * @modified By：
 * @version: 1.0
 */
@Component
@Slf4j
public class ServerThreadConfig implements TomcatConnectorCustomizer {

    @Override
    public void customize(Connector connector) {
        log.info("初始化主线程....");
        Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();
        //设置最大连接数
        protocol.setMaxConnections(5);
        //设置最大线程数
        protocol.setMaxThreads(2);
        protocol.setConnectionTimeout(3000);
    }

}
