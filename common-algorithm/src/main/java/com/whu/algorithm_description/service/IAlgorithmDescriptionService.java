package com.whu.algorithm_description.service;

import com.entity.AlgorithmDescription;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Hedon
 * @since 2020-07-11
 */
public interface IAlgorithmDescriptionService extends IService<AlgorithmDescription> {
    /**
     * @author Huiri Tan
     * @description TODO 添加算法描述
     * @create 2020/7/13 11:29 下午
     * @update 2020/7/13 11:29 下午
     * @param [description] 算法描述实体
     * @return int
     **/
    int addDescription(AlgorithmDescription description);
}
