package com.whu.python_version.service.impl;

import com.entity.PythonVersion;
import com.mapper.PythonVersionMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.whu.python_version.service.IPythonVersionService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Hedon
 * @since 2020-07-11
 */
@Service
public class PythonVersionServiceImpl extends ServiceImpl<PythonVersionMapper, PythonVersion> implements IPythonVersionService {

}
