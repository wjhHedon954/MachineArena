package com.whu.controller;

import com.entity.Algorithm;
import com.entity.AlgorithmUser;
import com.results.CommonResult;
import com.whu.service.AlgorithmFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Hedon Wang
 * @create 2020-07-14 10:50
 */

@RestController
@RequestMapping("/backstage")
public class AlgorithmController {


    @Autowired
    AlgorithmFeignService algorithmFeignService;

    /**
     * 分页查询算法
     * @param pageNum
     * @param pageSize
     * @param keyWord
     * @return
     */
    @GetMapping("/algorithms")
    public CommonResult selectAllAlgorithms(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                                            @RequestParam(value = "pageSize",defaultValue = "6")Integer pageSize,
                                            @RequestParam(value = "keyWord",defaultValue = "")String keyWord){

        return algorithmFeignService.selectAllAlgorithms(pageNum,pageSize,keyWord);
    }
}
