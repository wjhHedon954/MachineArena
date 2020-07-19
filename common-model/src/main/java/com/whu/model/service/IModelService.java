package com.whu.model.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.entity.Model;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Hedon
 * @since 2020-07-19
 */
public interface IModelService extends IService<Model> {
    /**
     * 接口6.3.1.1  导入模型
     * @description 导入模型
     * @author Yi Zheng
     * @create 2020-7-18 11:30
     * @updator
     * @update
     * @param model
     * @return
     */
    int importModel(Model model);

    /**
     * 接口6.3.1.2  根据id模型
     * @description 导入模型
     * @author Yi Zheng
     * @create 2020-7-18 11:30
     * @updator
     * @update
     * @param id
     * @return
     */
    Model selectModelById(Integer id);
}
