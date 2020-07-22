package com.whu.model.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.entity.Model;
import com.entity.ModelUser;
import com.mapper.ModelMapper;
import com.mapper.ModelUserMapper;
import com.responsevo.ModelResponseVo;
import com.whu.model.service.IModelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.WebParam;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Hedon
 * @since 2020-07-19
 */
@Service
public class ModelServiceImpl extends ServiceImpl<ModelMapper, Model> implements IModelService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    ModelUserMapper modelUserMapper;
    /**
     * 接口6.3.1.1  导入模型
     * @description 导入模型
     * @author Yi Zheng
     * @create 2020-7-18 12:00
     * @updator
     * @update
     * @param model
     * @return
     */
    @Override
    public int importModel(Model model) {
        return modelMapper.insert(model);
    }


    /**
     * 接口6.3.1.2  根据id查询模型
     * @description 根据id查询模型
     * @author Yi Zheng
     * @create 2020-7-18 12:30
     * @updator
     * @update
     * @param id
     * @return
     */
    @Override
    public Model selectModelById(Integer id) {
        return modelMapper.selectById(id);
    }


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
    @Override
    public int deleteModelById(Integer id) {
        return modelMapper.deleteById(id);
    }


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
    @Override
    public int updateModel(Model model) {
        return modelMapper.updateById(model);
    }


    /**
     * 询用户下的所有模型
     * @author Jiahan Wang
     * @create 2020-7-22 22:15
     * @updator
     * @update
     * @param userId  用户ID
     * @param keyWord 关键字
     * @return
     */
    @Override
    public List<ModelResponseVo> getUserModel(Integer userId, String keyWord) {
        List<ModelResponseVo> modelResponseVos = modelMapper.selectUsersModelsWithType(keyWord, userId);
        return modelResponseVos;
    }


    /**
     * 查询所有模型
     * @author Jiahan Wang
     * @create 2020-7-22 23:15
     * @updator
     * @update
     * @param keyWord 关键字
     * @return
     */
    @Override
    public List<ModelResponseVo> getModels(String keyWord) {
        List<ModelResponseVo> modelResponseVos = modelMapper.selectModelsWithType(keyWord);
        return modelResponseVos;
    }
}
