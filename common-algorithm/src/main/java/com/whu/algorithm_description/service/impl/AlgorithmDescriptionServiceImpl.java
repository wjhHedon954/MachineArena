package com.whu.algorithm_description.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.entity.Algorithm;
import com.entity.AlgorithmDescription;
import com.mapper.AlgorithmDescriptionMapper;
import com.mapper.AlgorithmMapper;
import com.whu.algorithm_description.service.IAlgorithmDescriptionService;
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
public class AlgorithmDescriptionServiceImpl extends ServiceImpl<AlgorithmDescriptionMapper, AlgorithmDescription> implements IAlgorithmDescriptionService {
    @Autowired
    AlgorithmDescriptionMapper descriptionMapper;


    @Autowired
    AlgorithmMapper algorithmMapper;
    /**
     * @author Huiri Tan
     * @description 向表中插入算法描述
     * @create 2020/7/13 11:36 下午
     * @update 2020/7/15 1:36 上午
     * @param description
     * @return int 返回受影响的行数
     **/
    @Override
    public int addDescription(AlgorithmDescription description) {
        description.setAlgorithmDescriptionId(-1);      // 添加之前置为null
        return descriptionMapper.insert(description);
    }

    /**
     * 查询算法描述
     * @author Jiahan Wang
     * @param algorithmId
     * @return
     */
    @Override
    public AlgorithmDescription getAlgorithmDescription(Integer algorithmId) {

        Algorithm algorithm = algorithmMapper.selectById(algorithmId);
        if (algorithm == null){
            return null;
        }
        AlgorithmDescription algorithmDescription = descriptionMapper.selectById(algorithm.getAlgorithmDescriptionId());

        return algorithmDescription;
    }
}
