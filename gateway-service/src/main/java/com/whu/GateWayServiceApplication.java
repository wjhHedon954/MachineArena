package com.whu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Hedon Wang
 * @create 2020-07-21 15:03
 */

@SpringBootApplication
@EnableDiscoveryClient
public class GateWayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GateWayServiceApplication.class,args);
    }
}
