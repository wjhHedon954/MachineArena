package com.whu.controller;

import com.results.CommonResult;
import com.whu.service.AlgorithmFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName InstanceTypeController
 * @Description TODO
 * @Author thomas
 * @Date 2020/7/23 12:22 上午
 * @Version 1.0
 */

@RestController
@RequestMapping("/frontstage")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = "*")
public class InstanceTypeController {

    @Autowired
    AlgorithmFeignService algorithmFeignService;

    @GetMapping("/instanceType")
    public CommonResult getInstanceType() {
        return algorithmFeignService.getInstanceType();
    }
}
