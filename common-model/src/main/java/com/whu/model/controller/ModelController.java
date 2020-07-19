package com.whu.model.controller;


import com.constants.ResultCode;
import com.entity.Model;
import com.results.CommonResult;
import com.whu.model.service.impl.ModelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
