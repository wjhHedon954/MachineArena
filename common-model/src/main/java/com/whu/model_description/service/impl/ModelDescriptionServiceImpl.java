package com.whu.model_description.service.impl;

import com.entity.ModelDescription;
import com.mapper.ModelDescriptionMapper;
import com.whu.model_description.service.IModelDescriptionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Hedon
 * @since 2020-07-19
 */
@Service
public class ModelDescriptionServiceImpl extends ServiceImpl<ModelDescriptionMapper, ModelDescription> implements IModelDescriptionService {
    @Autowired
    ModelDescriptionMapper modelDescriptionMapper;

    /**
     * @author Huiri Tan
     * @description 插入模型描述
     * @create 2020/7/23 2:25 上午
     * @update 2020/7/23 2:25 上午
     * @param [modelDescription]
     * @return com.entity.ModelDescription
     **/
    @Override
    public ModelDescription addModelDescription(ModelDescription modelDescription) {
        int result = modelDescriptionMapper.insert(modelDescription);
        if (result == 0) {
            return null;
        }
        return modelDescription;
    }
}
