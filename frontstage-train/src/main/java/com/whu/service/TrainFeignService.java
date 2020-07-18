package com.whu.service;

import com.constants.ResultCode;
import com.entity.TrainTask;
import com.entity.TrainTaskConf;
import com.responsevo.TrainTaskAndTrainTaskConfig;
import com.results.CommonResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

/**
 * OpenFeign跨服务调用层，指向 common-train
 *
 * @author Hedon Wang
 * @create 2020-07-17 11:38
 */

@Service
@FeignClient(value = "common-train")
public interface TrainFeignService {

    /**
     * 接口 6.2.1.1 创建训练作业
     *
     * @param param 一个包装类，因为@RequestBody不能使用两次。这个类里封装了TrainTask和TrainTaskConf
     * @return 返回通用数据
     * @author Yi Zheng
     * @create 2020-07-18 13:00
     * @updator
     * @update
     */
    @PostMapping("/trainTask")
    CommonResult addTrainTask(@RequestBody TrainTaskAndTrainTaskConfig param);


    /**
     * 接口 6.2.1.2 根据ID删除训练作业
     *
     * @param trainTaskID 删除的ID
     * @return 返回通用数据
     * @author Yi Zheng
     * @create 2020-07-18 13:00
     * @updator
     * @update
     */
    @DeleteMapping("/trainTask/{trainTaskID}")
    CommonResult deleteTrainTaskById(@PathVariable("trainTaskID") Integer trainTaskID);


    /**
     * 接口 6.2.1.4 根据训练作业ID同时更新train_task和train_task_conf
     *
     * @param param 一个包装类，因为@RequestBody不能使用两次。这个类里封装了TrainTask和TrainTaskConf
     * @return 返回通用数据
     * @author Yi Zheng
     * @create 2020-07-18 13:00
     * @updator
     * @update
     */
    @PutMapping("/trainTask")
    CommonResult updateTrainTask(@RequestBody TrainTaskAndTrainTaskConfig param);


    /**
     * 接口 6.2.1.8 分页查询当前用户的训练作业
     *
     * @param userId   用户ID
     * @param pageNum  当前页吗
     * @param pageSize 页面大小
     * @param keyWord  搜索关键字
     * @return
     * @author Jiahan Wang
     * @create 2020-07-18 15:59
     * @updator Jiahan Wang
     * @upadte 2020-07-18 15:59
     */
    @GetMapping("/trainTasks/{userId}")
    CommonResult getUserTrainTasks(@PathVariable(value = "userId") Integer userId,
                                   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                   @RequestParam(value = "pageSize", defaultValue = "6") Integer pageSize,
                                   @RequestParam(value = "keyWord", defaultValue = "") String keyWord);


    /**
     * 6.2.1.3 按ID查询作业
     *
     * @param trainTaskId
     * @return
     * @author Jiahan Wang
     * @create 2020-07-18 17:59
     * @updator Jiahan Wang
     * @upadte 2020-07-18 17:59
     */
    @ApiOperation(value = "6.2.1.3 按ID查询作业", httpMethod = "GET")
    @ApiImplicitParam(name = "trainTaskId", value = "作业ID", paramType = "path", dataType = "Integer", required = true)
    @GetMapping("/trainTask/{trainTaskId}")
    CommonResult getTrainTaskById(@PathVariable("trainTaskId") Integer trainTaskId);

    /**
     * 接口 6.2.1.7 分页查询训练作业
     *
     * @param pageNum  当前页吗
     * @param pageSize 页面大小
     * @param keyWord  搜索关键字
     * @return
     * @author Jiahan Wang
     * @create 2020-07-18 18:59
     * @updator Jiahan Wang
     * @upadte 2020-07-18 18:59
     */
    @GetMapping("/trainTasks")
    CommonResult getUserTrainTasks(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                   @RequestParam(value = "pageSize", defaultValue = "6") Integer pageSize,
                                   @RequestParam(value = "keyWord", defaultValue = "") String keyWord);


    /**
     * 接口 6.2.1.5 查看日志
     *
     * @param trainTaskId
     * @return
     * @author Jiahan Wang
     * @create 2020-07-18 19:10
     * @updator Jiahan Wang
     * @upadte 2020-07-18 19:10
     */
    @GetMapping("/trainTask/log/{trainTaskId}")
    CommonResult getTrainTaskLog(@PathVariable("trainTaskId") Integer trainTaskId);


    /**
     * 接口 6.2.1.6 查询资源占用情况
     *
     * @param trainTaskId
     * @return
     * @author Jiahan Wang
     * @create 2020-07-18 19:20
     * @updator Jiahan Wang
     * @upadte 2020-07-18 19:20
     */
    @GetMapping("/trainTask/resources/{trainTaskId}")
    CommonResult getTrainTaskResources(@PathVariable("trainTaskId") Integer trainTaskId);
}
