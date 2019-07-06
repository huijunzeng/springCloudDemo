package com.ningmeng.consumer;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@EnableDiscoveryClient
@EnableFeignClients // 开启Feign功能，同时查看依赖，Feign已经支持hytrix熔断，所以不需要再像Ribbon那样添加@EnableCircuitBreaker注解
@SpringBootApplication
public class FeignConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeignConsumerApplication.class, args);
    }

    @Bean //可在这配置负载均衡策略  不配置默认为轮询策略，这里配置为随机策略，策略详细内容可查看README.md
    public IRule iRule() {
        return new RandomRule();
    }

    /**
     * 相比springboot2.0以下版本无需配置默认直接输入/hystrix.stream，springboot2.0版本以上在结合hystrix的dashboard控制面板时，需要注入一个HystrixMetricsStreamServlet，并且设置访问路径/actuator/hystrix.stream或者/hystrix.stream，此外还需在配置文件设置开放/actuator端点
     * @return
     */
    @Bean(name = "hystrixRegistrationBean")
    public ServletRegistrationBean servletRegistrationBean() {
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/actuator/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }
}
