package com.whu.hyper_parameters;

import com.entity.HyperParameters;
import com.whu.hyper_parameters.service.IHyperParametersService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author Hedon Wang
 * @create 2020-07-15 17:04
 */

@SpringBootTest
public class HyperParametersTest {


    @Autowired
    IHyperParametersService hyperParametersService;

    /**
     * 测试根据算法ID获得算法超参规范 - 存在数据
     */
    @Test
    public void testSelectHyperParametersByAlgorithmIdHasData(){
        List<HyperParameters> hyperParaByAlgorithmId = hyperParametersService.getHyperParaByAlgorithmId(17);
        System.out.println(hyperParaByAlgorithmId);
    }

    /**
     * 测试根据算法ID获得算法超参规范 - 不存在数据
     */
    @Test
    public void testSelectHyperParametersByAlgorithmIdNoData(){
        List<HyperParameters> hyperParaByAlgorithmId = hyperParametersService.getHyperParaByAlgorithmId(9999);
        System.out.println(hyperParaByAlgorithmId);
    }
}
