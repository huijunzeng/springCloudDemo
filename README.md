本学习案例的框架版本：
SpringBoot (2.1.4.RELEASE)
Spring Cloud (Greenwich.RELEASE)

注意：最好不要在父pom.xml引入`spring-boot-starter-web`依赖，因为后面介绍的Spring Cloud Gateway网关服务时，由于`spring-cloud-starter-gateway`依赖包含的`spring-boot-starter-webflux`子依赖会与它产生冲突（都存在的情况下默认会用的是`spring-boot-starter-web`，那么此时启动gateway就会报错），后者属于传统的Web框架（在Spring Framework 5版本之前用的都是这个，底层用的是Servlet3.0或之前的版本），前者则属于一个新型的Web框架（在Spring Framework 5版本之后用的都是这个，底层用的是Servlet3.1以上版本），最大区别在于前者对异步非阻塞的支持（可以自行搜索相关资料）。

学习顺序：
1.Eureka
2.Ribbon
3.Feign
4.Hystrix（Dashboard/Turbine）
5.Spring Cloud Config(Bus)
6.Zuul
7.Spring Cloud Gateway
8.Spring Cloud Sleuth