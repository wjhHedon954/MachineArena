package com.whu.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;

/**
 * @author Hedon Wang
 * @create 2020-07-14 10:57
 */
@Service
@FeignClient(value = "common-algorithm")
public interface AlgorithmFeignService {

}
