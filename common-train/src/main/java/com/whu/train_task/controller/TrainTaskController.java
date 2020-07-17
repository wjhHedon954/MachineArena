package com.whu.train_task.controller;


import com.constants.ResultCode;
import com.entity.TrainTask;
import com.entity.TrainTaskConf;
import com.results.CommonResult;
import com.whu.train_task.service.impl.TrainTaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Hedon
 * @since 2020-07-17
 */
@RestController
public class TrainTaskController {
    @Autowired
    private TrainTaskServiceImpl trainTaskService;

    //此接口还未通过测试，目前@RequstBody获取数据报流关闭异常
    /**
     * 接口 6.2.1.1 创建训练作业
     * @author Yi Zheng
     * @create 2020-07-17 13:00
     * @updator
     * @update
     * @param trainTask 从前端获取训练作业数据，根据数据创建训练作业
     * @param trainTaskConf 从前端获取训练作业参数数据，根据数据创建训练作业参数
     * @return  返回通用数据
     */
    @PostMapping("/trainTask")
    CommonResult addTrainTask(@RequestBody TrainTask trainTask,@RequestBody TrainTaskConf trainTaskConf){
        if (trainTask == null || trainTaskConf==null) {
            return CommonResult.fail(ResultCode.EMPTY_OBJECT);
        }
        try {
            //执行更新
            int[] ints = trainTaskService.addTrainTask(trainTask, trainTaskConf);
            if (ints[0] == 0 || ints[1]==0) {
                //如果更新条数为0，则说明该训练作业或训练作业参数不在数据库中，返回数据不存在信息
                return CommonResult.fail(ResultCode.ALGORITHM_NOT_EXIST);
            } else {
                //因为是根据ID来更改，所以情况只有 0 和 1，如果不为 0 那必定是成功
                return CommonResult.success();
            }
        } catch (Exception e) {
            return CommonResult.fail(ResultCode.ERROR);
        }
    }
}
