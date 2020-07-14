package com.whu.hyper_parameters.service.impl;

import com.entity.HyperParameters;
import com.mapper.HyperParametersMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.whu.hyper_parameters.service.IHyperParametersService;
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
public class HyperParametersServiceImpl extends ServiceImpl<HyperParametersMapper, HyperParameters> implements IHyperParametersService {
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
}
