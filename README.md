# MachineArena

***********************

大二实训项目：分布式AI训练场



后端技术选型

| 技术                  | 说明                           |
| --------------------- | ------------------------------ |
| SpringBoot            | 容器、基本MVC框架              |
| Spring Cloud          | 分布式微服务框架               |
| Mysql                 | 数据库                         |
| Eureka                | 分布式微服务的注册中心         |
| OpenFeign             | 请求转发 微服务间相互调用      |
| Spring Gateway        | 分布式网关                     |
| Hystrix               | 服务熔断降级                   |
| Spring Security       | 安全认证和授权框架             |
| Druid                 | 数据库连接池                   |
| Mybatis               | 数据持久层框架                 |
| Mybatis Plus          | 简化Mybtis代码                 |
| Mybatis Codegenerator | Mybatis代码生成器              |
| Lombok                | 实体类框架 简化代码            |
| PageHelper            | Mybatis分页查询框架            |
| Swagger2              | 接口文档工具                   |
| Junit                 | 单元测试工具                   |
| log4j                 | 日志工具                       |
| Hutool                | 项目基础工具集                 |
| RibbitMQ              | 消息中间件                     |
| Elasticsearch         | 搜索引擎                       |
| OSS                   | 项目文件对象存储               |
| Redis                 | 项目数据缓存层                 |
| Jmeter                | 高并发压力测试                 |
| Jekins                | 自动化部署                     |
| Ribbon                | 微服务负载均衡                 |
| Nginx                 | 解决跨域、后端集群负载均衡问题 |
| Hystrix Dashboard     | 服务监控                       |
| JWT                   | 用户信息认证                   |
|                       |                                |
|                       |                                |

部署说明

整个项目共部署在5台服务器上

前端项目部署在**1台**与公网连接的服务器上。

后端与研发端项目分布式部署在**2台**内网服务器上，组成一个小型“集群”。

**4台**内网服务器由**2个**小型“集群”组成，来自前端的请求通过Nginx反向代理至内网集群，并实现负载均衡。
![](
https://thomas10011-image.oss-cn-beijing.aliyuncs.com/%E5%88%86%E5%B8%83%E5%BC%8FAI%E8%AE%AD%E7%BB%83%E5%9C%BA%E9%83%A8%E7%BD%B2%E6%9E%B6%E6%9E%84%E5%9B%BE-4K.png)

