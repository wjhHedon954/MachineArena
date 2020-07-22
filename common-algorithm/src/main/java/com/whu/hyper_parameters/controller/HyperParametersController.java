package com.whu.hyper_parameters.controller;


import com.entity.HyperParameters;
import com.whu.hyper_parameters.service.IHyperParametersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Hedon
 * @since 2020-07-11
 */

/**
 * @author Huiri Tan
 * @description 创建超参数
 * @create 2020/7/23 12:24 上午
 * @update 2020/7/23 12:24 上午
 **/
@RestController
public class HyperParametersController {
    @Autowired
    IHyperParametersService hyperParametersService;

    @PostMapping("/algorithm/hyper-parameters")
    public HyperParameters addHyperParameters(@RequestBody HyperParameters hyperParameters) {
        hyperParametersService.addHyperParameter(hyperParameters);
        return hyperParameters;
    }
}
