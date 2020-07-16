package com.whu.ai_engine.service;

import com.entity.AiEngine;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Hedon
 * @since 2020-07-11
 */
public interface IAiEngineService extends IService<AiEngine> {

    /**
     * @author Huiri Tan
     * @description 查询所有的ai引擎及 python版本
     * @create 2020/7/16 9:03 下午
     * @update 2020/7/16 9:03 下午
     * @param []
     * @return java.util.List<com.entity.AiEngine>
     **/
    List<AiEngine> getAiEngines();
}
