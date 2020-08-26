package com.lyq3.zookeeper.curator.service;

public interface DistributedLockService {

    /**
     * 获取可重入排他锁
     *
     * 原理:
     * InterProcessMutex通过在zookeeper的某路径节点下创建临时序列节点来实现分布式锁，
     * 即每个线程（跨进程的线程）获取同一把锁前，都需要在同样的路径下创建一个节点，
     * 节点名字由uuid + 递增序列组成。而通过对比自身的序列数是否在所有子节点的第一位，来判断是否成功获取到了锁。
     * 当获取锁失败时，它会添加watcher来监听前一个节点的变动情况，然后进行等待状态。
     * 直到watcher的事件生效将自己唤醒，或者超时时间异常返回。
     * @return
     */
    void interProcessMutexLock() throws Exception;

    /**
     * 共享锁，不可重入
     * @throws Exception
     */
    void interProcessSemaphoreMutex() throws Exception;


}
