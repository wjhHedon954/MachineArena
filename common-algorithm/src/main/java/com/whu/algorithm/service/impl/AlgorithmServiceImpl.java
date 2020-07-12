package com.whu.algorithm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.entity.Algorithm;
import com.mapper.AlgorithmMapper;
import com.responsevo.AlgorithmResponseVo;
import com.whu.algorithm.service.IAlgorithmService;
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
public class AlgorithmServiceImpl extends ServiceImpl<AlgorithmMapper, Algorithm> implements IAlgorithmService {

    @Autowired
    AlgorithmMapper algorithmMapper;

    /**
     * 编辑算法
     * @author Jiahan Wang
     * @create 2020-07-12 08:23
     * @update 2020-07-12 08:23
     * @param algorithm  算法对象
     * @return 数据库更新条数
     */
    @Override
    public int updateAlgorithm(Algorithm algorithm) {
        int i = algorithmMapper.updateById(algorithm);
        return i;
    }


    /**
     * 删除算法
     * @author Jiahan Wang
     * @create 2020-07-12 08:23
     * @update 2020-07-12 08:23
     * @param id 算法ID
     * @return 删除条目数
     */
    @Override
    public int deleteAlgorithmById(Integer id) {
        return algorithmMapper.deleteById(id);
    }

    /**
     * 根据ID查询算法
     * @author Jiahan Wang
     * @create 2020-07-12 08:55
     * @update 2020-07-12 08:55
     * @param id 算法ID
     * @return 删除条目数
     */
    @Override
    public Algorithm getAlgorithmById(Integer id) {
        Algorithm algorithm = algorithmMapper.selectById(id);
        return algorithm;
    }

    /**
     * 分页查询算法，附带其他信息
     * @author Jiahan Wang
     * @create 2020-07-12 14:40
     * @update 2020-07-12 14:40
     * @param keyWord 关键字
     */
    @Override
    public List<AlgorithmResponseVo> getAllFullAlgorithms(String keyWord) {
        keyWord = "%"+keyWord+"%";
        List<AlgorithmResponseVo> algorithmResponseVos = algorithmMapper.selectAllFullAlgorithms(keyWord);
        return algorithmResponseVos;

    }
}
