package com.whu.algorithm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.entity.Algorithm;
import com.responsevo.AlgorithmResponseVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Hedon
 * @since 2020-07-11
 */
public interface IAlgorithmService extends IService<Algorithm> {

    /**
     * 编辑算法
     * @author Jiahan Wang
     * @create 2020-07-12 08:23
     * @update 2020-07-12 08:23
     * @param algorithm  算法对象
     * @return 数据库更新条数
     */
    int updateAlgorithm(Algorithm algorithm);

    /**
     * 删除算法
     * @author Jiahan Wang
     * @create 2020-07-12 08:23
     * @update 2020-07-12 08:23
     * @param id 算法ID
     * @return 删除条目数
     */
    int deleteAlgorithmById(Integer id);

    /**
     * 根据ID查询算法
     * @author Jiahan Wang
     * @create 2020-07-12 08:55
     * @update 2020-07-12 08:55
     * @param id 算法ID
     * @return 删除条目数
     */
    Algorithm getAlgorithmById(Integer id);

    /**
     * 分页查询算法，附带其他信息
     * @author Jiahan Wang
     * @create 2020-07-12 14:40
     * @update 2020-07-12 14:40
     * @param  keyWord 关键字
     */
    List<AlgorithmResponseVo> getAllFullAlgorithms(String keyWord);
}
