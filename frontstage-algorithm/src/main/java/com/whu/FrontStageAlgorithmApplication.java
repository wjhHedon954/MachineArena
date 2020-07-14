package com.whu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Hedon Wang
 * @create 2020-07-14 10:18
 */

@SpringBootApplication
@EnableDiscoveryClient      //能够被注册中心所发现
@EnableFeignClients         //支持OpenFeign跨服务调用
public class FrontStageAlgorithmApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrontStageAlgorithmApplication.class,args);
    }

}
