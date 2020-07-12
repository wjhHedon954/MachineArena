package com.whu.python_version.controller;


import com.constants.ResultCode;
import com.entity.AlgorithmType;
import com.entity.PythonVersion;
import com.mapper.PythonVersionMapper;
import com.results.CommonResult;
import com.whu.python_version.service.IPythonVersionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Hedon
 * @since 2020-07-11
 */
@RestController
public class PythonVersionController {
    @Autowired
    private PythonVersionMapper pythonVersionMapper;

    @Autowired
    IPythonVersionService pythonVersionService;

    /**
     * 查询 Python 版本
     * @author Yi Zheng
     * @create 2020-07-11 21:01
     * @updator Jiahan Wang
     * @update 2020-07-12 09:12
     * @return 通用返回结果
     */
    @ApiOperation(value = " 6.1.5 查询 Python 版本",httpMethod = "GET",notes = "")
    @GetMapping(value = "/algorithm/pythonVersions")
    public CommonResult selectInstanceTypes(){
        //查询获得所有的engines
        List<PythonVersion> pythonVersions = pythonVersionService.getAllPythonVersions();

        //若为空
        if( pythonVersions == null || pythonVersions.size() == 0)
        {
            return CommonResult.fail(ResultCode.NO_PYTHON_VERSION_DATA);
        }

        //一切正常
        return CommonResult.success().add("pythonVersions",pythonVersions);

    }
}
