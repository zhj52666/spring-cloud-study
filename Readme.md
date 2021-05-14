# Spring-Cloud Study
> 基于分布式的微服务架构当下已经成为相当火爆的技术架构体系。 
> SpringCloud=**分布式微服务架构的一站式解决方案**，是N多种微服务架构落地技术的集合体，俗称微服务全家桶
# 分布式微服务架构关键词
- 服务注册与发现 （Nacos、Consul、Eureka(停更)、Zookeeper）
- 服务调用 （Ribbon、LoadBalance、Open Feign、Feign(停更)）
- 服务熔断 （sentinel、Resilience4j(国内使用少)、Hystrix(停更，国内用的比较多)）
- 负载均衡
- 服务降级
- 服务消息队列
- 配置中心管理 （Nacos、Config）
- 服务网关 （GetWay、Zuul(分裂Zuul2)）
- 服务总线 （Nacos、Bus(停更)）
- 服务监控
- 全链路追踪
- 自动化构建部署
- 服务定时任务调度操作
## 1 SpringCloud 架构选型
官网可以查看SpringBoot与SpringCloud对应版本
https://start.spring.io/actuator/info
### 本项目框架版本选型
- springCloud Hoxton.SR1
- SpringCloud Alibaba 2.1.0.RELEASE
- springBoot 2.2.2.RELEASE
- JAVA 8
- MAVEN 3.5+
- MySQL 5.7+
### 升级惨案
- 被动修复bug
- 不再接受合并请求
- 不再发布新版本
## 2 注册中心
> 便于服务的统一调度和管理
### 2.1 Eureka
注册中心可以配置集群，避免单点故障