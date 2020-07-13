package com.whu.algorithm.controller;


import cn.hutool.http.server.HttpServerRequest;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.constants.ResultCode;
import com.entity.Algorithm;
import com.entity.AlgorithmDescription;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.responsevo.AlgorithmResponseVo;
import com.entity.HyperParameters;
import com.mapper.AlgorithmMapper;
import com.results.CommonResult;
import com.whu.algorithm.service.IAlgorithmService;
import com.whu.algorithm_description.service.IAlgorithmDescriptionService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.awt.print.Book;
import java.util.List;


import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Hedon
 * @since 2020-07-11
 */
@RestController
public class AlgorithmController {

    @Autowired
    IAlgorithmService algorithmService;

    @Autowired
    IAlgorithmDescriptionService algorithmDescriptionService;

    /**
     * 接口 6.1.2 创建算法
     * @author Huiri Tan
     * @create 2020-07-11 20:00
     * @updator Huiri Tan
     * @update 2020-07-12 10:30
     * @param request 从前端获取data数据，根据数据创建算法对象
     * @return  返回算法信息
     */
    @ApiOperation(value = "接口6.1.2", httpMethod = "POST", notes = "创建算法")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "data", value = "算法创建信息")
    })
    @PostMapping(value = "/algorithm")
    public CommonResult addAlgorithm(HttpServletRequest request) {
        Algorithm algorithm = new Algorithm(); // 要创建的算法对象
        MultipartHttpServletRequest params =  (MultipartHttpServletRequest)request;
        List<MultipartFile> files  =  ((MultipartHttpServletRequest)request).getFiles("myfile");

        // 从传入的数据中获取data并转换为JSONObject 顺便获取超参数
        JSONObject data = null;
        JSONArray hyperParameters = null;
        try {
            JSONObject tmp = new JSONObject(params.getParameter("data"));
            data = (JSONObject)tmp.get("data");
            hyperParameters = JSONUtil.parseArray(data.get("hyperparameters"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        assert data != null;

        AlgorithmDescription algorithmDescription = new AlgorithmDescription();
        algorithmDescription.setAlgorithmDescriptionContent(data.get("algorithm_description").toString());
        algorithmDescriptionService.addDescription(algorithmDescription);

        algorithm.setAlgorithmName(data.get("algorithm_name").toString());
        algorithm.setAlgorithmVersion(data.get("algorithm_version").toString());
        algorithm.setAlgorithmTypeId((int)data.get("algorithm_type_id"));
        algorithm.setAlgorithmEngineId((int)data.get("algorithm_engine_id"));
        algorithm.setAlgorithmDescriptionId(algorithmDescription.getAlgorithmDescriptionId());
        algorithm.setAlgorithmInstanceTypeId((int)data.get("algorithm_instance_type_id"));
        algorithm.setAlgorithmInputReflect(data.get("algorithm_input_reflect").toString());
        algorithm.setAlgorithmOutputReflect(data.get("algorithm_output_reflect").toString());
        algorithm.setAlgorithmStarterUrl(data.get("algorithm_starter_URL").toString());
        algorithm.setAlgorithmSaveUrl("/Users/thomas/Desktop/Data");    // 暂时写死
        algorithm.setAlgorithmCustomizeHyperPara((boolean)data.get("algorithm_customize_hyper_para"));
        algorithm.setAlgorithmPythonVersionId((int)data.get("algorithm_python_version_id"));

        int addResult = algorithmService.addAlgorithm(algorithm);

        // 循环保存文件
        for (MultipartFile file : files) {
            String originName = file.getOriginalFilename();
            String fileName = originName;
            String originPath = "";
            if (originName == null) {
                return CommonResult.fail(ResultCode.ERROR); // 文件名为空暂时返回未知错误;
            }
            if(originName.contains("/")) {
                fileName = originName.substring(originName.lastIndexOf('/'));
                originPath = originName.substring(0, originName.lastIndexOf('/'));
            }

            String filePath = "/Users/thomas/Desktop/Data/" + originPath;
            File newFile = new File(filePath);
            if(!newFile.exists()) {
                newFile.mkdirs();
            }
            try {
                file.transferTo(new File(newFile + "/" + fileName));
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }


        return CommonResult.success().add("algorithm id: ", algorithm.getAlgorithmId().toString());
    }


    /**
     * 接口 6.1.7 编辑算法
     *
     * @param algorithm 从前端获取得到一个完整的被用户编辑后的对象
     * @return 返回这个被编辑的对象，不一定有用，如果前端需要可以查看里面的数据
     * @author Yi Zheng
     * @create 2020-07-11 20:00
     * @updator Jiahan Wang
     * @update 2020-07-12 08:30
     */
    @ApiOperation(value = "接口6.1.7", httpMethod = "PUT", notes = "更新算法信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "algorithm", value = "算法对象", paramType = "body", dataType = "Algorithm", required = true)
    })
    @PutMapping(value = "/algorithm")
    public CommonResult updateAlgorithm(@RequestBody Algorithm algorithm) {
        //检查对象是否为空
        if (algorithm == null) {
            return CommonResult.fail(ResultCode.EMPTY_OBJECT);
        }
        try {
            //执行更新
            int updateCount = algorithmService.updateAlgorithm(algorithm);
            if (updateCount == 0) {
                //如果更新条数为0，则说明该算法数据不在数据库中，返回数据不存在信息
                return CommonResult.fail(ResultCode.ALGORITHM_NOT_EXIST);
            } else {
                //因为是根据ID来更改，所以情况只有 0 和 1，如果不为 0 那必定是成功
                return CommonResult.success();
            }
        } catch (Exception e) {
            return CommonResult.fail(ResultCode.ERROR);
        }
    }

    /**
     * 接口 6.1.6 删除算法
     *
     * @param id 算法的id，要根据这个id来删除算法
     * @return 通用返回结果
     * @author Yi Zheng
     * @create 2020-07-11 20:10
     * @updator Jiahan Wang
     * @update 2020-07-12 08:45
     */
    @ApiOperation(value = "接口 6.1.6 删除算法", httpMethod = "DELETE", notes = "删除算法，不从数据库中真实删除，而是软删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "算法ID", paramType = "path", dataType = "Integer", required = true)
    })
    @DeleteMapping(value = "/algorithm/{id}")
    public CommonResult deleteAlgorithmById(@PathVariable("id") Integer id) {
        //检查ID是否为空
        if (id == null) {
            return CommonResult.fail(ResultCode.EMPTY_ALGORITHM_ID);
        }
        //执行删除操作
        try {
            int deleteCount = algorithmService.deleteAlgorithmById(id);
            if (deleteCount == 0) {
                return CommonResult.fail(ResultCode.ALGORITHM_NOT_EXIST);
            } else {
                return CommonResult.success();
            }
        } catch (Exception e) {
            return CommonResult.fail(ResultCode.ERROR);
        }

    }

    /**
     * 接口 6.1.5 根据 ID 查询算法
     *
     * @param id 算法ID
     * @return 通用返回结果
     * @author Yizheng
     * @create 2020-07-11 20:55
     * @updator Jiahan Wang
     * @update 2020-07-12 08:50
     */
    @ApiOperation(value = "接口 6.1.5 根据ID查询算法", httpMethod = "GET", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "算法ID", paramType = "path", dataType = "Integer", required = true)
    })
    @GetMapping(value = "/algorithm/{id}")
    public CommonResult selectById(@PathVariable("id") Integer id) {
        //检查ID是否为空
        if (id == null) {
            return CommonResult.fail(ResultCode.EMPTY_ALGORITHM_ID);
        }
        //执行查询操作
        Algorithm algorithm = algorithmService.getAlgorithmById(id);
        //如果对象为空，则说明该算法不存在
        if (algorithm == null) {
            return CommonResult.fail(ResultCode.ALGORITHM_NOT_EXIST);
        }
        //若一切正常，则将该算法对象封装到 extend 中传给前端
        return CommonResult.success().add("algorithm", algorithm);
    }


    /**
     * 接口 6.1.1 分页查询算法
     *
     * @return 通用查询到的分页数据
     * @author Jiahan Wang
     * @create 2020-07-12 14:55
     * @updator Jiahan Wang
     * @update 2020-07-12 14:55
     */
    @ApiOperation(value = "接口 6.1.1 分页查询算法",httpMethod = "GET",notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "当前页码",paramType = "query",dataType = "Integer",required = true),
            @ApiImplicitParam(name = "pageSize",value = "页面大小",paramType = "query",dataType = "Integer",required = true)
    })
    @GetMapping("/algorithms")
    public CommonResult selectAllAlgorithms(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                                            @RequestParam(value = "pageSize",defaultValue = "6")Integer pageSize,
                                            @RequestParam(value = "keyWord")String keyWord){
        //1. 开启分页查询
        PageHelper.startPage(pageNum,pageSize);
        //2. 从数据库拉取信息
        List<AlgorithmResponseVo> algorithmResponseVos =  algorithmService.getAllFullAlgorithms(keyWord);
        //3. 封装到 pageInfo 中
        PageInfo pageInfo = new PageInfo(algorithmResponseVos,5);
        //4. 传给前端
        return CommonResult.success().add("pageInfo",pageInfo);
    }

}
