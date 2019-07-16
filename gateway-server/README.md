Spring Cloud Gateway，通过它根据用户请求转发到具体的服务中去，可以代替Zuul。

引入依赖spring-cloud-starter-gateway，由于包含了spring-boot-starter-webflux依赖，不能同时存在spring-boot-starter-webflux与spring-boot-starter-web，所以需要移除spring-boot-starter-web，不然报错：
**Parameter 0 of method modifyRequestBodyGatewayFilterFactory in org.springframework.cloud.gateway.config.GatewayAutoConfiguration required a bean of type 'org.springframework.http.codec.ServerCodecConfigurer' that could not be found.**



配置路由规则有两种方式（跟Zuul一样，由于用到list数组，所以最好用yml配置文件）：
一：配置url
比如:
spring:
  cloud:
    gateway:
      routes:
        ##ribbon-consumer调用
        - id: ribbon
          uri: http://localhost:3001/
          predicates:
            - Path=/ribbon/**
          filters:
            - StripPrefix=1
        ##feign-consumer调用
        - id: feign
          uri: http://localhost:4001/
          predicates:
            - Path=/feign/**
          filters:
            - StripPrefix=1

二：根据注册中心注册服务的serviceId（需引入注册中心）
比如：
spring:
  cloud:
    gateway:
      routes:
        ##ribbon-consumer调用
        - id: ribbon
          uri: lb://ribbon-consumer
          predicates:
            - Path=/ribbon/**
          filters:
            - StripPrefix=1
        ##feign-consumer调用
        - id: feign
          uri: lb://feign-consumer
          predicates:
            - Path=/feign/**
          filters:
            - StripPrefix=1