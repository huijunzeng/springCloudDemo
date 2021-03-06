package com.ningmeng.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@SpringBootApplication
public class ServiceConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceConsumerApplication.class, args);
    }

    @Bean// 注入一个bean 利用RestTemplate对象实现对服务提供者接口的调用  也可以通过@Configuration配置文件的方式去注入
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
