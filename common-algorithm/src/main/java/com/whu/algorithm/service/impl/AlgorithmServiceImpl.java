package com.whu.algorithm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.entity.Algorithm;
import com.mapper.AlgorithmMapper;
import com.whu.algorithm.service.IAlgorithmService;
import io.swagger.models.auth.In;
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
public class AlgorithmServiceImpl extends ServiceImpl<AlgorithmMapper, Algorithm> implements IAlgorithmService {

    @Autowired
    AlgorithmMapper algorithmMapper;

    /**
     * 接口 6.1.2 创建算法
     * @author Huiri Tan
     * @create 2020-07-12 10:00
     * @updator Huiri Tan
     * @update 2020-07-12 10:00
     * @param algorithm 从前端获取data数据，根据数据创建算法对象
     * @return  返回算法信息
     */
    @Override
    public int addAlgorithm(Algorithm algorithm) {
        int result = algorithmMapper.insert(algorithm);
        return result;
    }

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
}
