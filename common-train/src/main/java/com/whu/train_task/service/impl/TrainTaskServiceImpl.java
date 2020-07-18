package com.whu.train_task.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.entity.TrainTask;
import com.entity.TrainTaskConf;
import com.entity.TrainTaskLog;
import com.entity.TrainTaskResource;
import com.mapper.TrainTaskConfMapper;
import com.mapper.TrainTaskLogMapper;
import com.mapper.TrainTaskMapper;
import com.mapper.TrainTaskResourceMapper;
import com.responsevo.TrainTaskAndTrainTaskConfig;
import com.responsevo.TrainTaskResponseVo;
import com.whu.train_task.service.ITrainTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;
import java.util.List;
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
    @Autowired
    private TrainTaskLogMapper trainTaskLogMapper;
    @Autowired
    private TrainTaskResourceMapper trainTaskResourceMapper;

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


    /**
     * 接口 6.2.1.4 根据训练作业ID同时更新train_task和train_task_conf
     * @author Yi Zheng
     * @create 2020-07-18 10:00
     * @updator
     * @update
     * @param trainTask 训练作业
     * @param trainTaskConf 训练作业参数
     * @return  返回更新影响的行数
     */
    @Override
    public int[] updateTrainTask(TrainTask trainTask,TrainTaskConf trainTaskConf) {
        //执行更新训练作业
        int i = trainTaskMapper.updateById(trainTask);

        //保证更新的执行对象是train_task_conf中训练作业ID等于参数训练作业ID的对象
        QueryWrapper wrapper = new QueryWrapper();
        Integer trainTaskId = trainTask.getTrainTaskId();
        trainTaskConf.setTrainTaskId(trainTaskId);
        wrapper.eq("train_task_id",trainTaskId);

        //执行更新训练作业配置
        int j = trainTaskConfMapper.update(trainTaskConf,wrapper);
        return new int[]{i,j};
    }


    /**
     * 6.2.1.8 分页模糊查询当前用户下的训练作业
     * @author Jihan Wang
     * @create 2020-07-18 16:00
     * @updator
     * @update
     * @param userId
     * @param keyWord
     * @return
     */
    @Override
    public List<TrainTaskResponseVo> getTrainTasksByUserId(Integer userId, String keyWord) {
        return trainTaskMapper.getTrainTasksByUserId(userId,keyWord);
    }


    /**
     * 6.2.1.3 按ID查询作业
     * @author Jihan Wang
     * @create 2020-07-18 17:00
     * @updator
     * @update
     * @param trainTaskId
     * @return
     */
    @Override
    public TrainTaskAndTrainTaskConfig getTrainTaskFullInfoById(Integer trainTaskId) {

        TrainTaskAndTrainTaskConfig trainTaskAndTrainTaskConfig = new TrainTaskAndTrainTaskConfig();
        //获得trainTask
        TrainTask trainTask = trainTaskMapper.selectById(trainTaskId);
        trainTaskAndTrainTaskConfig.setTrainTask(trainTask);
        //获得trainTaskConf
        QueryWrapper<TrainTaskConf> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("train_task_id",trainTaskId);
        List<TrainTaskConf> trainTaskConfs = trainTaskConfMapper.selectList(queryWrapper);
        if (trainTaskConfs == null || trainTaskConfs.size() == 0){
            trainTaskAndTrainTaskConfig.setTrainTaskConf(null);
        }else{
            trainTaskAndTrainTaskConfig.setTrainTaskConf(trainTaskConfs.get(0));
        }
        return trainTaskAndTrainTaskConfig;
    }


    /**
     * 接口 6.2.1.7 分页查询训练作业
     * @author Jiahan Wang
     * @create 2020-07-18 18:59
     * @updator Jiahan Wang
     * @upadte 2020-07-18 18:59
     * @param keyWord
     * @return
     */
    @Override
    public List<TrainTaskResponseVo> getTrainTasks(String keyWord) {
        return  trainTaskMapper.getTrainTasks(keyWord);
    }

    /**
     * 接口 6.2.1.5 查看日志
     * @author Jiahan Wang
     * @create 2020-07-18 19:10
     * @updator Jiahan Wang
     * @upadte 2020-07-18 19:10
     * @param trainTaskId
     * @return
     */
    @Override
    public List<TrainTaskLog> getTrainTaskLog(Integer trainTaskId) {
        QueryWrapper<TrainTaskLog> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("train_task_id",trainTaskId);
        List<TrainTaskLog> trainTaskLogs = trainTaskLogMapper.selectList(queryWrapper);
        return trainTaskLogs;
    }


    /**
     * 接口 6.2.1.6 查询资源占用情况
     * @author Jiahan Wang
     * @create 2020-07-18 19:20
     * @updator Jiahan Wang
     * @upadte 2020-07-18 19:20
     * @param trainTaskId
     * @return
     */
    @Override
    public List<TrainTaskResource> getTrainTaskResources(Integer trainTaskId) {
        QueryWrapper<TrainTaskResource> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("train_task_id",trainTaskId);
        return trainTaskResourceMapper.selectList(queryWrapper);
    }
}
