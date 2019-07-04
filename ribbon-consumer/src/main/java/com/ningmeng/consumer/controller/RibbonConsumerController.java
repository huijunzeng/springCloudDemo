package com.ningmeng.consumer.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RibbonConsumerController {

    @Autowired
    RestTemplate restTemplate;

    /**
     * 熔断降级服务ribbonConsumerHystrix方法与ribbonConsumer方法参数以及返回值类型要一致
     * 当熔断器打开即触发熔断后，每隔五秒会尝试连接，看一下是否能够正常执行，如果能够正常执行，那么熔断自动恢复，否则继续处于熔断状态。
     * @return
     */
    @HystrixCommand(fallbackMethod = "ribbonConsumerHystrix", //指定熔断服务降级方法
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1000"),//指定多久超时，单位毫秒,超时进fallbackMethod，默认事件为1000毫秒
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),//判断熔断的最少请求数，默认是10；只有在一个统计窗口内处理的请求数量达到这个阈值，才会进行熔断与否的判断
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50") //判断熔断的阈值，表示在一个统计窗口内有50%的请求处理失败，会触发熔断，默认是50
            })
    @GetMapping("/ribbonConsumer")
    public String ribbonConsumer(@RequestParam String time) {
        // SERVICE-PROVIDER 对应服务的serviceId,即spring.application.name的属性值，大小写均可
        return restTemplate.getForObject("http://SERVICE-PROVIDER/getApplicationName?time=" + time, String.class);
    }

    /**
     * 熔断服务降级方法
     * @return
     */
    public String ribbonConsumerHystrix(@RequestParam String time) {
        return "hystrix熔断：调用接口失败，服务降级，time=" + time;
    }
}
