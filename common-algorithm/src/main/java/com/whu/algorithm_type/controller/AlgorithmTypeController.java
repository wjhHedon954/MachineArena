package com.whu.algorithm_type.controller;


import com.entity.Algorithm;
import com.entity.AlgorithmType;
import com.mapper.AlgorithmTypeMapper;
import com.results.CommonResult;
import com.whu.algorithm.service.impl.AlgorithmServiceImpl;
import com.whu.algorithm_type.service.IAlgorithmTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Hedon
 * @since 2020-07-11
 */
@RestController
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = "*")
public class AlgorithmTypeController {
    @Autowired
    private IAlgorithmTypeService algorithmTypeService;

    @GetMapping(value = "/algorithm/type")
    public CommonResult getAlgorithmType() {
        return CommonResult.success().add("algorithmType", algorithmTypeService.getAlgorithmType());
    }
}
