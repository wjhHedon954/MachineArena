package com.whu.algorithm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.entity.Algorithm;
import com.entity.AlgorithmType;
import com.entity.AlgorithmUser;
import com.mapper.AlgorithmMapper;
import com.mapper.AlgorithmTypeMapper;
import com.mapper.AlgorithmUserMapper;
import com.responsevo.AlgorithmFullResponseVo;
import com.responsevo.AlgorithmResponseVo;
import com.whu.algorithm.service.IAlgorithmService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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


    @Autowired
    AlgorithmUserMapper algorithmUserMapper;

    @Autowired
    AlgorithmTypeMapper algorithmTypeMapper;

    /**
     * 接口 6.1.2 创建算法
     * @author Huiri Tan
     * @create 2020-07-12 10:00
     * @updator Huiri Tan
     * @update 2020-07-15 1:00
     * @param algorithm 从前端获取data数据，根据数据创建算法对象
     * @return  返回算法信息
     */
    @Override
    public int addAlgorithm(Algorithm algorithm) {
        algorithm.setAlgorithmId(-1);   // 添加之前置为null
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
     * @author Huiri Tan
     * @description 根据ID查询算法实体
     * @create 2020/7/20 1:30 下午
     * @update 2020/7/20 1:30 下午
     * @param [id]
     * @return com.entity.Algorithm
     **/
    @Override
    public  Algorithm getAlgorithmObjectById(Integer id) {
        return algorithmMapper.selectById(id);
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
    public AlgorithmFullResponseVo getAlgorithmFullInfoById(Integer id) {
        AlgorithmFullResponseVo algorithm = algorithmMapper.selectAllFullAlgorithmInfoById(id);
        return algorithm;
    }

    /**
     * 分页查询所有用户的算法
     * @author Jiahan Wang
     * @create 2020-07-12 14:40
     * @updator Jiahan Wang
     * @update 2020-07-18 14:40
     * @param keyWord 关键字
     */
    @Override
    public List<AlgorithmResponseVo> getAllFullAlgorithms(String keyWord) {
        keyWord = "%"+keyWord+"%";
        List<AlgorithmResponseVo> algorithmResponseVos = algorithmMapper.selectAlgorithmsBasicInfo(keyWord);
        return algorithmResponseVos;
    }

        /**
     * 根据用户ID和关键字查询算法
     * @param userId  用户ID
     * @param keyWord 关键字
     * @return
     */
    @Override
    public List<AlgorithmResponseVo> getAlgorithmsByUserId(Integer userId, String keyWord) {
        List<AlgorithmResponseVo> algorithmResponseVos = algorithmMapper.selectUsersAlgorithmsBasicInfo(keyWord, userId);
        return algorithmResponseVos;
    }

}
