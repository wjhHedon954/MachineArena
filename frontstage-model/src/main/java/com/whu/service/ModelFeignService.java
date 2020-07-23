package com.whu.service;

import com.entity.Model;
import com.entity.ModelDescription;
import com.results.CommonResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

/**
 * @author Hedon Wang
 * @create 2020-07-17 21:58
 */

@Service
@FeignClient(value = "common-model")
public interface ModelFeignService {

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
    CommonResult importModel(@RequestBody Model model);


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
    CommonResult selectModelById(@PathVariable("id") Integer id);


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
    CommonResult deleteModelById(@PathVariable("id") Integer id);

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
    CommonResult updateModelById(@RequestBody Model model);

    @GetMapping("/models/{userId}")
    CommonResult getUserModels(@PathVariable("userId")Integer userId,
                                     @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                                     @RequestParam(value = "pageSize",defaultValue = "6")Integer pageSize,
                                     @RequestParam(value = "keyWord",defaultValue = "")String keyWord);


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
    @GetMapping("/models")
    CommonResult getModels(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                           @RequestParam(value = "pageSize",defaultValue = "6")Integer pageSize,
                           @RequestParam(value = "keyWord",defaultValue = "")String keyWord);

    /**
     * @author Huiri Tan
     * @description 保存模型描述
     * @create 2020/7/23 1:32 上午
     * @update 2020/7/23 1:32 上午
     * @param
     * @return
     **/

    @PostMapping("/model-description")
    ModelDescription addModelDescription(ModelDescription modelDescription);
}
