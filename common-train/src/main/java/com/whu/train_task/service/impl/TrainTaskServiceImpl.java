package com.whu.train_task.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.entity.TrainTask;
import com.entity.TrainTaskConf;
import com.mapper.TrainTaskConfMapper;
import com.mapper.TrainTaskMapper;
import com.whu.train_task.service.ITrainTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

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
     * @updator Yi Zheng
     * @update 2020-7-17 16:00
     * @param trainTask 训练作业
     * @param trainTaskConf  训练作业参数
     * @return  返回通用数据
     */
    @Override
    public int[] addTrainTask(TrainTask trainTask,TrainTaskConf trainTaskConf) {
        int i = trainTaskMapper.insert(trainTask);

        //训练参数的属性"train_task_id"是上面insert生成的id
        trainTaskConf.setTrainTaskId(trainTask.getTrainTaskId());

        int j = trainTaskConfMapper.insert(trainTaskConf);
        System.out.println("j="+j);
        return new int[]{i,j};
    }


    /**
     * 接口 6.2.1.2 根据ID删除训练作业
     * @author Yi Zheng
     * @create 2020-07-17 14:00
     * @updator
     * @update
     * @param trainTaskID 删除的ID
     * @return  返回删除的操作影响行数
     */
    @Override
    public int deleteTrainTaskById(Integer trainTaskID) {
        return trainTaskMapper.deleteById(trainTaskID);
    }
}
