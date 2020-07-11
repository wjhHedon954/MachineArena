package com.whu.generator.test.service.impl;

import com.whu.generator.test.entity.Test;
import com.whu.generator.test.mapper.TestMapper;
import com.whu.generator.test.service.ITestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Hedon
 * @since 2020-07-10
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements ITestService {

}
