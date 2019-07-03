package com.ningmeng.consumer.controller;

import com.ningmeng.consumer.service.FeignConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignConsumerController {

    @Autowired
    FeignConsumerService feignConsumerService;

    @GetMapping("/feignConsumer")
    public String feignConsumer(@RequestParam(value = "time") String time){
        return feignConsumerService.feignConsumer(time);
    }

}
