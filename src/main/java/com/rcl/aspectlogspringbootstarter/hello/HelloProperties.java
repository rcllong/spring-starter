package com.rcl.aspectlogspringbootstarter.hello;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author chenglong.ren
 * @date 2020/9/3 11:35
 * @desc
 */
@ConfigurationProperties("my.hello")
public class HelloProperties {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
