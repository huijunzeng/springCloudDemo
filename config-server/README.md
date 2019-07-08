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
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-eureka</artifactId>
</dependency>

同时更改config-server的properties配置文件，加入eureka注册中心地址配置：
eureka.client.serviceUrl.defaultZone=http://localhost:1111/eureka/
以及在main主类中加入@@EnableDiscoveryClient注解

之后到config-client工程继续改造
###################################################################################################