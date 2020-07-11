package com.whu.python_version.controller;


import com.entity.AlgorithmType;
import com.entity.PythonVersion;
import com.mapper.PythonVersionMapper;
import com.results.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
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

    /**
     * @Author Izumi Sakai
     * @return 通用返回结果
     */
    @RequestMapping(value = "/alogrithom/engines",method = RequestMethod.GET)
    public CommonResult selectInstanceTypes(){
        //查询获得所有的engines
        List<PythonVersion> pythonVersions = pythonVersionMapper.selectList(null);

        //查询到的List空返回错误信息
        if(pythonVersions==null)
            return new CommonResult("错误代码*****","查询的List为空");

        //无engines返回错误信息
        if (pythonVersions.size()==0)
            return new CommonResult("错误代码****","无engines");

        //一切正常就往map中put进查询到的对象，返回正确信息
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("engines",pythonVersions);
        return new CommonResult("00000","查询成功",map);
    }
}
