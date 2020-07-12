package com.whu.algorithm.controller;


import com.constants.ResultCode;
import com.entity.Algorithm;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.responsevo.AlgorithmResponseVo;
import com.results.CommonResult;
import com.whu.algorithm.service.IAlgorithmService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
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
public class AlgorithmController {

    @Autowired
    IAlgorithmService algorithmService;

    /**
     * 接口 6.1.7 编辑算法
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
     * 接口 6.1.6 删除算法
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
     * 接口 6.1.5 根据 ID 查询算法
     *
     * @param id 算法ID
     * @return 通用返回结果
     * @author Yizheng
     * @create 2020-07-11 20:55
     * @updator Jiahan Wang
     * @update 2020-07-12 08:50
     */
    @ApiOperation(value = "接口 6.1.5 根据ID查询算法", httpMethod = "GET", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "算法ID", paramType = "path", dataType = "Integer", required = true)
    })
    @GetMapping(value = "/algorithm/{id}")
    public CommonResult selectById(@PathVariable("id") Integer id) {
        //检查ID是否为空
        if (id == null) {
            return CommonResult.fail(ResultCode.EMPTY_ALGORITHM_ID);
        }
        //执行查询操作
        Algorithm algorithm = algorithmService.getAlgorithmById(id);
        //如果对象为空，则说明该算法不存在
        if (algorithm == null) {
            return CommonResult.fail(ResultCode.ALGORITHM_NOT_EXIST);
        }
        //若一切正常，则将该算法对象封装到 extend 中传给前端
        return CommonResult.success().add("algorithm", algorithm);
    }


    /**
     * 接口 6.1.1 分页查询算法
     *
     * @return 通用查询到的分页数据
     * @author Jiahan Wang
     * @create 2020-07-12 14:55
     * @updator Jiahan Wang
     * @update 2020-07-12 14:55
     */
    @ApiOperation(value = "接口 6.1.1 分页查询算法",httpMethod = "GET",notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "当前页码",paramType = "query",dataType = "Integer",required = true),
            @ApiImplicitParam(name = "pageSize",value = "页面大小",paramType = "query",dataType = "Integer",required = true)
    })
    @GetMapping("/algorithms")
    public CommonResult selectAllAlgorithms(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                                            @RequestParam(value = "pageSize",defaultValue = "6")Integer pageSize,
                                            @RequestParam(value = "keyWord")String keyWord){
        //1. 开启分页查询
        PageHelper.startPage(pageNum,pageSize);
        //2. 从数据库拉取信息
        List<AlgorithmResponseVo> algorithmResponseVos =  algorithmService.getAllFullAlgorithms(keyWord);
        //3. 封装到 pageInfo 中
        PageInfo pageInfo = new PageInfo(algorithmResponseVos,5);
        //4. 传给前端
        return CommonResult.success().add("pageInfo",pageInfo);
    }

}
