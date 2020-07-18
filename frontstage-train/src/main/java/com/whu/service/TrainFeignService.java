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
     * @author Yi Zheng
     * @create 2020-07-18 13:00
     * @updator
     * @update
     * @param param 一个包装类，因为@RequestBody不能使用两次。这个类里封装了TrainTask和TrainTaskConf
     * @return  返回通用数据
     */
    @PostMapping("/trainTask")
    CommonResult addTrainTask(@RequestBody TrainTaskAndTrainTaskConfig param);


    /**
     * 接口 6.2.1.2 根据ID删除训练作业
     * @author Yi Zheng
     * @create 2020-07-18 13:00
     * @updator
     * @update
     * @param trainTaskID 删除的ID
     * @return  返回通用数据
     */
    @DeleteMapping("/trainTask/{trainTaskID}")
    CommonResult deleteTrainTaskById(@PathVariable("trainTaskID") Integer trainTaskID);


    /**
     * 接口 6.2.1.4 根据训练作业ID同时更新train_task和train_task_conf
     * @author Yi Zheng
     * @create 2020-07-18 13:00
     * @updator
     * @update
     * @param param 一个包装类，因为@RequestBody不能使用两次。这个类里封装了TrainTask和TrainTaskConf
     * @return  返回通用数据
     */
    @PutMapping("/trainTask")
    CommonResult updateTrainTask(@RequestBody TrainTaskAndTrainTaskConfig param);
}
