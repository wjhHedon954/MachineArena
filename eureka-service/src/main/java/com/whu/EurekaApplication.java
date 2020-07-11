package com.whu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author Hedon Wang
 * @create 2020-07-10 17:41
 */

@SpringBootApplication
@EnableEurekaServer //表明是服务注册中心的Server端
public class EurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication.class,args);
    }
}
