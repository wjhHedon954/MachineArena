package com.whu.train_task.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.entity.TrainTask;
import com.entity.TrainTaskConf;
import com.results.CommonResult;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Hedon
 * @since 2020-07-17
 */
public interface ITrainTaskService extends IService<TrainTask> {
    /**
     * 接口 6.2.1.1 创建训练作业
     * @author Yi Zheng
     * @create 2020-07-17 13:00
     * @updator
     * @update
     * @param trainTask 从前端获取训练作业数据，根据数据创建训练作业
     * @param trainTaskConf 从前端获取训练作业参数数据，根据数据创建训练作业参数
     * @return  返回创建影响的行数，因为插入了两条数据使用所以使用一个整数数组
     */
    int[] addTrainTask(TrainTask trainTask, TrainTaskConf trainTaskConf);
}
