package com.whu.model_description.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.entity.ModelDescription;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Hedon
 * @since 2020-07-19
 */
public interface IModelDescriptionService extends IService<ModelDescription> {
    /**
     * @author Huiri Tan
     * @description 插入模型描述
     * @create 2020/7/23 2:27 上午
     * @update 2020/7/23 2:27 上午
     * @param [modelDescription]
     * @return com.entity.ModelDescription
     **/
    ModelDescription addModelDescription(ModelDescription modelDescription);

}
