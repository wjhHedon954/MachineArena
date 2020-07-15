package com.whu.hyper_parameters.service;

import com.entity.HyperParameters;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Hedon
 * @since 2020-07-11
 */
public interface IHyperParametersService extends IService<HyperParameters> {
    /**
     * @author Huiri Tan
     * @description 添加超参数
     * @create 2020/7/14 1:06 上午
     * @update 2020/7/14 1:06 上午
     * @param hyperParameter
     * @return int
     **/
    int addHyperParameter(HyperParameters hyperParameter);


    /**
     * 根据算法ID查询超参规范
     * @author Jiahan Wang
     * @create 2020-07-15 16:32
     * @updator Jiahan Wang
     * @upadte 2020-07-15 16:32
     * @param algorithmId
     * @return
     */
    List<HyperParameters> getHyperParaByAlgorithmId(Integer algorithmId);
}
