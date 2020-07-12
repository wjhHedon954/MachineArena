package com.whu.python_version.service;

import com.entity.PythonVersion;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Hedon
 * @since 2020-07-11
 */
public interface IPythonVersionService extends IService<PythonVersion> {

    /**
     * 查询所有的 Python 版本
     * @author Jiahan Wang
     * @create 2020-07-12 09-12
     * @update 2020-07-12 09-12
     * @return 所有的Python版本
     */
    List<PythonVersion> getAllPythonVersions();
}
