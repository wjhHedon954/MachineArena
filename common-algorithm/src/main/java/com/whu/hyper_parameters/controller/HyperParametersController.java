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
