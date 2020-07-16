package com.whu.service;

import com.entity.Algorithm;
import com.entity.AlgorithmDescription;
import com.entity.HyperParameters;
import com.results.CommonResult;
//import com.whu.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Hedon Wang
 * @create 2020-07-14 10:57
 */
@Service
@FeignClient(value = "common-algorithm"/*,configuration = FeignConfig.class*/)
public interface AlgorithmFeignService {

    @GetMapping("/algorithms")
    CommonResult selectAllAlgorithms(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                     @RequestParam(value = "pageSize",defaultValue = "6")Integer pageSize,
                                     @RequestParam(value = "keyWord")String keyWord);

    @PostMapping("/algorithm")
    public Algorithm addAlgorithm(@RequestBody Algorithm algorithm);

    @PostMapping("/algorithm/description")
    public AlgorithmDescription addDescription (@RequestBody AlgorithmDescription description);

    @PostMapping("/algorithm/hyper-parameters")
    public HyperParameters addHyperParameters(@RequestBody HyperParameters hyperParameters);
}
