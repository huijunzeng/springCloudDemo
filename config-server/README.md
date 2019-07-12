spring cloud config分布式配置中心，用来为分布式系统的微服务提供集中化的外部配置支持

配置完成后，启动服务，可通过以下方式访问远程git仓库中的配置文件的内容：
1./{application}/{profile}[/{label}]
2./{application}-{profile}.yml
3./{label}/{application}-{profile}.yml
4./{application}-{profile}.properties
5./{label}/{application}-{profile}.properties

这里举例说明properties文件（yml文件同理），比如config-dev.properties，application为config，profile为dev，假如-后面不接其他后缀时，则profile默认为default，label为分支名称，不写时默认为master

本工程中，在gitee码云的configServer仓库的config-rep文件夹下配置了config.properties以及config-dev.properties，那么可通过
访问http://localhost:3001/config/default获取config.properties的内容，而config-dev.properties可通过http://localhost:3001/config/dev访问，其他访问方式还有http://localhost:3001/config-dev.properties


###################################################################################################
以上结合config-client工程即可实现基础版的配置管理中心，下面对config-server改造实现高可用
在config-server的pom.xml引入eureka的依赖，使得config-server可以注册到eureka注册中心，然后在config-client可以根据serviceId调用

同时更改config-server的properties配置文件，加入eureka注册中心地址配置
以及在main主类中加入@@EnableDiscoveryClient注解将服务注册到注册中心

由于SpringBoot2.x版本将一些基本的端点整合到了actuator，所以也需要在配置文件中开放actuator的bus-refresh端点

注意：请求刷新的接口也由原来SpringBoot1.5.x的/bus/refresh变成/actuator/bus-refresh

之后到config-client工程继续改造
###################################################################################################