package com.whu.algorithm_description.service.impl;

import com.entity.AlgorithmDescription;
import com.mapper.AlgorithmDescriptionMapper;
import com.whu.algorithm_description.service.IAlgorithmDescriptionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    /**
     * @author Huiri Tan
     * @description 向表中插入算法描述
     * @create 2020/7/13 11:36 下午
     * @update 2020/7/13 11:36 下午
     * @param [description]
     * @return int 返回受影响的行数
     **/
    @Override
    public int addDescription(AlgorithmDescription description) {
        return descriptionMapper.insert(description);
    }
}
