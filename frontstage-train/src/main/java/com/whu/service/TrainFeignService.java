package com.whu.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;

/**
 * OpenFeign跨服务调用层，指向 common-train
 *
 * @author Hedon Wang
 * @create 2020-07-17 11:38
 */

@Service
@FeignClient(value = "common-train")
public interface TrainFeignService {

}
