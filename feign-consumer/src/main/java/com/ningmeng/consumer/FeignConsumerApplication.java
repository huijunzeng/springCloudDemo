package com.ningmeng.consumer;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@EnableDiscoveryClient
@EnableFeignClients // 开启Feign功能
@SpringBootApplication
@EnableCircuitBreaker // 开启Hystrix熔断器的使用 @EnableHystrix亦可，抑或使用@SpringCloudApplication可替代以上三个注解
public class FeignConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeignConsumerApplication.class, args);
    }

    @Bean //可在这配置负载均衡策略  不配置默认为轮询策略，这里配置为随机策略，策略详细内容可查看README.md
    public IRule iRule() {
        return new RandomRule();
    }
}
