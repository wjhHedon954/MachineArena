package com.whu.controller;

import com.constants.ResultCode;
import com.results.CommonResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 一个小demo
 * @author Hedon Wang
 * @create 2020-07-17 22:07
 */
@RestController
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
            if (result == 0){
                return CommonResult.success().add("urls", outPutUrl);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return CommonResult.fail(ResultCode.ERROR);

    }

}
