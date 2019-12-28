package com.leyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * spring cloud: 在springboot的基础上构建的微服务框架
 *  eureka: 注册中心，服务的注册与发现
 *      服务端：
 *         1 引入服务端启动器，eureka-server
 *         2 添加了配置 spring.application.name服务名
 *                      eureka.client.service-url.defaultZone      -> http://localhost:10086/eruka
 *         3 @EnableEurekaServer   开启eureka服务端功能
 */

@SpringBootApplication
@EnableEurekaServer
public class LeyouRegistryApplication {
    public static void main(String[] args) {

        SpringApplication.run(LeyouRegistryApplication.class);
    }
}
