package com.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.entity.Model;
import com.responsevo.ModelResponseVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Hedon
 * @since 2020-07-19
 */
public interface ModelMapper extends BaseMapper<Model> {

    List<ModelResponseVo>  selectModelsWithType(String keyWord);

    List<ModelResponseVo> selectUsersModelsWithType(String keyWord,Integer userId);

}
