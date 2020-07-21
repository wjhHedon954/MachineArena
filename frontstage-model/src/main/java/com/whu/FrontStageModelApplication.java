package com.whu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Hedon Wang
 * @create 2020-07-17 21:57
 */

@SpringBootApplication
@EnableDiscoveryClient      //能够被注册中心所发现
@EnableFeignClients         //支持OpenFeign跨服务调用
@EnableSwagger2
public class FrontStageModelApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrontStageModelApplication.class,args);
    }
}
