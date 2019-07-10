package com.ningmeng.configclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope // 支持刷新
@RestController
public class ConfigClientController {

    @Value("${from}")
    String from;
    @Value("${server.port}")
    String port;

    @RequestMapping("/getRomteConfigurationFromGit")
    public String getRomteConfigurationFromGit(){
        return from + "-------" + port;
    }
}
