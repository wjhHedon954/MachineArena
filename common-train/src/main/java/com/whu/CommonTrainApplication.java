package com.whu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Hedon Wang
 * @create 2020-07-17 11:31
 */

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(value = "com.mapper")
@EnableSwagger2
public class CommonTrainApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommonTrainApplication.class, args);
    }
}
