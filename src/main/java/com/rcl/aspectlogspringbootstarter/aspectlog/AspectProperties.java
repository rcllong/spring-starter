package com.rcl.aspectlogspringbootstarter.aspectlog;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author chenglong.ren
 * @date 2020/9/3 13:45
 * @desc
 */
@ConfigurationProperties("aspect-log")
public class AspectProperties {
    private boolean enable;
    public boolean isEnable() {
        return enable;
    }
    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
