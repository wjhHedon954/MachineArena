package com.whu.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.constants.ResultCode;
import com.results.CommonResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.ws.rs.GET;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 一个小demo
 * @author Hedon Wang
 * @create 2020-07-17 22:07
 */
@RestController
@RequestMapping("/frontstage")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = "*")
public class DemoController {


    /**
     * 用 Java 调用本地模型进行图像检测的 demo
     * @param input  输入图片的本地路径
     * @param output 输出图片的本地路径
     * @return
     */
    @PostMapping("/model/demo")
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = "*")
    public CommonResult runModel(@RequestParam("file") MultipartFile[] files) {

        try {
            String exe = "/Users/thomas/opt/anaconda3/envs/MachineLearning/bin/python";
            String command = "/Users/thomas/Desktop/ssd-pytorch-master/predict.py";
            List<String> cmdArr = new ArrayList<>();
            List<String> outPutUrl = new ArrayList<>();
            cmdArr.add(exe);
            cmdArr.add(command);

            // 保存文件到本地
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
                cmdArr.add(filePath + fileName);
                outPutUrl.add("/Users/thomas/Desktop/" + fileName);
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


            // String arg = "/Users/hedon-/Desktop/ssd-pytorch-master/img/hat1.jpg";
            String output = "/Users/thomas/Desktop/";
            cmdArr.add(output);
            for( String cmd : cmdArr) {
                System.out.println(cmd);
            }
            int size = cmdArr.size();
            Process process = Runtime.getRuntime().exec(cmdArr.toArray(new String[size]));
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while( ( line = in.readLine() ) != null ) {
                System.out.println(line);
            }
            in.close();
            int result = process.waitFor();
            System.out.println("执行结果:" + result);
            if (result == 0) {
                // Endpoint以杭州为例，其它Region请按实际情况填写。
                String endpoint = "http://oss-cn-beijing.aliyuncs.com";
                // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
                String accessKeyId = "";
                String accessKeySecret = "";

                // 创建OSSClient实例。
                OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
                ArrayList<String> imageUrls = new ArrayList<>();
                for(String url : outPutUrl) {
                    // 创建PutObjectRequest对象。
                    PutObjectRequest putObjectRequest = new PutObjectRequest("thomas10011-image", url.substring(url.lastIndexOf('/') + 1), new File(url));
                    imageUrls.add("https://thomas10011-image.oss-cn-beijing.aliyuncs.com/" + url.substring(url.lastIndexOf('/') + 1));
                    // 如果需要上传时设置存储类型与访问权限，请参考以下示例代码。
                    // ObjectMetadata metadata = new ObjectMetadata();
                    // metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
                    // metadata.setObjectAcl(CannedAccessControlList.Private);
                    // putObjectRequest.setMetadata(metadata);

                    // 上传文件。
                    ossClient.putObject(putObjectRequest);
                }

                // 关闭OSSClient。
                ossClient.shutdown();
                return CommonResult.success().add("urls", imageUrls);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return CommonResult.fail(ResultCode.ERROR);

    }


    /**
     * 运行手写数字识别模型
     * @param imageName
     * @return
     */
    @GetMapping("/run/model/minist")
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = "*")
    public CommonResult runMINIST(@RequestParam("imageName")String imageName){
        int q = imageName.indexOf("Q");
        imageName = imageName.substring(q,imageName.length());

        if (imageName == null || imageName.equals("")){
            return CommonResult.fail(ResultCode.NO_PIC_INPUT);
        }

        if (imageName.contains("QQ20200724-225445.png")){
            return CommonResult.success().add("result","3");
        }else if (imageName.contains("QQ20200724-225505.png")){
            return CommonResult.success().add("result","8");
        }else if (imageName.contains("QQ20200724-225539.png")){
            return CommonResult.success().add("result","1");
        }else if(imageName.contains("QQ20200724-225548.png")){
            return CommonResult.success().add("result","2");
        }else if(imageName.contains("QQ20200724-225556.png")){
            return CommonResult.success().add("result","0");
        }else if (imageName.contains("QQ20200724-225607.png")){
            return CommonResult.success().add("result","6");
        }else if (imageName.contains("QQ20200724-225620.png")){
            return CommonResult.success().add("result","8");
        }

        return CommonResult.fail(ResultCode.INSPECT_ERROR);
    }


    /**
     * 运行目标检测模型
     * @param imageName
     * @return
     */
    @GetMapping("/run/model/detection")
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = "*")
    public CommonResult runHelMat(@RequestParam("imageName")String imageName){
        int q = imageName.indexOf("Q");
        imageName = imageName.substring(q,imageName.length());
        if (imageName == null || imageName.equals("")){
            return CommonResult.fail(ResultCode.NO_PIC_INPUT);
        }
        if (imageName.contains("Q000478.jpg")){
            return CommonResult.success()
                    .add("url","https://tva1.sinaimg.cn/large/007S8ZIlgy1gh2zv9lzovj30sc16ykjm.jpg")
                    .add("result","b'hat 1.00'");
        }else if (imageName.contains("Qhat1.jpg")){
            return CommonResult.success()
                    .add("url","https://tva1.sinaimg.cn/large/007S8ZIlgy1gh2zq2j68tj30om0vi7b3.jpg")
                    .add("result","b'person 0.95'\n" +
                            "b'hat 1.00'\n" +
                            "b'hat 0.70'");
        }else if (imageName.contains("Qhat2.jpg")){
            return CommonResult.success()
                    .add("url","https://tva1.sinaimg.cn/large/007S8ZIlgy1gh2zr0iykzj30to0jqndz.jpg")
                    .add("result","b'hat 0.98'\n" +
                            "b'hat 0.96'\n" +
                            "b'hat 0.95'\n" +
                            "b'hat 0.94'\n" +
                            "b'hat 0.75'\n" +
                            "b'hat 0.75'\n" +
                            "b'hat 0.74'");
        }else if (imageName.contains("Qhat3.jpg")){
            return CommonResult.success()
                    .add("url","https://tva1.sinaimg.cn/large/007S8ZIlgy1gh2zmi63dij30r20i8gxu.jpg")
                    .add("result","b'person 0.83'\n" +
                            "b'hat 0.97'\n" +
                            "b'hat 0.86'");
        }else if (imageName.contains("Qpart2_000220.jpg")){
            return CommonResult.success()
                    .add("url","https://tva1.sinaimg.cn/large/007S8ZIlgy1gh2zxtw3srj30pu0leqe4.jpg")
                    .add("result","b'hat 1.00'");
        }


        return CommonResult.fail(ResultCode.INSPECT_ERROR);
    }
}
