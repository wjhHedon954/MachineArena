package com.whu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Hedon Wang
 * @create 2020-07-11 09:21
 */

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(value = "com.mapper")
public class CommonAlgorithmApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonAlgorithmApplication.class,args);
    }
}
