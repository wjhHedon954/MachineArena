package com.whu.train_task.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.entity.TrainTask;
import com.mapper.TrainTaskMapper;
import com.whu.train_task.service.ITrainTaskService;
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

}
