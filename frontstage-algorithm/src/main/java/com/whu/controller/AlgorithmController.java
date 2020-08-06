package com.whu.controller;

import cn.hutool.core.util.ZipUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.constants.ResultCode;
import com.entity.Algorithm;
import com.entity.AlgorithmDescription;
import com.entity.HyperParameters;
import com.results.CommonResult;
import com.whu.service.AlgorithmFeignService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Hedon Wang
 * @create 2020-07-14 10:50
 */

@RestController
@RequestMapping("/frontstage")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = "*")
public class AlgorithmController {


    @Autowired
    AlgorithmFeignService algorithmFeignService;

    /**
     * @author Huiri Tan
     * @description 前台创建算法
     * @create 2020/7/23 11:00 上午
     * @update 2020/7/23 11:00 上午
     * @param [request]
     * @return com.results.CommonResult
     **/
    @PostMapping("/algorithm")
    public CommonResult addAlgorithm(HttpServletRequest request) {
        Algorithm algorithm = new Algorithm(); // 要创建的算法对象
        List<MultipartFile> files = null;
        MultipartHttpServletRequest params =  (MultipartHttpServletRequest)request;
        try {
            files  =  ((MultipartHttpServletRequest)request).getFiles("myfile");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        // 从传入的数据中获取data并转换为JSONObject 顺便获取超参数
        JSONObject data = null;
        JSONArray hyperParameters = null;
        try {
//            JSONObject tmp = new JSONObject(params.getParameter("data"));
            data =  new JSONObject(params.getParameter("data"));
            hyperParameters = JSONUtil.parseArray(data.get("hyperParameters"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        assert data != null;
        // 首先保存算法描述
        AlgorithmDescription algorithmDescription = new AlgorithmDescription();
        try{
            algorithmDescription.setAlgorithmDescriptionContent(data.get("algorithm_description").toString());
            algorithmDescription.setAlgorithmDescriptionId(0);  // 给ID丢个值，不然请求转发的时候报错
            algorithmDescription = algorithmFeignService.addDescription(algorithmDescription);    // 添加算法描述
        }
        catch (Exception e) {
            e.printStackTrace();
        }

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
        algorithm.setAlgorithmStatus(0);
        algorithm.setAlgorithmCreateTime(LocalDateTime.now());
        algorithm.setAlgorithmId(0);    // 丢个数给ID 免得转发会报错

        // 保存算法
        algorithm = algorithmFeignService.addAlgorithm(algorithm); //添加算法

        // 若有超参数则保存
        if(hyperParameters != null) {
            for(int i = 0; i < hyperParameters.size(); i++) {
                HyperParameters hyperParameter = new HyperParameters();
                hyperParameter.setHyperParaName(hyperParameters.getJSONObject(i).get("hyper_para_name").toString());
                hyperParameter.setHyperParaDescription(hyperParameters.getJSONObject(i).get("hyper_para_description").toString());
                hyperParameter.setHyperParaType((int)hyperParameters.getJSONObject(i).get("hyper_para_type"));
                hyperParameter.setHyperParaAllowAdjust((boolean)hyperParameters.getJSONObject(i).get("hyper_para_allow_adjust"));
                hyperParameter.setHyperParaRange(hyperParameters.getJSONObject(i).get("hyper_para_range").toString());
                hyperParameter.setHyperParaDefaultValue(hyperParameters.getJSONObject(i).get("hyper_para_default_value").toString());
                hyperParameter.setHyperParaIsNeeded((boolean)hyperParameters.getJSONObject(i).get("hyper_para_is_needed"));
                hyperParameter.setAlgorithmId(algorithm.getAlgorithmId());
                hyperParameter.setHyperParaId(0);       // 丢个数给ID 免得转发会报错
                algorithmFeignService.addHyperParameters(hyperParameter);
            }
        }

        if(files == null)
            return CommonResult.fail(ResultCode.EMPTY_OBJECT);

        String fileDir = null;

        File dataDir = new File("data");
        if(!dataDir.exists())
            dataDir.mkdirs();
        System.out.println(dataDir.getAbsolutePath());
        // 循环保存文件
        for (MultipartFile file : files) {
            String originName = file.getOriginalFilename();
            String fileName = originName;
            String originPath = "";

            if (originName == null) {
                return CommonResult.fail(ResultCode.ERROR); // 文件名为空暂时返回未知错误;
            }

            if (originName.contains("/")) {
                fileName = originName.substring(originName.lastIndexOf('/') + 1);
                originPath = originName.substring(0, originName.lastIndexOf('/') + 1);
                if(fileDir == null)
                    fileDir = "/var/tmp/" + originName.substring(0, originName.indexOf('/'));        // 文件夹最终存储路径

            }
            else {
                // 单个文件的情况
                if(fileDir == null)
                    fileDir = "/var/tmp/" + fileName;
            }

            String filePath = "/var/tmp/" + originPath;
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

        ZipUtil.zip(fileDir, fileDir + ".zip",true);


        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "http://oss-cn-beijing.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = "";
        String accessKeySecret = "";

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 创建PutObjectRequest对象。
        String objPath = "1/" + algorithm.getAlgorithmId().toString() + "/code" + fileDir.substring(fileDir.lastIndexOf('/')) + ".zip";
        PutObjectRequest putObjectRequest = new PutObjectRequest("thomas10011-image",
                objPath,
                new File(fileDir + ".zip"));

        // 上传文件。
        ossClient.putObject(putObjectRequest);
        // 关闭OSSClient。
        ossClient.shutdown();

        algorithm.setAlgorithmSaveUrl("https://thomas10011-image.oss-cn-beijing.aliyuncs.com/" + objPath);

        return algorithmFeignService.updateAlgorithm(algorithm).add("algorithmId", algorithm.getAlgorithmId());
    }


    /**
     * 接口 6.1.1.1 分页查询当前用户的算法
     * @author Jiahan Wang
     * @create 2020-07-15 14:59
     * @updator Jiahan Wang
     * @upadte 2020-07-15 14:59
     * @param userId    用户ID
     * @param pageNum   当前页吗
     * @param pageSize  页面大小
     * @param keyWord   搜索关键字
     * @return
     */
    @ApiOperation(value = "接口 6.1.1.1 分页查询当前用户的算法 ",httpMethod = "GET",notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "当前用户ID",paramType = "path",dataType = "Integer",required = true),
            @ApiImplicitParam(name = "pageNum",value = "当前页码",paramType = "query",dataType = "Integer",required = true),
            @ApiImplicitParam(name = "pageSize",value = "页面大小",paramType = "query",dataType = "Integer",required = true),
            @ApiImplicitParam(name = "keyWord",value = "搜索关键字",paramType = "query",dataType = "String",required = true)
    })
    @GetMapping("/algorithms/{userId}")
    public CommonResult getAllAlgorithms(@PathVariable(value = "userId")Integer userId,
                                         @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                                         @RequestParam(value = "pageSize",defaultValue = "6")Integer pageSize,
                                         @RequestParam(value = "keyWord",defaultValue = "")String keyWord){

        return algorithmFeignService.getAllAlgorithms(userId,pageNum,pageSize,keyWord);

    }


    /**
     * 接口 6.1.1.2 根据ID查询算法信息
     * @author Jiahan Wang
     * @create 2020-07-15 15:59
     * @updator Jiahan Wang
     * @upadte 2020-07-15 15:59
     * @param algorithmId
     * @return
     */
    @ApiOperation(value = "接口 6.1.1.2 按ID查询算法信息",httpMethod = "GET",notes = "")
    @ApiImplicitParam(value = "algorithmId",name = "算法ID",paramType = "path",dataType = "Integer",required = true)
    @GetMapping("/algorithm/{algorithmId}")
    public CommonResult getAlgorithmBasicById(@PathVariable(value = "algorithmId")Integer algorithmId){
        return algorithmFeignService.getAlgorithmBasicById(algorithmId);
    }


    /**
     * 接口 6.1.1.3 按ID查询算法训练规范
     * @author Jiahan Wang
     * @create 2020-07-15 16:19
     * @updator Jiahan Wang
     * @upadte 2020-07-15 16:19
     * @param algorithmId
     * @return
     */
    @ApiOperation(value = "接口 6.1.1.3 按ID查询算法训练规范",httpMethod = "GET",notes = "")
    @ApiImplicitParam(value = "algorithmId",name = "算法ID",paramType = "path",dataType = "Integer",required = true)
    @GetMapping("/algorithm/trainStandard/{algorithmId}")
    public CommonResult getAlgorithmTrainStandardById(@PathVariable("algorithmId")Integer algorithmId){
        return algorithmFeignService.getAlgorithmTrainStandardById(algorithmId);
    }


    /**
     * 接口 6.1.1.4 按ID查询算法超参规范
     * @author Jiahan Wang
     * @create 2020-07-15 16:32
     * @updator Jiahan Wang
     * @upadte 2020-07-15 16:32
     * @param algorithmId
     * @return
     */
    @ApiOperation(value = "接口 6.1.1.4 按ID查询算法超参规范",httpMethod = "GET",notes = "")
    @ApiImplicitParam(value = "algorithmId",name = "算法ID",paramType = "path",dataType = "Integer",required = true)
    @GetMapping("/algorithm/hyperPara/{algorithmId}")
    public CommonResult getAlgorithmHyperPara(@PathVariable("algorithmId")Integer algorithmId){
        return  algorithmFeignService.getAlgorithmHyperPara(algorithmId);
    }


    /**
     * 接口 6.1.1.7 编辑算法
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
        return algorithmFeignService.updateAlgorithm(algorithm);
    }

    /**
     * 接口 6.1.1.6 删除算法
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
        return algorithmFeignService.deleteAlgorithmById(id);
    }


    /**
     * 查询算法描述
     * @author Jiahan Wang
     * @create 2020-07-18 14:00
     * @updator Jiahan Wang
     * @update 2020-07-18 14:00
     * @param algorithmId
     * @return
     */
    @GetMapping("/algorithm/description/{algorithmId}")
    public CommonResult getAlgorithmDescription(@PathVariable("algorithmId")Integer algorithmId){
        return algorithmFeignService.getAlgorithmDescription(algorithmId);
    }


}
