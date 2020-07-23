package com.whu.model_description.controller;


import com.entity.ModelDescription;
import com.results.CommonResult;
import com.whu.model_description.service.IModelDescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Hedon
 * @since 2020-07-19
 */
@RestController
public class ModelDescriptionController {

    @Autowired
    IModelDescriptionService iModelDescriptionService;

    /**
     * @author Huiri Tan
     * @description 保存模型描述
     * @create 2020/7/23 1:27 上午
     * @update 2020/7/23 1:27 上午
     * @param [modelDescription]
     * @return com.entity.ModelDescription
     **/
    @PostMapping("/model-description")
    public ModelDescription addModelDescription(@RequestBody ModelDescription modelDescription) {
        iModelDescriptionService.addModelDescription(modelDescription);
        return modelDescription;
    }
}
