package com.whu.algorithm_type.controller;


import com.entity.Algorithm;
import com.entity.AlgorithmType;
import com.mapper.AlgorithmTypeMapper;
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
public class AlgorithmTypeController {
    @Autowired
    private AlgorithmTypeMapper algorithmTypeMapper;

    @RequestMapping(value = "/algorithm/instanceTypes",method = RequestMethod.GET)
    public CommonResult selectInstanceTypes(){
        //查询获得所有的instanceTypes
        List<AlgorithmType> algorithmTypes = algorithmTypeMapper.selectList(null);

        //查询到的List空返回错误信息
        if(algorithmTypes==null)
            return new CommonResult("错误代码*****","查询的List为空");

        //无instanceTypes返回错误信息
        if (algorithmTypes.size()==0)
            return new CommonResult("错误代码****","无instanceTypes");

        //一切正常就往map中put进查询到的对象，返回正确信息
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("algorithmTypes",algorithmTypes);
        return new CommonResult("00000","查询成功",map);
    }
}
