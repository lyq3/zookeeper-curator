package com.lyq3.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BaseTest {
    @Autowired
    private CuratorFramework zkClient;
    @Test
    public void test() throws Exception {
        byte[] bytes = zkClient.getData().forPath("/bigdata/config/pvuv");
        String str = new String(bytes, "UTF-8");
        System.out.println(str);
    }
}
