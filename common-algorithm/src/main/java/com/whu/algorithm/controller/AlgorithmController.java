package com.whu.algorithm.controller;


import com.constants.ResultCode;
import com.entity.Algorithm;
import com.entity.AlgorithmDescription;
import com.entity.HyperParameters;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.responsevo.AlgorithmFullResponseVo;
import com.responsevo.AlgorithmResponseVo;
import com.results.CommonResult;
import com.whu.algorithm.service.IAlgorithmService;
import com.whu.algorithm_description.service.IAlgorithmDescriptionService;
import com.whu.hyper_parameters.service.IHyperParametersService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Hedon
 * @since 2020-07-11
 */
@RestController
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = "*")
public class AlgorithmController {

    @Autowired
    IAlgorithmService algorithmService;

    @Autowired
    IAlgorithmDescriptionService algorithmDescriptionService;

    @Autowired
    IHyperParametersService hyperParametersService;





    /**
     * 接口 6.1.1.5 创建算法
     * @author Huiri Tan
     * @create 2020-07-11 20:00
     * @updator Huiri Tan
     * @update 2020-07-12 10:30
     * @param algorithm 从前端获取data数据，根据数据创建算法对象
     * @return  返回算法信息
     */
    @ApiOperation(value = "接口6.1.2", httpMethod = "POST", notes = "创建算法")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "data", value = "算法创建信息")
    })
    @PostMapping("/algorithm")
    public Algorithm addAlgorithm(@RequestBody Algorithm algorithm) {
        algorithmService.addAlgorithm(algorithm);
        return algorithm;
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
        //检查对象是否为空
        if (algorithm == null) {
            return CommonResult.fail(ResultCode.EMPTY_OBJECT);
        }
        try {
            //执行更新
            int updateCount = algorithmService.updateAlgorithm(algorithm);
            if (updateCount == 0) {
                //如果更新条数为0，则说明该算法数据不在数据库中，返回数据不存在信息
                return CommonResult.fail(ResultCode.ALGORITHM_NOT_EXIST);
            } else {
                //因为是根据ID来更改，所以情况只有 0 和 1，如果不为 0 那必定是成功
                return CommonResult.success();
            }
        } catch (Exception e) {
            return CommonResult.fail(ResultCode.ERROR);
        }
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
        //检查ID是否为空
        if (id == null) {
            return CommonResult.fail(ResultCode.EMPTY_ALGORITHM_ID);
        }
        //执行删除操作
        try {
            int deleteCount = algorithmService.deleteAlgorithmById(id);
            if (deleteCount == 0) {
                return CommonResult.fail(ResultCode.ALGORITHM_NOT_EXIST);
            } else {
                return CommonResult.success();
            }
        } catch (Exception e) {
            return CommonResult.fail(ResultCode.ERROR);
        }

    }


    /**
     * 分页查询当前用户的算法
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
        //1. 检查用户ID是否为空
        if (userId == null){
            return CommonResult.fail(ResultCode.EMPTY_USER_ID);
        }
        //2. 开启分页查询
        PageHelper.startPage(pageNum, pageSize);
        //3. 从数据库中拉取数据
        List<AlgorithmResponseVo> algorithms = algorithmService.getAlgorithmsByUserId(userId,keyWord);
        //4. 将数据封装到 PageInfo 当中
        PageInfo pageInfo = new PageInfo(algorithms,5);
        //5. 返回给前端
        return CommonResult.success().add("pageInfo",pageInfo);

    }



    /**
     * 分页查询所有算法
     *
     * @return 通用查询到的分页数据
     * @author Jiahan Wang
     * @create 2020-07-12 14:55
     * @updator Jiahan Wang
     * @update 2020-07-12 14:55
     */
    @GetMapping("/algorithms")
    public CommonResult selectAllAlgorithms(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                                            @RequestParam(value = "pageSize",defaultValue = "6")Integer pageSize,
                                            @RequestParam(value = "keyWord",defaultValue = "")String keyWord){
        //1. 开启分页查询
        PageHelper.startPage(pageNum,pageSize);
        //2. 从数据库拉取信息
        List<AlgorithmResponseVo> algorithmResponseVos =  algorithmService.getAllFullAlgorithms(keyWord);
        //3. 封装到 pageInfo 中
        PageInfo pageInfo = new PageInfo(algorithmResponseVos,5);
        //4. 传给前端
        return CommonResult.success().add("pageInfo",pageInfo);
    }




    /**
     * 根据ID查询算法信息
     * @author Jiahan Wang
     * @create 2020-07-15 15:59
     * @updator Jiahan Wang
     * @upadte 2020-07-15 15:59
     * @param algorithmId
     * @return
     */
    @GetMapping("/algorithm/{algorithmId}")
    public CommonResult getAlgorithmBasicById(@PathVariable(value = "algorithmId")Integer algorithmId){
        //判断算法ID是否为空
        if (algorithmId == null){
            return CommonResult.fail(ResultCode.EMPTY_ALGORITHM_ID);
        }
        //查询算法
        AlgorithmFullResponseVo algorithm = algorithmService.getAlgorithmFullInfoById(algorithmId);
        if (algorithm == null){
            return CommonResult.fail(ResultCode.ALGORITHM_NOT_EXIST);
        }
        return CommonResult.success().add("algorithm",algorithm);
    }

    /**
     * @author Huiri Tan
     * @description 根据ID查询算法 返回对应的算法实体
     * @create 2020/7/20 1:26 下午
     * @update 2020/7/20 1:26 下午
     * @param
     * @return
     **/
    @GetMapping("/algorithm/object/{algorithmId}")
    public Algorithm getAlgorithmObjectById(@PathVariable(value = "algorithmId")Integer algorithmId) {
        if (algorithmId == null) {
            return null;
        }
        return algorithmService.getAlgorithmObjectById(algorithmId);
    }

    /**
     * 按ID查询算法超参规范
     * @author Jiahan Wang
     * @create 2020-07-15 16:32
     * @updator Jiahan Wang
     * @upadte 2020-07-15 16:32
     * @param algorithmId
     * @return
     */
    @GetMapping("/algorithm/hyperPara/{algorithmId}")
    public CommonResult getAlgorithmHyperPara(@PathVariable("algorithmId")Integer algorithmId){
        //检查算法ID是否为空
        if (algorithmId == null){
            return CommonResult.fail(ResultCode.EMPTY_ALGORITHM_ID);
        }
        List<HyperParameters> hyperParameters = hyperParametersService.getHyperParaByAlgorithmId(algorithmId);
        if (hyperParameters == null){
            return CommonResult.fail(ResultCode.EMPTY_HYPER_PARA);
        }
        return CommonResult.success()
                    .add("algorithmId",algorithmId)
                    .add("hyperParameters",hyperParameters);
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
        //检查算法ID是否为空
        if (algorithmId == null){
            return CommonResult.fail(ResultCode.EMPTY_ALGORITHM_ID);
        }
        //查询描述
        AlgorithmDescription algorithmDescription = algorithmDescriptionService.getAlgorithmDescription(algorithmId);
        if (algorithmDescription == null) {
            return CommonResult.fail(ResultCode.ALGORITHM_NOT_EXIST);
        }
        return CommonResult.success().add("algorithmDescription",algorithmDescription);
    }
}
