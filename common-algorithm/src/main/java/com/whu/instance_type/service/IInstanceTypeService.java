package com.whu.instance_type.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.entity.InstanceType;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Hedon
 * @since 2020-07-14
 */
public interface IInstanceTypeService extends IService<InstanceType> {
    List<InstanceType> getInstanceType();
}
