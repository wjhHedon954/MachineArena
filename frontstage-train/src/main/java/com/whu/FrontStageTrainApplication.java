package com.whu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Hedon Wang
 * @create 2020-07-17 11:37
 */

@SpringBootApplication
@EnableDiscoveryClient      //能够被注册中心所发现
@EnableFeignClients         //支持OpenFeign跨服务调用
public class FrontStageTrainApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrontStageTrainApplication.class, args);
    }
}
