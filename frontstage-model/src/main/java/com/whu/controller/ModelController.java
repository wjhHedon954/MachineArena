package com.whu.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.entity.Model;
import com.results.CommonResult;
import com.whu.service.ModelFeignService;
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
     * @param model
     * @return
     */
    @PostMapping("/model")
    CommonResult importModel(HttpServletRequest request){
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
    CommonResult updateModelById(@RequestBody Model model){
        return service.updateModelById(model);
    }
}
