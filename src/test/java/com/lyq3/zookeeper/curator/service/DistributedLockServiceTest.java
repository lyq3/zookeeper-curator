package com.lyq3.zookeeper.curator.service;

import com.lyq3.zookeeper.curator.BaseTest;
import com.sun.corba.se.spi.orbutil.threadpool.ThreadPool;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 卡卢比 (mail@lyq3.com)
 * @package com.lyq3.zookeeper.curator.service
 * @createTime 2020-08-26 17:02
 * @description 分布式锁设置
 */
public class DistributedLockServiceTest extends BaseTest {
    @Autowired
    private DistributedLockService distributedLockService;

    private ExecutorService threadPool = Executors.newFixedThreadPool(10);

    @Test
    public void testInterProcessMutexLock() throws IOException {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    distributedLockService.interProcessMutexLock();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }

        System.in.read();
    }


    @Test
    public void testInterProcessSemaphoreMutex() throws IOException {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    distributedLockService.interProcessSemaphoreMutex();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }

        System.in.read();
    }


}
