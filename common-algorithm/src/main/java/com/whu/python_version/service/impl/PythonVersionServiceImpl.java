package com.whu.python_version.service.impl;

import com.entity.PythonVersion;
import com.mapper.PythonVersionMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.whu.python_version.service.IPythonVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Autowired
    PythonVersionMapper pythonVersionMapper;


    /**
     * 查询所有的 Python 版本
     * @author Jiahan Wang
     * @create 2020-07-12 09-12
     * @update 2020-07-12 09-12
     * @return 所有的Python版本
     */
    @Override
    public List<PythonVersion> getAllPythonVersions() {
        List<PythonVersion> pythonVersions = pythonVersionMapper.selectList(null);
        return pythonVersions;
    }
}
