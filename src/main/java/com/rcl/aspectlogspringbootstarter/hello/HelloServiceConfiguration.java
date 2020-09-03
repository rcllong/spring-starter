package com.rcl.aspectlogspringbootstarter.hello;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author chenglong.ren
 * @date 2020/9/3 11:37
 * @desc
 */
@Configuration
@EnableConfigurationProperties(HelloProperties.class)
public class HelloServiceConfiguration {
    private HelloProperties helloProperties;

    public HelloServiceConfiguration(HelloProperties helloProperties) {
        this.helloProperties = helloProperties;
    }

    @Bean
    @ConditionalOnMissingBean
    public HelloService helloService() {
        return new HelloService(this.helloProperties.getName());
    }
}
