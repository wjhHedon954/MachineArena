package com.whu.controller;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.entity.Model;
import com.entity.ModelDescription;
import com.results.CommonResult;
import com.whu.service.ModelFeignService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@Controller
@RequestMapping("/frontstage")
@RestController
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = "*")
public class ModelController {
    @Autowired
    private ModelFeignService modelFeignService;

    /**
     * 接口6.3.1.1  导入模型
     * @description 导入模型
     * @author Yi Zheng
     * @create 2020-7-21 13:00
     * @updator
     * @update
     * @return
     */
    @PostMapping("/model")
    public CommonResult importModel(HttpServletRequest request) throws IOException {
        Model model = new Model();
        ModelDescription modelDescription = new ModelDescription();
        MultipartFile modelImage = ((MultipartHttpServletRequest)request).getFile("modelImage");
        MultipartFile modelFile = ((MultipartHttpServletRequest)request).getFile("modelFile");
        MultipartHttpServletRequest params =  (MultipartHttpServletRequest)request;

        // 从传入的数据中获取data并转换为JSONObject 顺便获取超参数
        JSONObject data = null;
        try {
            data =  new JSONObject(params.getParameter("data"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        assert data != null;
        model.setModelName(data.get("modelName").toString());
        model.setModelTypeId((Integer) data.get("modelTypeId"));
        model.setModelIsSuccessful((Integer)data.get("modelIsSuccessful"));
        model.setModelStatus(0);
        model.setModelApi("");
        model.setModelPhotoUrl("");
        model.setModelUrl("");
        CommonResult result = modelFeignService.importModel(model);
        model.setModelId((Integer) JSONUtil.parseObj(result.getExtend()).get("modelId"));   // 保存到数据库里以后设置id

        modelDescription.setModelId(model.getModelId());
        modelDescription.setModelDescriptionContent(data.get("modelDescription").toString());
//        modelDescription.setModelDescriptionId(1);     // 给个id 否则会报错
        modelFeignService.addModelDescription(modelDescription);        // 保存模型描述

        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "http://oss-cn-beijing.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = "";
        String accessKeySecret = "";

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        ArrayList<String> imageUrls = new ArrayList<>();

        byte[] content;
        String fileName;
        assert modelImage != null;
        // 上传文件。

        content = modelImage.getBytes();
        fileName = "model-image/" + model.getModelId().toString() + "-" + modelImage.getOriginalFilename();
        ossClient.putObject("thomas10011-image", fileName, new ByteArrayInputStream(content));
        model.setModelPhotoUrl("https://thomas10011-image.oss-cn-beijing.aliyuncs.com/" + fileName);        // 保存图片url

        content = modelFile.getBytes();
        fileName = "model-file/" + model.getModelId().toString() + "-" + modelFile.getOriginalFilename();
        ossClient.putObject("thomas10011-image", fileName, new ByteArrayInputStream(content));
        model.setModelUrl("https://thomas10011-image.oss-cn-beijing.aliyuncs.com/" + fileName);             // 保存文件url


        // 关闭OSSClient。
        ossClient.shutdown();

        // 更新model并返回
        return modelFeignService.updateModelById(model);
    }


    /**
     * 接口6.3.1.2  根据id查询模型
     * @description 根据id查询模型
     * @author Yi Zheng
     * @create 2020-7-21 13:10
     * @updator
     * @update
     * @param id
     * @return
     */
    @GetMapping("/model/{id}")
    CommonResult selectModelById(@PathVariable("id") Integer id){
        return modelFeignService.selectModelById(id);
    }


    /**
     * 接口6.3.1.23 根据id删除模型
     * @description 根据id删除模型
     * @author Yi Zheng
     * @create 2020-7-21 13:10
     * @updator
     * @update
     * @param id
     * @return
     */
    @DeleteMapping("/model/{id}")
    public CommonResult deleteModelById(@PathVariable("id") Integer id){
        return modelFeignService.deleteModelById(id);
    }

    /**
     * 接口6.3.1.23 根据id更改模型
     * @description 根据id更改模型
     * @author Yi Zheng
     * @create 2020-7-21 13:10
     * @updator
     * @update
     * @param model  需要更改的墨香
     * @return int 更改印象的行数
     */
    @PutMapping("/model/")
    public CommonResult updateModelById(@RequestBody Model model){
        return modelFeignService.updateModelById(model);
    }


    /**
     * 6.3.1.5 查询所有模型
     * @author Jiahan Wang
     * @create 2020-7-22 23:15
     * @updator
     * @update
     * @param pageNum 页码
     * @param pageSize 页面大小
     * @param keyWord 关键字
     * @return
     */
    @ApiOperation(value = "6.3.1.5 查询所有模型",httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "页码",paramType = "query",dataType = "Integer",required = true),
            @ApiImplicitParam(name = "pageSize",value = "页面大小",paramType = "query",dataType = "Integer",required = true),
            @ApiImplicitParam(name = "keyWord",value = "搜索关键字",paramType = "query",dataType = "String",required = true)
    })
    @GetMapping("/models")
    public CommonResult getUserModel(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                                     @RequestParam(value = "pageSize",defaultValue = "6")Integer pageSize,
                                     @RequestParam(value = "keyWord",defaultValue = "")String keyWord){
        return modelFeignService.getModels(pageNum,pageSize,keyWord);
    }


    /**
     * 6.3.1.8 查询用户下的所有模型
     * @author Jiahan Wang
     * @create 2020-7-22 22:15
     * @updator
     * @update
     * @param userId  用户ID
     * @param pageNum 页码
     * @param pageSize 页面大小
     * @param keyWord 关键字
     * @return
     */
    @ApiOperation(value = "6.3.1.8 查询用户下的所有模型",httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户ID",paramType = "path",dataType = "Integer",required = true),
            @ApiImplicitParam(name = "pageNum",value = "页码",paramType = "query",dataType = "Integer",required = true),
            @ApiImplicitParam(name = "pageSize",value = "页面大小",paramType = "query",dataType = "Integer",required = true),
            @ApiImplicitParam(name = "keyWord",value = "搜索关键字",paramType = "query",dataType = "String",required = true)
    })
    @GetMapping("/models/{userId}")
    public CommonResult getUserModel(@PathVariable("userId")Integer userId,
                                     @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                                     @RequestParam(value = "pageSize",defaultValue = "6")Integer pageSize,
                                     @RequestParam(value = "keyWord",defaultValue = "")String keyWord){

        return modelFeignService.getUserModels(userId,pageNum,pageSize,keyWord);
    }
}
