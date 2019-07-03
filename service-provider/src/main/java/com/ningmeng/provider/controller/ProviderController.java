package com.ningmeng.provider.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProviderController {

    // 读取配置文件的工程名以及端口号
    @Value("${spring.application.name}")
    String applicationName;
    @Value("${server.port}")
    String port;

    @GetMapping("/getApplicationName")
    public String test(@RequestParam String time){
        return time + "===工程名===：" + applicationName + ",===端口号===：" + port;
    }

}
