package com.gomai.order.delay;

import org.springframework.context.annotation.Configuration;

@Configuration
public class MyProperties {
    private Integer jedisMaxIdle=16;
    private String jedisHost ="117.78.0.140";
    private Integer jedisPort =6379;
    private Integer maxTotal = 300;
    private Integer jedisTimeout = 3000;
    private String jedisPassword = "123456";
    public Integer getJedisMaxIdle() {
        return jedisMaxIdle;
    }

    public Integer getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(Integer maxTotal) {
        this.maxTotal = maxTotal;
    }

    public void setJedisMaxIdle(Integer jedisMaxIdle) {
        this.jedisMaxIdle = jedisMaxIdle;
    }

    public String getJedisHost() {
        return jedisHost;
    }

    public void setJedisHost(String jedisHost) {
        this.jedisHost = jedisHost;
    }

    public Integer getJedisPort() {
        return jedisPort;
    }

    public void setJedisPort(Integer jedisPort) {
        this.jedisPort = jedisPort;
    }

    public Integer getJedisTimeout() {
        return jedisTimeout;
    }

    public void setJedisTimeout(Integer jedisTimeout) {
        this.jedisTimeout = jedisTimeout;
    }

    public String getJedisPassword() {
        return jedisPassword;
    }

    public void setJedisPassword(String jedisPassword) {
        this.jedisPassword = jedisPassword;
    }
}
