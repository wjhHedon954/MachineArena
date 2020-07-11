package com.whu.algorithm.controller;


import com.entity.Algorithm;
import com.mapper.AlgorithmMapper;
import com.results.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
public class AlgorithmController {
    @Autowired
    private AlgorithmMapper algorithmMapper;

    /**
     * @author Izumi Sakai
     * @param algorithm 从前端获取得到一个完整的被用户编辑后的对象
     * @return  返回这个被编辑的对象，不一定有用，如果前端需要可以查看里面的数据
     */
    @RequestMapping(value = "/algotithm/",method = RequestMethod.PUT)
    public CommonResult update(@RequestBody Algorithm algorithm){
        //update这个从前端返回的对象，如果中途报错就返回编辑算法失败
        try{
            algorithmMapper.updateById(algorithm);
        }catch (Exception e){
            return new CommonResult("错误代码****","编辑算法失败");
        }

        //根据id从数据库中获取update的对象
        Algorithm updatedAlgorithm = algorithmMapper.selectById(algorithm.getAlgorithmId());

        //如果获取不到就返回编辑后的算法不存在错误信息
        if (updatedAlgorithm==null)
            return new CommonResult("错误代码****","编辑后的算法不存在");

        //一切正常就往map中put进update后的对象，返回正确信息
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("algorithm",algorithm);
        return new CommonResult("错误代码****","编辑成功",map);
    }

    /**
     * @Author Izumi Sakai
     * @param id 算法的id，要根据这个id来删除算法
     * @return 通用返回结果
     */
    @RequestMapping(value = "/algotithm/{id}",method = RequestMethod.DELETE)
    public CommonResult deleteById(@PathVariable("id") Integer id){
        //根据ID delete从前端返回的对象，如果中途报错就返回删除算法失败
        try{
            algorithmMapper.deleteById(id);
        }catch (Exception e){
            return new CommonResult("错误代码****","删除算法失败");
        }
        //删除成功返回正确的信息
        return new CommonResult("错误代码****","删除成功");
    }

    /**
     * @Author Izumi Sakai
     * @param id
     * @return
     */
    @RequestMapping(value = "/algotithm/{id}",method = RequestMethod.GET)
    public CommonResult selectById(@PathVariable("id") Integer id){
        //根据id查询得到这个算法对象
        Algorithm algorithm = algorithmMapper.selectById(id);

        //如果获取不到就返回编辑后的算法不存在错误信息
        if (algorithm==null)
            return new CommonResult("错误代码****","查询的算法不存在");

        //一切正常就往map中put进查询到的对象，返回正确信息
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("algorithm",algorithm);
        return new CommonResult("错误代码****","查询成功",map);
    }

}
