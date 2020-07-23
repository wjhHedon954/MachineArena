package com.whu.algorithm.service.impl;

import com.entity.Algorithm;
import com.responsevo.AlgorithmFullResponseVo;
import com.responsevo.AlgorithmResponseVo;
import com.whu.algorithm.service.IAlgorithmService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * AlgorithmServiceImpl 单元测试
 * @author Hedon Wang
 * @create 2020-07-23 16:05
 */

@SpringBootTest
class AlgorithmServiceImplTest {


    @Autowired
    IAlgorithmService algorithmService;

    /**
     * 添加算法
     * @author Jiahan Wang
     * @create 2020-07-23 16:10
     * @update 2020-07-23 16:10
     * @result 输出结果为："1"，符合预期
     */
    @Test
    void addAlgorithm() {
        Algorithm algorithm = new Algorithm();
        algorithm.setAlgorithmName("单元测试1");
        algorithm.setAlgorithmVersion("v1");
        algorithm.setAlgorithmTypeId(1);
        algorithm.setAlgorithmEngineId(1);
        algorithm.setAlgorithmDescriptionId(1);
        algorithm.setAlgorithmStarterUrl("启动路径");
        algorithm.setAlgorithmCustomizeHyperPara(true);
        algorithm.setAlgorithmStatus(1);
        algorithm.setAlgorithmSaveUrl("保存路径");
        algorithm.setAlgorithmInputReflect("输出路径");
        int i = algorithmService.addAlgorithm(algorithm);
        System.out.println(i);

    }


    /**
     * 根据ID查询算法
     * @author Jiahan Wang
     * @create 2020-07-23 16:15
     * @update 2020-07-23 16:15
     * @result 输出结果为："Algorithm(algorithmId=83, algorithmName=单元测试1, algorithmVersion=v1, algorithmTypeId=1, algorithmEngineId=1, algorithmDescriptionId=1, algorithmInstanceTypeId=null, algorithmInputReflect=输出路径, algorithmOutputReflect=null, algorithmStarterUrl=启动路径, algorithmSaveUrl=保存路径, algorithmCustomizeHyperPara=true, algorithmStatus=1, algorithmCreateTime=2000-01-01T08:01, algorithmImageId=null)"，
     * @result 输出结果为："1"，符合预期
     */
    @Test
    void getAlgorithmObjectById() {
        Algorithm algorithmObjectById = algorithmService.getAlgorithmObjectById(83);
        System.out.println(algorithmObjectById);
    }


    /**
     * 更新算法
     * @author Jiahan Wang
     * @create 2020-07-23 16:20
     * @update 2020-07-23 16:20
     * @result 输出结果为："Algorithm(algorithmId=83, algorithmName=单元测试更新算法, algorithmVersion=v1, algorithmTypeId=1, algorithmEngineId=1, algorithmDescriptionId=1, algorithmInstanceTypeId=null, algorithmInputReflect=输出路径, algorithmOutputReflect=null, algorithmStarterUrl=启动路径, algorithmSaveUrl=保存路径, algorithmCustomizeHyperPara=true, algorithmStatus=1, algorithmCreateTime=2000-01-01T08:01, algorithmImageId=null)"
     *              符合预期
     */
    @Test
    void updateAlgorithm() {
        Algorithm algorithmObjectById = algorithmService.getAlgorithmObjectById(83);
        System.out.println(algorithmObjectById);
        algorithmObjectById.setAlgorithmName("单元测试更新算法");
        algorithmService.updateAlgorithm(algorithmObjectById);
        Algorithm algorithmObjectById1 = algorithmService.getAlgorithmObjectById(83);
        System.out.println(algorithmObjectById1);
    }


    /**
     * 根据ID删除算法  => 算法存在情况
     * @author Jiahan Wang
     * @create 2020-07-23 16:23
     * @update 2020-07-23 16:23
     * @result 输出结果为"1"    符合预期
     */
    @Test
    void deleteAlgorithmById() {
        int i = algorithmService.deleteAlgorithmById(83);
        System.out.println(i);
    }

    /**
     * 根据ID删除算法  => 算法不存在情况
     * @author Jiahan Wang
     * @create 2020-07-23 16:23
     * @update 2020-07-23 16:23
     * @result 输出结果为"0"    符合预期
     */
    @Test
    void deleteAlgorithmById2() {
        int i = algorithmService.deleteAlgorithmById(10000);
        System.out.println(i);
    }


    /**
     * 根据ID查询算法详细信息  => 算法存在情况
     * @author Jiahan Wang
     * @create 2020-07-23 16:23
     * @update 2020-07-23 16:23
     * @result 输出结果为"AlgorithmFullResponseVo(algorithmId=3, algorithmName=4342, algorithmVersion=4234, algorithmTypeId=1, algorithmType=AlgorithmType(algorithmTypeId=1, algorithmTypeName=图像分类, algorithmTypeDescription=暂时没有描述), algorithmEngineId=2, aiEngine=AiEngine(algorithmEngineId=2, algorithmEngineName=TensorFlow, algorithmEngineVersion=1.13.0, pythonVersionName=python3.7), algorithmDescriptionId=1, algorithmDescription=null, algorithmInstanceTypeId=1, instanceType=InstanceType(instanceTypeId=1, instanceTypeDescription=单核 GTX 1080Ti), algorithmInputReflect=423, algorithmOutputReflect=423, algorithmStarterUrl=trainmnist/main/train.py, algorithmSaveUrl=423, algorithmCustomizeHyperPara=1, algorithmStatus=0, algorithmCreateTime=2000-01-01T08:01, algorithmImageId=48985c382eb9)"    符合预期
     */
    @Test
    void getAlgorithmFullInfoById() {
        AlgorithmFullResponseVo algorithmFullInfoById = algorithmService.getAlgorithmFullInfoById(3);
        System.out.println(algorithmFullInfoById);
    }

    /**
     * 模糊搜索所有算法的详细信息
     * @author Jiahan Wang
     * @create 2020-07-23 16:27
     * @update 2020-07-23 16:27
     * @resukt 输出结果符合预期
     */
    @Test
    void getAllFullAlgorithms() {
        List<AlgorithmResponseVo> allFullAlgorithms = algorithmService.getAllFullAlgorithms("");
        for (AlgorithmResponseVo algorithmResponseVo:allFullAlgorithms) {
            System.out.println(algorithmResponseVo);
        }
    }

    /**
     * 模糊搜索当前用户下的所有算法的详细信息
     * @author Jiahan Wang
     * @create 2020-07-23 16:33
     * @update 2020-07-23 16:33
     * @resukt 输出结果符合预期
     */
    @Test
    void getAlgorithmsByUserId() {
        List<AlgorithmResponseVo> algorithmsByUserId = algorithmService.getAlgorithmsByUserId(1, "");
        for (AlgorithmResponseVo algorithmResponseVo:algorithmsByUserId){
            System.out.println(algorithmResponseVo);
        }
    }
}