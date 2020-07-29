package com.lyq3.zookeeper.curator.config;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 卡卢比 (mail@lyq3.com)
 * @package com.lyq3.zookeeper.curator.config
 * @createTime 2020-07-29 14:04
 * @description Curator配置
 */
@Configuration
public class CuratorConfig {
    @Value("${curator.address}")
    private String address;
    @Value("${curator.timeout}")
    private Integer timeout;
    @Value("${curator.retryCount}")
    private Integer retryCount;
    @Value("${curator.retryElapsedTime}")
    private Integer retryElapsedTime;
    @Value("${curator.sessionTimeout}")
    private Integer sessionTimeout;

    @Bean(initMethod = "start") //调用CuratorFramework的start方法初始化
    public CuratorFramework getZkClient(){
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(retryElapsedTime, retryCount);
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder()
                .connectString(address)
                .sessionTimeoutMs(sessionTimeout)
                .retryPolicy(retryPolicy)
                .build();
        return curatorFramework;
    }
}
