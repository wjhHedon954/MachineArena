package com.whu.model.controller;


import com.constants.ResultCode;
import com.entity.Model;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.responsevo.ModelResponseVo;
import com.results.CommonResult;
import com.whu.model.service.impl.ModelServiceImpl;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.jws.WebParam;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Hedon
 * @since 2020-07-19
 */
@RestController
public class ModelController {
    @Autowired
    private ModelServiceImpl service;


    /**
     * 接口6.3.1.1  导入模型
     * @description 导入模型
     * @author Yi Zheng
     * @create 2020-7-19 12:00
     * @updator
     * @update
     * @param model
     * @return
     */
    @PostMapping("/model")
    public CommonResult importModel(@RequestBody Model model){
        //判断参数是否为空
        if (model==null)
            return CommonResult.fail(ResultCode.EMPTY_PARAM);
        //执行insert操作
        int i = service.importModel(model);
        //判断insert是否成功
        if (i==0)
            return CommonResult.fail(ResultCode.INSERT_ERROR);

        return CommonResult.success();
    }


    /**
     * 接口6.3.1.2  根据id查询模型
     * @description 根据id查询模型
     * @author Yi Zheng
     * @create 2020-7-18 12:30
     * @updator
     * @update
     * @param id
     * @return
     */
    @GetMapping("/model/{id}")
    public CommonResult selectModelById(@PathVariable("id") Integer id){
        //判断参数是否为空
        if (id==null)
            return CommonResult.fail(ResultCode.EMPTY_PARAM);

        //执行查询操作
        Model model = service.selectModelById(id);
        //判断查询是否成功
        if (model==null)
            return CommonResult.fail(ResultCode.NO_MODEL_IN_DATABASE);

        return CommonResult.success().add("model",model);
    }


    /**
     * 接口6.3.1.23 根据id删除模型
     * @description 根据id删除模型
     * @author Yi Zheng
     * @create 2020-7-18 13:00
     * @updator
     * @update
     * @param id
     * @return
     */
    @DeleteMapping("/model/{id}")
    public CommonResult deleteModelById(@PathVariable("id") Integer id){
        //判断参数是否为空
        if (id==null)
            return CommonResult.fail(ResultCode.EMPTY_PARAM);

        //执行删除操作
        int i = service.deleteModelById(id);
        //判断删除是否成功
        if (i==0)
            return CommonResult.fail(ResultCode.DELETE_ERROR);

        return CommonResult.success();
    }

    /**
     * 接口6.3.1.23 根据id更改模型
     * @description 根据id更改模型
     * @author Yi Zheng
     * @create 2020-7-18 13:30
     * @updator
     * @update
     * @param model  需要更改的墨香
     * @return int 更改印象的行数
     */
    @PutMapping("/model/")
    public CommonResult updateModelById(@RequestBody Model model){
        //判断参数是否为空
        if (model==null)
            return CommonResult.fail(ResultCode.EMPTY_PARAM);

        //执行更新操作
        int i = service.updateModel(model);
        //判断更新是否成功
        if (i==0)
            return CommonResult.fail(ResultCode.DELETE_ERROR);

        return CommonResult.success();
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
    public CommonResult getModels(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                                     @RequestParam(value = "pageSize",defaultValue = "6")Integer pageSize,
                                     @RequestParam(value = "keyWord",defaultValue = "")String keyWord){
        //1. 开启分页查询
        PageHelper.startPage(pageNum,pageSize,true,true,true);
        //2. 从数据库拉取数据
        List<ModelResponseVo> models =  service.getModels(keyWord);
        //3. 封装到 PageInfo 中
        PageInfo pageInfo = new PageInfo(models,5);
        //4. 传给前端
        return CommonResult.success().add("pageInfo",pageInfo);

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
        //1. 检查 UserId 是否为空
        if (userId == null){
            return CommonResult.fail(ResultCode.EMPTY_USER_ID);
        }
        //2. 开启分页查询
        PageHelper.startPage(pageNum,pageSize,true,true,true);
        //3. 从数据库拉取数据
        List<ModelResponseVo> models =  service.getUserModel(userId,keyWord);
        //4. 封装到 PageInfo 中
        PageInfo pageInfo = new PageInfo(models,5);
        //5. 传给前端
        return CommonResult.success().add("pageInfo",pageInfo);

    }
}
