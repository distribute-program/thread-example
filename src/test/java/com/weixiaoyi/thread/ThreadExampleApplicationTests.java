package com.weixiaoyi.thread;

import com.weixiaoyi.thread.controller.ThreadOneController;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ThreadExampleApplicationTests {

    @Resource
    private ThreadOneController threadOneController;

    @Test
    public void contextLoads() {
        for (int i = 0; i < 2; i++) {
            String s = threadOneController.sleepTenSecond(i+"");
            System.out.println("范湖结果" + s);
        }
    }

}
