先访问地址http://localhost:5002/hystrix

参数说明：
turbine.aggregator.cluster-config参数设定cluster名字，当使用default，默认聚合turbine.appConfig中设定的所有服务名的数据；
turbine.app-config参数设定需要收集监控信息的服务名；
turbine.cluster-name-expression 参数指定了集群名称为 default，当我们服务数量非常多的时候，可以启动多个 Turbine 服务来构建不同的聚合集群，而该参数可以用来区分这些不同的聚合集群，同时该参数值可以在 Hystrix 仪表盘中用来定位不同的聚合集群，只需要在 Hystrix Stream 的 URL 中通过 cluster 参数来指定；

最后依次启动eureka-server、service-provider(两个实例)、feign-consumer、ribbon-consumer以及hystrix-turbine工程，

turbine.aggregator.cluster-config为default时访问http://localhost:5002/hystrix

turbine.aggregator.cluster-config为注册的服务名时访问http://localhost:5002/turbine.stream?cluster=服务名
