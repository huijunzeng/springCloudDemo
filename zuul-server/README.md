Zuul，通过它根据用户请求转发到具体的服务中去，Spring官方出了Spring Cloud Gateway代替它了。

引入依赖spring-cloud-starter-netflix-zuul

配置路由规则有两种方式：
一：配置url
比如:
spring.cloud.
zuul.routes.ribbon.path=/ribbon/**
zuul.routes.ribbon.url=http://localhost:3001/  #url对应的是ribbon-consumer服务的访问地址

二：根据注册中心注册服务的serviceId（需引入注册中心）
比如：
zuul.routes.ribbon.path=/ribbon/**
zuul.routes.ribbon.service-id=ribbon-consumer #ribbon-consumer服务的spring.application.name（即注册到注册中心的serviceId）
