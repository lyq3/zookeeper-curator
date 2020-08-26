package com.lyq3.zookeeper.curator.service.impl;

import com.lyq3.zookeeper.curator.service.DistributedLockService;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 卡卢比 (mail@lyq3.com)
 * @package com.lyq3.zookeeper.curator.service.impl
 * @createTime 2020-08-20 14:35
 * @description
 */
@Service
public class DistributedLockServiceImpl implements DistributedLockService {
    @Autowired
    private CuratorFramework curatorFramework;

    @Override
    public void interProcessMutexLock() throws Exception {
        System.out.println("线程"+Thread.currentThread().getId());
        //创建共享锁
        final InterProcessLock lock = new InterProcessMutex(curatorFramework, "/lock/test/InterProcessMutexLock");

        System.out.println(this+"线程"+Thread.currentThread().getId()+":开始获取锁...");
        lock.acquire();
        System.out.println(this+"线程"+Thread.currentThread().getId()+":获取锁执行业务逻辑...");

        Thread.sleep(2*1000);

        System.out.println(this+"线程"+Thread.currentThread().getId()+":再次开始获取锁...");
        lock.acquire();
        System.out.println(this+"线程"+Thread.currentThread().getId()+":再次获取锁执行业务逻辑...");
        Thread.sleep(1*1000);

        lock.release();
        System.out.println(this+"线程"+Thread.currentThread().getId()+":释放锁");
        lock.release();
        System.out.println(this+"线程"+Thread.currentThread().getId()+":再次释放锁");

    }
}
