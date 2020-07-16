package com.whu.ai_engine.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.entity.AiEngine;
import com.mapper.AiEngineMapper;
import com.whu.ai_engine.service.IAiEngineService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Hedon
 * @since 2020-07-11
 */
@Service
public class AiEngineServiceImpl extends ServiceImpl<AiEngineMapper, AiEngine> implements IAiEngineService {
    @Autowired
    AiEngineMapper aiEngineMapper;

    @Override
    public List<AiEngine> getAiEngines() {
        /**
         * @author Huiri Tan
         * @description 查询所有的ai引擎及 python版本
         * @create 2020/7/16 9:04 下午
         * @update 2020/7/16 9:04 下午
         * @param []
         * @return java.util.List<com.entity.AiEngine>
         **/
        return aiEngineMapper.selectList(
                new QueryWrapper<AiEngine>()
                        .groupBy("algorithm_engine_id", "algorithm_engine_name", "algorithm_engine_version", "python_version_name")
        );
    }
}
