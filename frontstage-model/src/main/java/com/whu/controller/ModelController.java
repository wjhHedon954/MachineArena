package com.whu.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.entity.Model;
import com.results.CommonResult;
import com.whu.service.ModelFeignService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;

@Controller
@RequestMapping("/frontstage")
@RestController
public class ModelController {
    @Autowired
    private ModelFeignService service;

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
    public CommonResult importModel(HttpServletRequest request){
        Model model = new Model();
        MultipartFile modelImage = ((MultipartHttpServletRequest)request).getFile("modelImage");
        MultipartFile modelFile = ((MultipartHttpServletRequest)request).getFile("modelFile");

        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "http://oss-cn-beijing.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = "LTAI4G9iw6Da8c5Px6qDGWNA";
        String accessKeySecret = "GTorRGPG8BrG7hN7UGMCP9XV51q9IK";

        // 创建OSSClient实例。
//        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
//        ArrayList<String> imageUrls = new ArrayList<>();
//        for(String url : outPutUrl) {
//            // 创建PutObjectRequest对象。
//            PutObjectRequest putObjectRequest = new PutObjectRequest("thomas10011-image", url.substring(url.lastIndexOf('/') + 1), new File(url));
//            imageUrls.add("https://thomas10011-image.oss-cn-beijing.aliyuncs.com/" + url.substring(url.lastIndexOf('/') + 1));
//            // 如果需要上传时设置存储类型与访问权限，请参考以下示例代码。
//            // ObjectMetadata metadata = new ObjectMetadata();
//            // metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
//            // metadata.setObjectAcl(CannedAccessControlList.Private);
//            // putObjectRequest.setMetadata(metadata);
//
//            // 上传文件。
//            ossClient.putObject(putObjectRequest);
//        }
//
//        // 关闭OSSClient。
//        ossClient.shutdown();


        return CommonResult.success();


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
        return service.selectModelById(id);
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
        return service.deleteModelById(id);
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
        return service.updateModelById(model);
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
        return service.getModels(pageNum,pageSize,keyWord);
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

        return service.getUserModels(userId,pageNum,pageSize,keyWord);
    }
}
