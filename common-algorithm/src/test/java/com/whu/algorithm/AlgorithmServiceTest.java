package com.whu.algorithm;

import com.entity.Algorithm;
import com.whu.algorithm.service.IAlgorithmService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 算法服务层的单元测试
 * @author Hedon Wang
 * @create 2020-07-12 08:58
 */
@SpringBootTest
public class AlgorithmServiceTest {


    @Autowired
    IAlgorithmService algorithmService;

    /**
     * 根据ID查询算法 —— 没有数据
     * @author Jiahan Wang
     * @create 2020-07-12 09:20
     * @update 2020-07-12 09:20
     * @result 输出结果为："null"，符合预期
     */
    @Test
    public void testGetAlgorithmById1_noData(){
        Algorithm algorithm = algorithmService.getAlgorithmById(999);
        System.out.println(algorithm);
    }


    /**
     * 根据ID查询算法  —— 存在数据
     * @author Jiahan Wang
     * @create 2020-07-12 09:20
     * @update 2020-07-12 09:20
     * @result 输出结果为："Algorithm(~~~)" 符合预期
     */
    @Test
    public void testGetAlgorithmById1_hasData(){
        Algorithm algorithm = algorithmService.getAlgorithmById(3);
        System.out.println(algorithm);
    }

}
