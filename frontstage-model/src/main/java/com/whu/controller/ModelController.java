package com.whu.controller;

import com.entity.Model;
import com.results.CommonResult;
import com.whu.service.ModelFeignService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
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
    public CommonResult importModel(@RequestBody Model model){
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
    public CommonResult selectModelById(@PathVariable("id") Integer id){
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
    public CommonResult updateModelById(@RequestBody Model model){
        return service.updateModelById(model);
    }


    /**
     * 6.3.1.5 查询所有模型
     * @author Jiahan Wang
     * @create 2020-7-22 23:15
     * @updator
     * @update
     * @param pageNum 页码
     * @param pageSize 页面大小
     * @param keyWord 关键字
     * @return
     */
    @ApiOperation(value = "6.3.1.5 查询所有模型",httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "页码",paramType = "query",dataType = "Integer",required = true),
            @ApiImplicitParam(name = "pageSize",value = "页面大小",paramType = "query",dataType = "Integer",required = true),
            @ApiImplicitParam(name = "keyWord",value = "搜索关键字",paramType = "query",dataType = "String",required = true)
    })
    @GetMapping("/models")
    public CommonResult getUserModel(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                                     @RequestParam(value = "pageSize",defaultValue = "6")Integer pageSize,
                                     @RequestParam(value = "keyWord",defaultValue = "")String keyWord){
        return service.getModels(pageNum,pageSize,keyWord);
    }


    /**
     * 6.3.1.8 查询用户下的所有模型
     * @author Jiahan Wang
     * @create 2020-7-22 22:15
     * @updator
     * @update
     * @param userId  用户ID
     * @param pageNum 页码
     * @param pageSize 页面大小
     * @param keyWord 关键字
     * @return
     */
    @ApiOperation(value = "6.3.1.8 查询用户下的所有模型",httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户ID",paramType = "path",dataType = "Integer",required = true),
            @ApiImplicitParam(name = "pageNum",value = "页码",paramType = "query",dataType = "Integer",required = true),
            @ApiImplicitParam(name = "pageSize",value = "页面大小",paramType = "query",dataType = "Integer",required = true),
            @ApiImplicitParam(name = "keyWord",value = "搜索关键字",paramType = "query",dataType = "String",required = true)
    })
    @GetMapping("/models/{userId}")
    public CommonResult getUserModel(@PathVariable("userId")Integer userId,
                                     @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                                     @RequestParam(value = "pageSize",defaultValue = "6")Integer pageSize,
                                     @RequestParam(value = "keyWord",defaultValue = "")String keyWord){

        return service.getUserModels(userId,pageNum,pageSize,keyWord);
    }
}
