package com.ningmeng.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RibbonConsumerController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/ribbonConsumer")
    public String ribbonConsumer(@RequestParam String time) {
        // SERVICE-PROVIDER 对应服务的serviceId,即spring.application.name的属性值，大小写均可
        return restTemplate.getForObject("http://SERVICE-PROVIDER/getApplicationName?time=" + time, String.class);
    }
}
