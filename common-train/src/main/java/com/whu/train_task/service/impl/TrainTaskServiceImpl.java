package com.whu.train_task.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.entity.TrainTask;
import com.entity.TrainTaskConf;
import com.mapper.TrainTaskConfMapper;
import com.mapper.TrainTaskMapper;
import com.whu.train_task.service.ITrainTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Hedon
 * @since 2020-07-17
 */
@Service
public class TrainTaskServiceImpl extends ServiceImpl<TrainTaskMapper, TrainTask> implements ITrainTaskService {
    @Autowired
    private TrainTaskMapper trainTaskMapper;
    @Autowired
    private TrainTaskConfMapper trainTaskConfMapper;

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
    @Override
    public int[] addTrainTask(TrainTask trainTask, TrainTaskConf trainTaskConf) {
        int i = trainTaskMapper.insert(trainTask);

        //训练参数的属性"train_task_id"是上面insert生成的id
        trainTaskConf.setTrainTaskId(trainTask.getTrainTaskId());
        int j = trainTaskConfMapper.insert(trainTaskConf);
        return new int[]{i,j};
    }
}
