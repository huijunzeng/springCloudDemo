package com.ningmeng.zuulserver;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy// 开启zuul的功能  也可以使用@EnableZuulServer，@EnableZuulProxy是@EnableZuulServer的超集，也就是说@EnableZuulProxy比@EnableZuulServer拥有更多的功能，基本使用的话用@EnableZuulServer也足够了
@EnableDiscoveryClient// 向注册中心注册
@SpringBootApplication
public class ZuulServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulServerApplication.class, args);
    }
}
