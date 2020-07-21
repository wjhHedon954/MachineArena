package com.whu.controller;

import com.entity.Model;
import com.results.CommonResult;
import com.whu.service.ModelFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/frontstage")
@RestController
public class ModelController {
    @Autowired
    private ModelFeignService service;

    /**
     * 接口6.3.1.1  导入模型
     * @description 导入模型
     * @author Yi Zheng
     * @create 2020-7-21 13:00
     * @updator
     * @update
     * @param model
     * @return
     */
    @PostMapping("/model")
    CommonResult importModel(@RequestBody Model model){
        return service.importModel(model);
    }


    /**
     * 接口6.3.1.2  根据id查询模型
     * @description 根据id查询模型
     * @author Yi Zheng
     * @create 2020-7-21 13:10
     * @updator
     * @update
     * @param id
     * @return
     */
    @GetMapping("/model/{id}")
    CommonResult selectModelById(@PathVariable("id") Integer id){
        return service.selectModelById(id);
    }


    /**
     * 接口6.3.1.23 根据id删除模型
     * @description 根据id删除模型
     * @author Yi Zheng
     * @create 2020-7-21 13:10
     * @updator
     * @update
     * @param id
     * @return
     */
    @DeleteMapping("/model/{id}")
    public CommonResult deleteModelById(@PathVariable("id") Integer id){
        return service.deleteModelById(id);
    }

    /**
     * 接口6.3.1.23 根据id更改模型
     * @description 根据id更改模型
     * @author Yi Zheng
     * @create 2020-7-21 13:10
     * @updator
     * @update
     * @param model  需要更改的墨香
     * @return int 更改印象的行数
     */
    @PutMapping("/model/")
    CommonResult updateModelById(@RequestBody Model model){
        return service.updateModelById(model);
    }
}
