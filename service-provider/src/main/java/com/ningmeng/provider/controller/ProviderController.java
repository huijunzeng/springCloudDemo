package com.ningmeng.provider.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProviderController {

    // 读取配置文件的工程名
    @Value("${spring.application.name}")
    String applicationName;

    @RequestMapping("/getApplicationName")
    public String test(){
        return "===工程名===：" + applicationName;
    }

}
