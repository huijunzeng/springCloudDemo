spring cloud config分布式配置中心，用来为分布式系统的微服务提供集中化的外部配置支持

配置文件为bootstrap.yml, 会比application.yml文件优先加载，搭配spring cloud config使用application.yml里面定义的文件可以实现动态替换

分别启动config-server以及config-client工程，访问测试(这里用的是bootstrap.yml中被注释掉的那部分配置信息)

若存在同名但文件类型不同的文件，比如test.properties和test.yml时，优先读取.properties文件
若同时存在本地配置配件和远程配置文件，比如application.yml和bootstrap.yml时，那么读取的是远程bootstrap.yml文件

#################################################################################
为了实现spring cloud config高可用，在改造完config-server工程后，接着在config-client的pom.xml文件中加入eureka的依赖（已经从父pom.xml文件自动继承了）：
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>

为了配置可动态刷新
在config-client的pom.xml中新增spring-boot-starter-actuator监控模块（已经从父pom.xml文件自动继承了），其中包含了/refresh刷新API：
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>

修改bootstrap.yml配置文件

以及在main主类添加@EnableDiscoveryClient使得可以通过在注册中心发现config-server配置服务器的serviceId实现配置文件信息的拉取，同时在用到@Value的controller层的头部加上此注解 `@RefreshScope`，使其能获取刷新后的信息


分别依次重新启动eureka-server、config-server以及config-client工程，访问测试，然后修改gitee中的配置文件内容，刷新访问，返回内容没有发生改变，之后用postman发送post请求http://localhost:8888/actuator/bus-refresh，发现服务重新拉取配置信息，再次访问会发现配置已刷新了。（注意：之前的SpringBoot1.x版本用的是http://localhost:8888/bus/refresh）

#################################################################################