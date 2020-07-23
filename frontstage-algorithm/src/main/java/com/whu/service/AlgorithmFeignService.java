package com.whu.service;

import com.entity.Algorithm;
import com.entity.AlgorithmDescription;
import com.entity.HyperParameters;
import com.entity.ModelDescription;
import com.results.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

/**
 * @author Hedon Wang
 * @create 2020-07-14 10:57
 */
@Service
@FeignClient(value = "common-algorithm")
public interface AlgorithmFeignService {


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
    @GetMapping("/algorithms/{userId}")
    CommonResult getAllAlgorithms(@PathVariable(value = "userId")Integer userId,
                                 @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                                 @RequestParam(value = "pageSize",defaultValue = "6")Integer pageSize,
                                 @RequestParam(value = "keyWord",defaultValue = "")String keyWord);

    /**
     * 接口 6.1.1.2 根据ID查询算法基本信息
     * @param algorithmId 算法ID
     * @return
     */
    @GetMapping("/algorithm/{algorithmId}")
    CommonResult getAlgorithmBasicById(@PathVariable(value = "algorithmId")Integer algorithmId);

    /**
     * 接口 6.1.1.3 按ID查询算法训练规范
     * @author Jiahan Wang
     * @create 2020-07-15 16:19
     * @updator Jiahan Wang
     * @upadte 2020-07-15 16:19
     * @param algorithmId 算的ID
     * @return
     */
    @GetMapping("/algorithm/trainStandard/{algorithmId}")
    CommonResult getAlgorithmTrainStandardById(@PathVariable("algorithmId") Integer algorithmId);


    /**
     * 接口 6.1.1.4 按ID查询算法超参规范
     * @author Jiahan Wang
     * @create 2020-07-15 16:32
     * @updator Jiahan Wang
     * @upadte 2020-07-15 16:32
     * @param algorithmId
     * @return
     */
    @GetMapping("/algorithm/hyperPara/{algorithmId}")
    CommonResult getAlgorithmHyperPara(@PathVariable("algorithmId")Integer algorithmId);

    /**
     * 接口 6.1.1.7 编辑算法
     *
     * @param algorithm 从前端获取得到一个完整的被用户编辑后的对象
     * @author Yi Zheng
     * @create 2020-07-11 20:00
     * @updator Jiahan Wang
     * @update 2020-07-12 08:30
     * @return
     */
    @PutMapping(value = "/algorithm")
    CommonResult updateAlgorithm(@RequestBody Algorithm algorithm);

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
    @DeleteMapping(value = "/algorithm/{id}")
    CommonResult deleteAlgorithmById(@PathVariable("id") Integer id);


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
    CommonResult getAlgorithmDescription(@PathVariable("algorithmId")Integer algorithmId);

    /**
     * @author Huiri Tan
     * @description 查询算法类型
     * @create 2020/7/23 12:15 上午
     * @update 2020/7/23 12:15 上午
     * @param []
     * @return com.results.CommonResult
     **/
    @GetMapping(value = "/algorithm/type")
    CommonResult getAlgorithmType();

    /**
     * @author Huiri Tan
     * @description 查询实例类型
     * @create 2020/7/23 12:21 上午
     * @update 2020/7/23 12:21 上午
     * @param
     * @return
     **/
    @GetMapping("/instanceType")
    CommonResult getInstanceType();

    /**
     * @author Huiri Tan
     * @description 添加超参数
     * @create 2020/7/23 12:15 下午
     * @update 2020/7/23 12:15 下午
     * @param
     * @return
     **/
    @PostMapping("/algorithm/hyper-parameters")
    HyperParameters addHyperParameters(@RequestBody HyperParameters hyperParameters);


    /**
     * @author Huiri Tan
     * @description 添加算法转发
     * @create 2020/7/23 12:16 下午
     * @update 2020/7/23 12:16 下午
     * @param [algorithm]
     * @return com.entity.Algorithm
     **/
    @PostMapping("/algorithm")
    Algorithm addAlgorithm(@RequestBody Algorithm algorithm);

    /**
     * @author Huiri Tan
     * @description 添加算法描述
     * @create 2020/7/23 12:17 下午
     * @update 2020/7/23 12:17 下午
     * @param [description]
     * @return com.entity.AlgorithmDescription
     **/
    @PostMapping("/algorithm/description")
    AlgorithmDescription addDescription (@RequestBody AlgorithmDescription description);

}
