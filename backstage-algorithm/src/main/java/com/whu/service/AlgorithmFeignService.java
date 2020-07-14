package com.whu.service;

import com.results.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Hedon Wang
 * @create 2020-07-14 10:57
 */
@Service
@FeignClient(value = "common-algorithm")
public interface AlgorithmFeignService {

    @GetMapping("/algorithms")
    public CommonResult selectAllAlgorithms(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                                            @RequestParam(value = "pageSize",defaultValue = "6")Integer pageSize,
                                            @RequestParam(value = "keyWord",defaultValue = "")String keyWord);
}
