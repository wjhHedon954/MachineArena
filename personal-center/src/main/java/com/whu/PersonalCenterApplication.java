package com.whu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Hedon Wang
 * @create 2020-07-20 10:00
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2
@MapperScan(value = "com.mapper")
public class PersonalCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonalCenterApplication.class,args);
    }
}
