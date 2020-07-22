package com.whu.controller;

import com.entity.Algorithm;
import com.results.CommonResult;
import com.whu.service.AlgorithmFeignService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Hedon Wang
 * @create 2020-07-14 10:50
 */

@RestController
@RequestMapping("/frontstage")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = "*")
public class AlgorithmController {


    @Autowired
    AlgorithmFeignService algorithmFeignService;


    /**
     * 接口 6.1.1.1 分页查询当前用户的算法
     * @author Jiahan Wang
     * @create 2020-07-15 14:59
     * @updator Jiahan Wang
     * @upadte 2020-07-15 14:59
     * @param userId    用户ID
     * @param pageNum   当前页吗
     * @param pageSize  页面大小
     * @param keyWord   搜索关键字
     * @return
     */
    @ApiOperation(value = "接口 6.1.1.1 分页查询当前用户的算法 ",httpMethod = "GET",notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "当前用户ID",paramType = "path",dataType = "Integer",required = true),
            @ApiImplicitParam(name = "pageNum",value = "当前页码",paramType = "query",dataType = "Integer",required = true),
            @ApiImplicitParam(name = "pageSize",value = "页面大小",paramType = "query",dataType = "Integer",required = true),
            @ApiImplicitParam(name = "keyWord",value = "搜索关键字",paramType = "query",dataType = "String",required = true)
    })
    @GetMapping("/algorithms/{userId}")
    public CommonResult getAllAlgorithms(@PathVariable(value = "userId")Integer userId,
                                         @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                                         @RequestParam(value = "pageSize",defaultValue = "6")Integer pageSize,
                                         @RequestParam(value = "keyWord",defaultValue = "")String keyWord){

        return algorithmFeignService.getAllAlgorithms(userId,pageNum,pageSize,keyWord);

    }


    /**
     * 接口 6.1.1.2 根据ID查询算法信息
     * @author Jiahan Wang
     * @create 2020-07-15 15:59
     * @updator Jiahan Wang
     * @upadte 2020-07-15 15:59
     * @param algorithmId
     * @return
     */
    @ApiOperation(value = "接口 6.1.1.2 按ID查询算法信息",httpMethod = "GET",notes = "")
    @ApiImplicitParam(value = "algorithmId",name = "算法ID",paramType = "path",dataType = "Integer",required = true)
    @GetMapping("/algorithm/{algorithmId}")
    public CommonResult getAlgorithmBasicById(@PathVariable(value = "algorithmId")Integer algorithmId){
        return algorithmFeignService.getAlgorithmBasicById(algorithmId);
    }


    /**
     * 接口 6.1.1.3 按ID查询算法训练规范
     * @author Jiahan Wang
     * @create 2020-07-15 16:19
     * @updator Jiahan Wang
     * @upadte 2020-07-15 16:19
     * @param algorithmId
     * @return
     */
    @ApiOperation(value = "接口 6.1.1.3 按ID查询算法训练规范",httpMethod = "GET",notes = "")
    @ApiImplicitParam(value = "algorithmId",name = "算法ID",paramType = "path",dataType = "Integer",required = true)
    @GetMapping("/algorithm/trainStandard/{algorithmId}")
    public CommonResult getAlgorithmTrainStandardById(@PathVariable("algorithmId")Integer algorithmId){
        return algorithmFeignService.getAlgorithmTrainStandardById(algorithmId);
    }


    /**
     * 接口 6.1.1.4 按ID查询算法超参规范
     * @author Jiahan Wang
     * @create 2020-07-15 16:32
     * @updator Jiahan Wang
     * @upadte 2020-07-15 16:32
     * @param algorithmId
     * @return
     */
    @ApiOperation(value = "接口 6.1.1.4 按ID查询算法超参规范",httpMethod = "GET",notes = "")
    @ApiImplicitParam(value = "algorithmId",name = "算法ID",paramType = "path",dataType = "Integer",required = true)
    @GetMapping("/algorithm/hyperPara/{algorithmId}")
    public CommonResult getAlgorithmHyperPara(@PathVariable("algorithmId")Integer algorithmId){
        return  algorithmFeignService.getAlgorithmHyperPara(algorithmId);
    }


    /**
     * 接口 6.1.1.7 编辑算法
     *
     * @param algorithm 从前端获取得到一个完整的被用户编辑后的对象
     * @return 返回这个被编辑的对象，不一定有用，如果前端需要可以查看里面的数据
     * @author Yi Zheng
     * @create 2020-07-11 20:00
     * @updator Jiahan Wang
     * @update 2020-07-12 08:30
     */
    @ApiOperation(value = "接口6.1.7", httpMethod = "PUT", notes = "更新算法信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "algorithm", value = "算法对象", paramType = "body", dataType = "Algorithm", required = true)
    })
    @PutMapping(value = "/algorithm")
    public CommonResult updateAlgorithm(@RequestBody Algorithm algorithm) {
        return algorithmFeignService.updateAlgorithm(algorithm);
    }

    /**
     * 接口 6.1.1.6 删除算法
     *
     * @param id 算法的id，要根据这个id来删除算法
     * @return 通用返回结果
     * @author Yi Zheng
     * @create 2020-07-11 20:10
     * @updator Jiahan Wang
     * @update 2020-07-12 08:45
     */
    @ApiOperation(value = "接口 6.1.6 删除算法", httpMethod = "DELETE", notes = "删除算法，不从数据库中真实删除，而是软删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "算法ID", paramType = "path", dataType = "Integer", required = true)
    })
    @DeleteMapping(value = "/algorithm/{id}")
    public CommonResult deleteAlgorithmById(@PathVariable("id") Integer id) {
        return algorithmFeignService.deleteAlgorithmById(id);
    }


    /**
     * 查询算法描述
     * @author Jiahan Wang
     * @create 2020-07-18 14:00
     * @updator Jiahan Wang
     * @update 2020-07-18 14:00
     * @param algorithmId
     * @return
     */
    @GetMapping("/algorithm/description/{algorithmId}")
    public CommonResult getAlgorithmDescription(@PathVariable("algorithmId")Integer algorithmId){
        return algorithmFeignService.getAlgorithmDescription(algorithmId);
    }


}
