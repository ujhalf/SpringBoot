package com.ujhalf.hello.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author Hui-min Lu
 * @Date 2020/12/30 13:38
 * @Version 1.0
 * @Description
 */
@ConfigurationProperties("com.half.hello")
public class HelloProperties {
    private String prefix;
    private String suffix;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
