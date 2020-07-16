package com.whu.ai_engine.controller;


import com.entity.AiEngine;
import com.results.CommonResult;
import com.whu.ai_engine.service.IAiEngineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Hedon
 * @since 2020-07-11
 */
@RestController
public class AiEngineController {
    @Autowired
    IAiEngineService aiEngineService;

    /**
     * @author Huiri Tan
     * @description 查询所有的ai引擎及 python版本
     * @create 2020/7/16 9:04 下午
     * @update 2020/7/16 9:04 下午
     * @param []
     * @return java.util.List<com.entity.AiEngine>
     **/
    @GetMapping("algorithm/engines")
    public CommonResult getAiEngines() {

        return CommonResult.success().add("engines", aiEngineService.getAiEngines());
    }
}
