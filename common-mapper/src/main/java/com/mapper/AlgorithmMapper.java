package com.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.entity.Algorithm;
import com.responsevo.AlgorithmFullResponseVo;
import com.responsevo.AlgorithmResponseVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Hedon
 * @since 2020-07-11
 */
public interface AlgorithmMapper extends BaseMapper<Algorithm> {


    //查询算法的详细数据
    AlgorithmFullResponseVo selectAllFullAlgorithmInfoById(Integer algorithmId);

    //根据ID查询算法的基本信息
    List<AlgorithmResponseVo> selectAlgorithmsBasicInfo(String kewWord);

    //查询一个用户下的所有算法数据
    List<AlgorithmResponseVo> selectUsersAlgorithmsBasicInfo(String keyWord,Integer userId);
}
