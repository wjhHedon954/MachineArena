package com.whu.algorithm_type.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.entity.AlgorithmType;
import com.mapper.AlgorithmTypeMapper;
import com.whu.algorithm_type.service.IAlgorithmTypeService;
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
public class AlgorithmTypeServiceImpl extends ServiceImpl<AlgorithmTypeMapper, AlgorithmType> implements IAlgorithmTypeService {
    @Autowired
    AlgorithmTypeMapper algorithmTypeMapper;

    @Override
    public List<AlgorithmType> getAlgorithmType() {
        return algorithmTypeMapper.selectList(new QueryWrapper<>());
    }
}
