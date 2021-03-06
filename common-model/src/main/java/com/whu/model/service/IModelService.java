package com.whu.model.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.entity.Model;
import com.responsevo.ModelResponseVo;

import java.util.List;

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

    /**
     * 接口6.3.1.23 根据id删除模型
     * @description 根据id删除模型
     * @author Yi Zheng
     * @create 2020-7-18 13:00
     * @updator
     * @update
     * @param id
     * @return
     */
    int deleteModelById(Integer id);

    /**
     * 接口6.3.1.23 根据id更改模型
     * @description 根据id更改模型
     * @author Yi Zheng
     * @create 2020-7-18 13:30
     * @updator
     * @update
     * @param model  需要更改的墨香
     * @return int 更改印象的行数
     */
    int updateModel(Model model);

    /**
     * 查询用户下的所有模型
     * @author Jiahan Wang
     * @create 2020-7-22 22:15
     * @updator
     * @update
     * @param userId  用户ID
     * @param keyWord 关键字
     * @return
     */
    List<ModelResponseVo> getUserModel(Integer userId, String keyWord);

    /**
     * 查询所有模型
     * @author Jiahan Wang
     * @create 2020-7-22 23:15
     * @updator
     * @update
     * @param keyWord 关键字
     * @return
     */
    List<ModelResponseVo> getModels(String keyWord);
}
