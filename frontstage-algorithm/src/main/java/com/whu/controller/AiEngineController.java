package com.whu.controller;

import com.results.CommonResult;
import com.whu.service.AlgorithmFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName AiEngineController
 * @Description 获取AI引擎
 * @Author thomas
 * @Date 2020/7/23 3:50 下午
 * @Version 1.0
 */
@RestController
@RequestMapping("/frontstage")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = "*")
public class AiEngineController {
    @Autowired
    AlgorithmFeignService algorithmFeignService;

    @GetMapping("/engines")
    public CommonResult getAiEngines() {

        return algorithmFeignService.getAiEngines();
    }
}
