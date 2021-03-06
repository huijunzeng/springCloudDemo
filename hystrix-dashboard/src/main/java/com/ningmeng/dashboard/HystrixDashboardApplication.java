package com.ningmeng.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

//@SpringCloudApplication  //相当于这三个注解@SpringBootApplication @EnableDiscoveryClient @EnableCircuitBreaker
@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrixDashboard // 开启hystrix仪表盘
public class HystrixDashboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(HystrixDashboardApplication.class, args);
    }

}
