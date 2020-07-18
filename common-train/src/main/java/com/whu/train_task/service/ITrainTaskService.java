package com.whu.train_task.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.entity.TrainTask;
import com.entity.TrainTaskConf;
import com.entity.TrainTaskLog;
import com.responsevo.TrainTaskAndTrainTaskConfig;
import com.responsevo.TrainTaskResponseVo;
import com.results.CommonResult;

import java.util.List;
import java.util.Map;

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
     * @updator Yi Zheng
     * @update 2020-7-17 16:00
     * @param trainTask 训练作业
     * @param trainTaskConf  训练作业参数
     * @return  返回通用数据
     */
    int[] addTrainTask(TrainTask trainTask,TrainTaskConf trainTaskConf);


    /**
     * 接口 6.2.1.2 根据ID删除训练作业
     * @author Yi Zheng
     * @create 2020-07-17 14:00
     * @updator
     * @update
     * @param trainTaskID 删除的ID
     * @return  返回删除影响的行数
     */
    int deleteTrainTaskById(Integer trainTaskID);


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
    int[] updateTrainTask(TrainTask trainTask,TrainTaskConf trainTaskConf);

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
    List<TrainTaskResponseVo> getTrainTasksByUserId(Integer userId, String keyWord);


    /**
     * 6.2.1.3 按ID查询作业
     * @author Jihan Wang
     * @create 2020-07-18 17:00
     * @updator
     * @update
     * @param trainTaskId
     * @return
     */
    TrainTaskAndTrainTaskConfig getTrainTaskFullInfoById(Integer trainTaskId);


    /**
     * 接口 6.2.1.7 分页查询训练作业
     * @author Jiahan Wang
     * @create 2020-07-18 18:59
     * @updator Jiahan Wang
     * @upadte 2020-07-18 18:59
     * @param keyWord
     * @return
     */
    List<TrainTaskResponseVo> getTrainTasks(String keyWord);

    /**
     * 接口 6.2.1.5 查看日志
     * @author Jiahan Wang
     * @create 2020-07-18 19:10
     * @updator Jiahan Wang
     * @upadte 2020-07-18 19:10
     * @param trainTaskId
     * @return
     */
    List<TrainTaskLog> getTrainTaskLog(Integer trainTaskId);
}
