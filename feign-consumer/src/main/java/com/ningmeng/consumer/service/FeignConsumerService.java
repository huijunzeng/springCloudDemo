package com.ningmeng.consumer.service;

import com.ningmeng.consumer.service.fallback.FeignConsumerHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-provider", fallback = FeignConsumerHystrix.class,configuration = {

})//绑定该接口对应service-provider服务  fallback指定的类必须实现@FeignClient标记的接口
public interface FeignConsumerService {

    /**
     * @RequestMapping属性要跟service-provider服务暴露初开的接口的属性一样  比如post请求方法要对应post请求方法,请求路径名要相同等等
     * 方法中的参数也要与暴露的接口的参数一致(参数数量一致，名称一致（若名称不一致，要通过设置value同名）)
     * @return
     */
    @GetMapping("/getApplicationName")
    String feignConsumer(@RequestParam(value = "time") String time);

}
