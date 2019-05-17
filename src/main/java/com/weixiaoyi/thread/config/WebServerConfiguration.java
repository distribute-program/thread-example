package com.weixiaoyi.thread.config;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author ：yuanLong Wei
 * @date ：Created in 2019/5/17 10:20
 * @description：tomcat设置
 * @modified By：
 * @version: 1.0
 */
@Component
public class WebServerConfiguration implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {

    @Resource
    private ServerThreadConfig serverThreadConfig;

    @Override
    public void customize(TomcatServletWebServerFactory factory) {
        Collection<ServerThreadConfig> tomcatConnectorCustomizers = new ArrayList<>();
        tomcatConnectorCustomizers.add(serverThreadConfig);
        factory.setTomcatConnectorCustomizers(tomcatConnectorCustomizers);
    }

}
