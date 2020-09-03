package com.rcl.aspectlogspringbootstarter.hello;

/**
 * @author chenglong.ren
 * @date 2020/9/3 11:36
 * @desc
 */
public class HelloService {
    private String name;

    public HelloService(String name) {
        this.name = name;
    }
    public String hello(String name) {
        return "hello " + name;
    }
}
