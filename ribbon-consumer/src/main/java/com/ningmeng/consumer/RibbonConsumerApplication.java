package com.ningmeng.consumer;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@SpringBootApplication
@EnableCircuitBreaker // 开启Hystrix熔断器的使用 @EnableHystrix亦可，抑或使用@SpringCloudApplication可替代以上三个注解
public class RibbonConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RibbonConsumerApplication.class, args);
    }

    @Bean// 注入一个bean 利用RestTemplate对象实现对服务提供者接口的调用  也可以通过@Configuration配置文件的方式去注入
    @LoadBalanced// 开启负载均衡
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean //可在这配置负载均衡策略  不配置默认为轮询策略，这里配置为随机策略，各策略详细内容可查看README.md
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
