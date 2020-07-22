package com.whu.controller;

import com.results.CommonResult;
import com.whu.service.AlgorithmFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName AlgorithmTypeController
 * @Description TODO
 * @Author thomas
 * @Date 2020/7/22 11:36 下午
 * @Version 1.0
 */
@RestController
@RequestMapping("/frontstage")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = "*")
public class AlgorithmTypeController {

    @Autowired
    AlgorithmFeignService algorithmFeignService;

    @GetMapping("/algorithm/type")
    public CommonResult getAlgorithmType() {
        return algorithmFeignService.getAlgorithmType();

    }
}
