package com.whu.controller;

import com.whu.service.AlgorithmFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Hedon Wang
 * @create 2020-07-14 10:50
 */

@RestController
@RequestMapping("/backstage")
public class AlgorithmController {


    @Autowired
    AlgorithmFeignService algorithmFeignService;


}
