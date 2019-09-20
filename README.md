# 校园双创服务平台（基于SpringCloud的微服务架构及DevOps实践）

[![Build Status](https://travis-ci.org/notobject/ccsu-micro-platform-projects.svg?branch=master)](https://travis-ci.org/notobject/ccsu-micro-platform-projects)
[![License](https://img.shields.io/badge/LICENSE-Apache2.0-ff69b4.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)
## 一、介绍

### 逻辑架构

![系统逻辑架构](https://raw.githubusercontent.com/notobject/notobject.github.io/master/1.png)

主要分为四层：
1. 负载均衡层：各终端通过域名访问云端，通过DNS服务器解析至负载均衡层，负载均衡层（采用了Nginx）负责通过特定的负载均衡算法将终端请求路由到网关层的某一具体实例。
2. 网关层：网关层是整个系统的唯一入口。负责建立、维护会话连接，并对请求进行多级过滤，确保到达服务后端的请求是确定的和安全的。
3. 微服务层：微服务层又可以细分为基础微服务和业务微服务，其中基础微服务作为整个后端系统的支撑结构，为所有业务微服务所依赖。微服务层主要处理来自终端的业务请求。
4. 数据访问层：Mysql集群，Redis集群和RabbitMQ集群。

### 物理架构

![系统物理架构](https://raw.githubusercontent.com/notobject/notobject.github.io/master/2.png)

### 云控中心架构及模块组成

![云控中心架构](https://raw.githubusercontent.com/notobject/notobject.github.io/master/3.png)

![系统结构](https://raw.githubusercontent.com/notobject/notobject.github.io/master/4.png)

序号|名称|简述
--|--|--
1|服务上线|供开发者使用，提供服务上线前的配置工作，包括服务名配置，资源分配，配置文件定义及代码仓库。
2|服务构建|由系统自动执行，通过拉取预配置代码仓库的程序代码进行自动化构建的过程。
3|服务治理|提供给服务责任人的简单服务管理功能，包括服务的启动，停止，重启，扩容/缩容，回滚，服务降级，熔断等操作。
4|服务配置|针对指定服务的配置文件进行在线修改实时生效，并提供配置回滚能力。
4|状态监控|对服务状态及宿主服务器状态进行实时监控。
4|资源配置|提供给运维人员进行服务器资源配置功能，方便进行资源分配。
4|审计日志|记录持续集成系统内的全链路操作日志，方便故障溯源和排错。


### 目录结构

目录名 | 简述
-------|--
MP-Agent |控制代理，运行于宿主机，通过接收控制中心指令，在服务上执行相应的操作，Go语言实现。采用了RabbitMQ的topic模式用于接收指令，fanout模式用于实时上报执行过程和执行结果。
MP-UI|平台前端，采用微信小程序实现
control-center|控制中心Web端，负责展示系统状态，和用户交互。将命令下发给控制代理，并展示过程和结果。
common-script|一些Shell脚本，主要用于前期自动化部署测试，现已废弃。
common-code|项目公共代码
ccsu-register-server|注册中心，采用了eureka实现，负责服务的注册与发现。
ccsu-config-server| 配置中心，直接采用了Github作为配置仓库，并配置了WebHook和AMQP实现自动更新。 [配置仓库](https://github.com/notobject/config-center)
ccsu-proxy-server|API网关层，采用了Zuul实现。网关层主要做了请求的登录校验，请求头校验，权限校验，会话及用户信息统一管理功能。
ccsu-user-service|用户信息及会话服务，这个主要是为网关层服务。用了Redis进行分布式会话维护。用户信息直接落在Mysql。
ccsu-notify-service| 通知服务，直接提供一对一，一对多的用户提醒功能，包括邮件通知，短信通知，站内通知，基于Websocket做站内主动推送。并且采用了spring cloud stream对发送和接收解耦，该服务为通用服务。
ccsu-main-service| 首页聚合服务，主要聚合Banber推荐，功能菜单栏，Feed流等服务的数据，客户端只需要请求一次这个服务，就能取得首页所有需要渲染的数据。
ccsu-team-service| 团队页聚合服务，效果同上。
ccsu-socre-service|综测服务，统一综测管理，该服务为通用服务。

## 三、准备

### 服务器资源分配

为能够最大程度模拟集群部署，请至少准备4台云服务器 or 个人电脑 or 虚拟机：

序号|标签|用途|备注
--|--|--|--
1|ServerA|服务集群|模拟A地机房
2|ServerB|服务集群|模拟B地机房
3|ServerC|持久化集群| 这个非重点，用一台模拟集群环境
4|ServerD|服务治理平台| 这个可以选择在自己本地部署

### 系统&工具

### centos 配置
  
  略
  
### maven 安装及配置
  
  略
  
### git 安装及配置
  
  略
  
### docker 安装及配置
  
  略
  
### ningx 安装及配置
  
  略
  
### mysql 安装及配置
  
  略
  
### redis 安装及配置
  
  略
  
### rabbitMQ 安装及配置
  
  略
  

## 四、开始

### step 1. Control-Center

  略

### step 2. MP-Agent
  
  略

### step 3. Register-Server

#### 注册中心:ServiceA 120.78.82.47

首先拉取代码并执行编译

```shell

git clone https://github.com/notobject/ccsu-micro-platform-projects build
cd build/
cd ccsu-register-server/
mvn clean package -Dmaven.test.skip=true -Pprod


cp target/*.jar ./app.jar

vim Dockerfile

#-----------------------------下同

FROM java:openjdk-8-jre-alpine

COPY app.jar /app.jar

ENTRYPOINT ["java","-jar","/app.jar"]

#---------------------------------------

docker build -t notobject/mp-base:register-center .

docker run -d --name register-server -p 8761:8761 -t notobject/mp-base:register-center -v /var/log/:/var/log/ --restart=always --eureka.instance.ip-address=120.78.82.47 --eureka.client.service-url.defaultZone=http://39.106.96.220:8761/eureka/

#推送镜像到远程，

docker push notobject/mp-base:register-center

```

#### 注册中心:ServiceB 39.106.96.220

> 拉取远程镜像

docker pull notobject/mp-base:register-center

> 运行容器

docker run -d --name register-server -p 8761:8761 -t notobject/mp-base:register-center -v /var/log/:/var/log/ --restart=always  --eureka.instance.ip-address=39.106.96.220 --eureka.client.service-url.defaultZone=http://120.78.82.47:8761/eureka/

### step 4. Config-Server

#### ServiceA 120.78.82.47

docker run -d --name config-server -p 8888:8888 -v /var/log/:/var/log/ -t notobject/mp-base:config-center --restart=always --eureka.instance.ip-address=120.78.82.47 --eureka.client.service-url.defaultZone=http://120.78.82.47:8761/eureka/,http://39.106.96.220:8761/eureka/

#### ServiceB 39.106.96.220

docker run -d --name config-server -p 8888:8888 -v /var/log/:/var/log/ -t notobject/mp-base:config-center --restart=always --eureka.instance.ip-address=39.106.96.220 --eureka.client.service-url.defaultZone=http://120.78.82.47:8761/eureka/,http://39.106.96.220:8761/eureka/

### step 5. Proxy-Server

#### ServiceA 120.78.82.47

docker run -d --name proxy-server -p 8000:8000 -v /var/log/:/var/log/ -t notobject/mp-base:proxy-center --restart=always --eureka.instance.ip-address=120.78.82.47 --eureka.client.service-url.defaultZone=http://120.78.82.47:8761/eureka/,http://39.106.96.220:8761/eureka/

#### ServiceB 39.106.96.220

docker run -d --name proxy-server -p 8000:8000 -v /var/log/:/var/log/ -t notobject/mp-base:proxy-center --restart=always --eureka.instance.ip-address=39.106.96.220 --eureka.client.service-url.defaultZone=http://120.78.82.47:8761/eureka/,http://39.106.96.220:8761/eureka/

### step 6. Business-Services

#### 以ccsu-user-service 为例

git clone https://github.com/notobject/ccsu-micro-platform-projects build

cd build/

cd ccsu-user-service

mvn clean package -Dmaven.test.skip=true -Pprod

cp target/*.jar ./app.jar

docker build -t notobject/mp-base:user-service .

- ServiceA:

docker run -d --name user-service -p 58080:58080 -v /var/log/:/var/log/ -t notobject/mp-base:user-service  --restart=always --server.port=58080 --eureka.instance.ip-address=120.78.82.47 --eureka.client.service-url.defaultZone=http://120.78.82.47:8761/eureka/,http://39.106.96.220:8761/eureka/

- ServiceB:

docker run -d --name user-service -p 58080:58080 -v /var/log/:/var/log/ -t notobject/mp-base:user-service  --restart=always --server.port=58080 --eureka.instance.ip-address=39.106.96.220 --eureka.client.service-url.defaultZone=http://120.78.82.47:8761/eureka/,http://39.106.96.220:8761/eureka/

### step 7. MP-UI (mini program)
  
  参见微信小程序开发文档
  

## 五、FAQ
- Any issue or question is welcome, Please feel free to open github issues :)

## 六、Contributors

- [longduping](https://github.com/notobject/ccsu-micro-platform-projects/edit/master/README.md#六、Contributors) 
- [zhanghang](https://github.com/notobject/ccsu-micro-platform-projects/edit/master/README.md#六、Contributors)
- [caoxiaoshuang](https://github.com/notobject/ccsu-micro-platform-projects/edit/master/README.md#六、Contributors)
- [hechong](https://github.com/notobject/ccsu-micro-platform-projects/edit/master/README.md#六、Contributors)
- [xiaohaoxiong](https://github.com/notobject/ccsu-micro-platform-projects/edit/master/README.md#六、Contributors)

