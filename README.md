# 长沙学院-校园双创服务平台
[![Build Status](https://travis-ci.org/notobject/ccsu-micro-platform-projects.svg?branch=master)](https://travis-ci.org/notobject/ccsu-micro-platform-projects)
[![License](https://img.shields.io/badge/LICENSE-Apache2.0-ff69b4.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)
## 一、介绍

### 逻辑架构

### 物理架构

### 目录结构

## 二、文档
 
 - [API 文档](https://github.com/notobject/ccsu-micro-platform-projects/edit/master/README.md#)

## 三、准备

### 系统&工具版本参考
- CentOS = 7.x
- JDK >= 1.8
- Maven >= 3.6.0
- Git >= 2.20.0
- Go >= 1.1
- Nginx 
- Mysql
- Redis
- RabbitMQ

### 服务器资源分配参考
为能够最大程度模拟集群部署，请至少准备4台云服务器 or 个人电脑 or 虚拟机：

序号|标签|用途|备注
--|--|--|--
1|ServerA|服务集群|模拟A地机房
2|ServerB|服务集群|模拟B地机房
3|ServerC|持久化集群| 这个非重点，用一台模拟集群环境
4|ServerD|服务治理平台| 这个可以选择在自己本地部署

## 四、开始
### step 1. Mysql安装

### step 2. Redis安装

### step 3. RabbitMQ安装

### step 4. MP-Agent

### step 5. Control-Center

### step 6. Register-Server

#### 注册中心:ServiceA 120.78.82.47

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

// 推送镜像到远程，

docker push notobject/mp-base:register-center

#### 注册中心:ServiceB 39.106.96.220

docker pull notobject/mp-base:register-center

docker run -d --name register-server -p 8761:8761 -t notobject/mp-base:register-center -v /var/log/:/var/log/ --restart=always  --eureka.instance.ip-address=39.106.96.220 --eureka.client.service-url.defaultZone=http://120.78.82.47:8761/eureka/

### step 7. Config-Server

#### ServiceA 120.78.82.47

docker run -d --name config-server -p 8888:8888 -v /var/log/:/var/log/ -t notobject/mp-base:config-center --restart=always --eureka.instance.ip-address=120.78.82.47 --eureka.client.service-url.defaultZone=http://120.78.82.47:8761/eureka/,http://39.106.96.220:8761/eureka/

#### ServiceB 39.106.96.220

docker run -d --name config-server -p 8888:8888 -v /var/log/:/var/log/ -t notobject/mp-base:config-center --restart=always --eureka.instance.ip-address=39.106.96.220 --eureka.client.service-url.defaultZone=http://120.78.82.47:8761/eureka/,http://39.106.96.220:8761/eureka/

### step 8. Proxy-Server

#### ServiceA 120.78.82.47

docker run -d --name proxy-server -p 8000:8000 -v /var/log/:/var/log/ -t notobject/mp-base:proxy-center --restart=always --eureka.instance.ip-address=120.78.82.47 --eureka.client.service-url.defaultZone=http://120.78.82.47:8761/eureka/,http://39.106.96.220:8761/eureka/

#### ServiceB 39.106.96.220

docker run -d --name proxy-server -p 8000:8000 -v /var/log/:/var/log/ -t notobject/mp-base:proxy-center --restart=always --eureka.instance.ip-address=39.106.96.220 --eureka.client.service-url.defaultZone=http://120.78.82.47:8761/eureka/,http://39.106.96.220:8761/eureka/

### step 9. Business-Services

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

### step 10. MP-UI (mini program)

## 五、FAQ
- Any issue or question is welcome, Please feel free to open github issues :)

## 六、Contributors

- [longduping](https://github.com/notobject/ccsu-micro-platform-projects/edit/master/README.md#六、Contributors) 
- [zhanghang](https://github.com/notobject/ccsu-micro-platform-projects/edit/master/README.md#六、Contributors)
- [caoxiaoshuang](https://github.com/notobject/ccsu-micro-platform-projects/edit/master/README.md#六、Contributors)
- [hechong](https://github.com/notobject/ccsu-micro-platform-projects/edit/master/README.md#六、Contributors)

