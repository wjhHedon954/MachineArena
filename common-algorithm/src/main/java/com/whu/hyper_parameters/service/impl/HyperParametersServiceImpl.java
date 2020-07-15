package com.whu.hyper_parameters.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.entity.Algorithm;
import com.entity.HyperParameters;
import com.mapper.AlgorithmMapper;
import com.mapper.HyperParametersMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.whu.hyper_parameters.service.IHyperParametersService;
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
public class HyperParametersServiceImpl extends ServiceImpl<HyperParametersMapper, HyperParameters> implements IHyperParametersService {

    @Autowired
    AlgorithmMapper algorithmMapper;

    @Autowired
    HyperParametersMapper hyperParametersMapper;

    /**
     * @author Huiri Tan
     * @description 向表中插入参数
     * @create 2020/7/14 1:08 上午
     * @update 2020/7/15 1:08 上午
     * @param [hyperParameter]
     * @return int
     **/
    @Override
    public int addHyperParameter(HyperParameters hyperParameter) {
        hyperParameter.setHyperParaId(-1);      // 添加之前置为null
        return hyperParametersMapper.insert(hyperParameter);
    }

    /**
     * 根据算法ID查询超参规范
     * @author Jiahan Wang
     * @create 2020-07-15 16:32
     * @updator Jiahan Wang
     * @upadte 2020-07-15 16:32
     * @param algorithmId
     * @return
     */
    @Override
    public List<HyperParameters> getHyperParaByAlgorithmId(Integer algorithmId) {
        QueryWrapper<HyperParameters> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("algorithm_id",algorithmId);
        List<HyperParameters> hyperParameters = hyperParametersMapper.selectList(queryWrapper);
        return hyperParameters;
    }
}
