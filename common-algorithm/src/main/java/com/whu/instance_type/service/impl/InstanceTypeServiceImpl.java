package com.whu.instance_type.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.entity.InstanceType;
import com.mapper.InstanceTypeMapper;
import com.whu.instance_type.service.IInstanceTypeService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Hedon
 * @since 2020-07-14
 */
@Service
public class InstanceTypeServiceImpl extends ServiceImpl<InstanceTypeMapper, InstanceType> implements IInstanceTypeService {
    @Autowired
    InstanceTypeMapper instanceTypeMapper;
    
    /**
     * @author Huiri Tan
     * @description 查询所有实例类型
     * @create 2020/7/17 11:40 上午
     * @update 2020/7/17 11:40 上午
     * @param [] 
     * @return java.util.List<com.entity.InstanceType> 
     **/
    @Override
    public List<InstanceType> getInstanceType() {
        return instanceTypeMapper.selectList(new QueryWrapper<>());
    }
}
