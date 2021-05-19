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
- 服务网关 （Gateway、Zuul(分裂Zuul2)）
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
### 2.2 Zookeeper
> Zookeeper是一个分布式协调工具,可以实现注册中心功能

zookeeper 可以代替 eureka 成为springCloud的注册中心

### 2.3 Consul
docker 安装启动 Consul
```bash
$ docker pull consul # 默认拉取latest
$ docker pull consul:1.6.1 # 拉取指定版本
docker run -d -p 8500:8500 --restart=always --name=consul consul:latest agent -server -bootstrap -ui -node=1 -client='0.0.0.0'
```
参数
- agent: 表示启动 Agent 进程。
- server：表示启动 Consul Server 模式
- client：表示启动 Consul Cilent 模式。
- bootstrap：表示这个节点是 Server-Leader ，每个数据中心只能运行一台服务器。技术角度上讲 Leader 是通过 Raft 算法选举的，但是集群第一次启动时需要一个引导 Leader，在引导群集后，建议不要使用此标志。
- ui：表示启动 Web UI 管理器，默认开放端口 8500，所以上面使用 Docker 命令把 8500 端口对外开放。
- node：节点的名称，集群中必须是唯一的，默认是该节点的主机名。
- client：consul服务侦听地址，这个地址提供HTTP、DNS、RPC等服务，默认是127.0.0.1所以不对外提供服务，如果你要对外提供服务改成0.0.0.0
- join：表示加入到某一个集群中去。 如：-json=192.168.0.11。
### 2.4 Nacos
> Name Config Service 代替注册中心，配置中心
集群部署： 借助nginx 进行负载
### 2.5 四者之间的区别
> CAP Consistency代表强一致性 Availability代表可用性 Partition tolerance分区容错性（分布式必备）
CAP 只能满足两种
- AP(Eureka)
- CP(Zookeeper、Consul)
- AP和CP都支持的(Nacos)
  ![注册中心区别](https://img-blog.csdnimg.cn/20210518224616690.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3poajUyNjY2,size_16,color_FFFFFF,t_70)
## 3 负载均衡
- LB负载均衡

简单的说就是将用户的请求平摊的分配到多个服务上，从而达到系统的HA(高可用)
常见的负载均衡软件Nginx、LVS，硬件F5等

- Ribbon 本地负载均衡与Nginx的区别

Nginx 是服务端实现的负载均衡，nginx 可以转发请求
Ribbon 本地负载均衡，是在调用微服务接口，会从注册中心获取服务信息列表缓存在JVM.从而在本地实现RPC远程服务调用技术
### 3.1 Ribbon 
Eureka 默认包含Ribbon的依赖，可以实现默认轮询的负载均衡机制
更改默认的负载均衡，需要在spring 包扫描范围之外配置，然后在启动类加注解引入

### 3.2 OpenFeign
编写 java 客户端变得容易。
前面在使用Ribbon+RestTemplate,使用RestTemplate对http请求的封装。
Feign是一个声明实的客服端，让编写Web服务客户端变得非常容易，只需要在接口上添加对应注解即可

## 4 熔断降级
- 服务降级 服务调用失败给出友好的反馈
  1. 程序运行异常
  2. 超时
  3. 服务熔断触发降级
  4. 线程池/信号量 打满
- 服务熔断 达到最大访问后，拒绝服务，拉闸限电
- 服务限流 秒杀高并发，避免一窝蜂打入，排队获取请求

## 5 网关
### 5.1 Gateway
SpringCloud Gateway使用的是Webflux中的reactor-netty响应式编程组件,底层使用了Netty通讯框架
Gateway特性
- 动态路由：能够匹配任何请求属性
- 可以对路由指定Predicate(断言)和Filter(过滤器)
- 集成Hystrix 断路器功能
- 集成Spring Cloud 服务发现功能
- 易于编写Predicate(断言)和Filter(过滤器)
- 请求限流

三大核心概念 
1. Route(路由) 路由是构建网关的基本模块,它由ID,目标URI,一系列的断言和过滤器组成,如断言为true则匹配该路由
2. Predicate(断言) 
   参考的是Java8的java.util.function.Predicate
   开发人员可以匹配HTTP请求中的所有内容(例如请求头或请求参数),如果请求与断言相匹配则进行路由
3. Filter(过滤)
   指的是Spring框架中GatewayFilter的实例,使用过滤器,可以在请求被路由前或者之后对请求进行修改.

## 6 配置中心
### 6.1 Config + buss
config 实现配置中心加配置客户端  可以实现读取github等平台仓库内的配置文件
加入bus 是因为客户端无法实时获取修改后的配置，需要消息总线进行通知刷新（不使用bus只能一个客户端一个客户端的通知）

## 7 消息驱动
### 7.1 Stream
- 为什么使用 ？
  屏蔽底层消息中间件的差异，降低切换成本，统一消息的编程模型
  
Binder 绑定器连接消息中间件，屏蔽差异 Input消费者，Output生产者
Channel：通道，是队列Queue的一种抽象，在消息通讯系统中就是实现存储和转发的媒介，通过channel对队列进行配置
Source和Sink：简单的可理解为参照对象是Springcloud Stream自身，从Stream发布消息就是输出，接受消息就是输入

## 8 分布式链路跟踪
### 8.1 Sleuth
- 安装zipkin： docker run -d -p 9411:9411 openzipkin/zipkin

Sleuth提供了一套完整的服务跟踪的解决方案，在分布式系统中提供追踪解决方案并且兼容支持了zipkin