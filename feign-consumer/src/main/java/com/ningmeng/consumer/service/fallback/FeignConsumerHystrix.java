package com.ningmeng.consumer.service.fallback;

import com.ningmeng.consumer.service.FeignConsumerService;
import org.springframework.stereotype.Component;

@Component
public class FeignConsumerHystrix implements FeignConsumerService {

    /**
     * 熔断降级服务ribbonConsumerHystrix方法与ribbonConsumer方法参数以及返回值类型要一致
     * @param time
     * @return
     */
    @Override
    public String feignConsumer(String time) {
        return "hystrix熔断：调用接口失败，服务降级，time=" + time;
    }
}
