package com.whu.train_task.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.entity.*;
import com.mapper.*;
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
    @Autowired
    private TaskIpContainerMapper taskIpContainerMapper;
    @Autowired
    private AlgorithmMapper algorithmMapper;

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
        int trainTaskId=trainTask.getTrainTaskId();

        //训练参数的属性"train_task_id"是上面insert生成的id
        trainTaskConf.setTrainTaskId(trainTaskId);

        int j = trainTaskConfMapper.insert(trainTaskConf);
        int trainTaskConfId = trainTaskConf.getTrainTaskConfId();

        //把生成的两个id一起传过去
        return new int[]{i,j,trainTaskId,trainTaskConfId};
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
     * 接口 6.2.1.2 根据ID删除训练作业配置
     * @author Yi Zheng
     * @create 2020-07-22 19:00
     * @updator
     * @update
     * @param trainTaskConfId 删除的ID
     * @return  返回删除影响的行数
     */
    @Override
    public int deleteTrainTaskConfById(Integer trainTaskConfId) {
        return trainTaskConfMapper.deleteById(trainTaskConfId);
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
     * 接口 6.2.1.1 根据id查询trainTaskConf
     * @author Yi Zheng
     * @create 2020-07-22 20:20
     * @updator
     * @update
     * @param trainTaskConfId id
     * @return  TrainTaskConf
     */
    @Override
    public TrainTaskConf selectTrainTaskConfById(Integer trainTaskConfId) {
        return trainTaskConfMapper.selectById(trainTaskConfId);
    }


    /**
     * 接口 6.2.1.1 根据id更改trainTaskConf
     * @author Yi Zheng
     * @create 2020-07-22 20:20
     * @updator
     * @update
     * @param trainTaskConf 被更改的对象
     * @return  影响的行数
     */
    @Override
    public int updateTrainTaskConfById(TrainTaskConf trainTaskConf) {
        return trainTaskConfMapper.updateById(trainTaskConf);
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


    /**
     * 接口 6.2.1.10 insert一条数据
     * @author Yi Zheng
     * @create 2020-07-19 00:50
     * @updator
     * @upadte
     * @param ipContainer insert的数据
     * @return
     */
    @Override
    public int addTaskIpContainer(TaskIpContainer ipContainer) {
        return taskIpContainerMapper.insert(ipContainer);
    }


    /**
     * 根据trainTaskId(非主键)删除TaskIpContainer
     * @author Yi Zheng
     * @create 2020-07-19 01:30
     * @updator
     * @upadte
     * @param trainTaskId id
     * @return
     */
    @Override
    public int deleteTaskIpContainerByTrainTaskId(Integer trainTaskId) {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("train_task_id",trainTaskId);
        return taskIpContainerMapper.delete(wrapper);
    }


    /**
     * 根据根据算法id查算法
     * @author Yi Zheng
     * @create 2020-07-21 19:00
     * @updator
     * @upadte
     * @param id
     * @return
     */
    @Override
    public Algorithm selectAlgorithmById(Integer id) {
        return algorithmMapper.selectById(id);
    }


    /**
     * 根据根据算训练任务id查询训练TaskIpContainer
     * @author Yi Zheng
     * @create 2020-07-21 19:00
     * @updator
     * @upadte
     * @param id
     * @return
     */
    @Override
    public TaskIpContainer selectTaskIpContainerByTrainTaskId(Integer id) {
        QueryWrapper<TaskIpContainer> wrapper=new QueryWrapper<>();
        wrapper.eq("train_task_id",id);
        TaskIpContainer taskIpContainer = taskIpContainerMapper.selectOne(wrapper);
        return taskIpContainer;
    }


    /**
     * 根据根据算训练任务id查询训练TaskIpContainer
     * @author Jiahan Wang
     * @create 2020-07-24 10:00
     * @updator
     * @upadte
     * @param keyWord 关键字
     * @param status 状态
     * @return
     */
    @Override
    public List<TrainTaskResponseVo> getTrainTasksWithStatus(String keyWord, Integer status) {
        List<TrainTaskResponseVo> trainTasksByStatus = trainTaskMapper.getTrainTasksByStatus(keyWord, status);
        return trainTasksByStatus;
    }

    /**
     * 模糊查询某用户下的某状态的作业
     * @author Jihan Wang
     * @create 2020-07-24 10:57
     * @updator
     * @update
     * @param userId  用户ID
     * @param keyWord 关键字
     * @param status  状态
     * @return
     */
    @Override
    public List<TrainTaskResponseVo> getTrainTasksByUserIdWithStatus(Integer userId, String keyWord, Integer status) {
        return trainTaskMapper.getUsersTrainTasksByStatus(userId,keyWord,status);
    }
}
