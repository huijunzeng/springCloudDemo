package com.ningmeng.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ConsumerController {

    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired// 利用RestTemplate对象实现对服务提供者接口的调用  本例中是对service-provider服务的test方法的调用
    RestTemplate restTemplate;

    @GetMapping("/consumer1")
    public String consumer1(@RequestParam String time) {
        ServiceInstance serviceInstance = loadBalancerClient.choose("service-provider");// 选取一个服务实例
        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/getApplicationName?time=" + time; // 拼接调用接口如http://localhost:1001/getApplicationName
        System.out.println("-------serviceInstance1:" + serviceInstance.toString());
        System.out.println(url);
        return restTemplate.getForObject(url, String.class);
    }

    @GetMapping("/consumer2")
    public String consumer2(@RequestParam String time) {
        List<ServiceInstance> serviceInstanceList = discoveryClient.getInstances("service-provider");
        ServiceInstance serviceInstance = serviceInstanceList.get(0);
        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/getApplicationName?time=" + time; // 拼接调用接口如http://localhost:1001/getApplicationName
        System.out.println("======serviceInstance2:" + serviceInstance.toString());
        System.out.println(url);
        return restTemplate.getForObject(url, String.class);
    }

}
