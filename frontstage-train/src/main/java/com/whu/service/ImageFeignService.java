package com.whu.service;

import com.entity.Algorithm;
import com.results.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName ImageFeignService
 * @Description 创建镜像时需要调用通用算法层的服务
 * @Author thomas
 * @Date 2020/7/20 12:07 下午
 * @Version 1.0
 */
@FeignClient(value = "common-algorithm")
public interface ImageFeignService {
    @PutMapping("/algorithm")
    CommonResult updateAlgorithm(@RequestBody Algorithm algorithm);

    @GetMapping("/algorithm/object/{algorithmId}")
    Algorithm getAlgorithmObjectById(@PathVariable(value = "algorithmId")Integer algorithmId);

    @DeleteMapping(value = "/algorithm/{id}")
    CommonResult deleteAlgorithmById(@PathVariable("id") Integer id);
}
