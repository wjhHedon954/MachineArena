package com.whu.algorithm_type.service;

import com.entity.AlgorithmType;
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
public interface IAlgorithmTypeService extends IService<AlgorithmType> {
    List<AlgorithmType> getAlgorithmType();
}
