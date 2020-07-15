package com.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.entity.Algorithm;
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


    //查询完整的算法信息
    AlgorithmResponseVo selectFullAlgorithmInfo(Integer algorithmId);

    //查询所有的算法数据
    List<AlgorithmResponseVo> selectAllFullAlgorithms(String keyWord);


}
