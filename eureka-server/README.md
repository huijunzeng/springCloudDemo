spring cloud eureka服务注册中心，用于服务的注册与发现

eureka的配置有四部分（详细请自行搜索文档）：
1.instance：当前Eureka Instance实例信息配置（基本用不到）
2.client：Eureka Client客户端特性配置
3.server：Eureka Server注册中心特性配置：
  enableSelfPreservation=true  是否开启自我保护
4.dashboard：Eureka Server注册中心仪表盘配置

若需要将Eureka注册中心组件更换为Consul，可以省掉这个子聚合工程，因为Consul自身提供了服务端，只需把服务提供方的依赖
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
</dependency>

替换为：
<dependency>
  <groupId>org.springframework.cloud</groupId>
  <artifactId>spring-cloud-starter-consul-discovery</artifactId>
</dependency>

同时把服务提供方的配置文件的eureka注册中心地址配置：
eureka.client.serviceUrl.defaultZone=http://localhost:8761/eureka/
替换为：
spring.cloud.consul.host=localhost
spring.cloud.consul.port=8500
最后到Consul官网下载consul程序启动运行，然后启动服务提供方，访问http://localhost:8500即可看到Consul控制台以及被注册的服务！
8500端口是Consul控制台默认访问端口
