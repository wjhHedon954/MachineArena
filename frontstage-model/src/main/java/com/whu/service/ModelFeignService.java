package com.whu.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;

/**
 * @author Hedon Wang
 * @create 2020-07-17 21:58
 */

@Service
@FeignClient(value = "common-model")
public interface ModelFeignService {


}
