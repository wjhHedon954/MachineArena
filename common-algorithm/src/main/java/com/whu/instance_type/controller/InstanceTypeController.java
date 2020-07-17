package com.whu.instance_type.controller;


import com.results.CommonResult;
import com.whu.instance_type.service.IInstanceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Hedon
 * @since 2020-07-14
 */
@RestController
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = "*")
public class InstanceTypeController {
    @Autowired
    IInstanceTypeService instanceTypeService;

    /**
     * @author Huiri Tan
     * @description 查询所有实例类型
     * @create 2020/7/17 11:39 上午
     * @update 2020/7/17 11:39 上午
     * @param []
     * @return com.results.CommonResult
     **/
    @GetMapping("/instanceType")
    public CommonResult getInstanceType() {
        return CommonResult.success().add("instanceType", instanceTypeService.getInstanceType());
    }

}
