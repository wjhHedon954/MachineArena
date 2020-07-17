package com.whu.controller;

import com.constants.ResultCode;
import com.results.CommonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
    @GetMapping("/model/demo")
    public CommonResult runModel(@RequestParam("input") String input,
                                 @RequestParam("output") String output){

        try {
            String exe = "/usr/local/Cellar/python/3.7.6_1/bin/python3";
            String command = "/Users/hedon-/Desktop/ssd-pytorch-master/predict.py";
            // String arg = "/Users/hedon-/Desktop/ssd-pytorch-master/img/hat1.jpg";
            String inputArg = input;
            //String arg2 = "/Users/hedon-/Desktop/";
            String outputArg = output;
            String[] cmdArr = new String[] { exe, command ,inputArg,outputArg};
            Process process = Runtime.getRuntime().exec(cmdArr);
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while( ( line = in.readLine() ) != null ) {
                System.out.println(line);
            }
            in.close();
            int result = process.waitFor();
            System.out.println("执行结果:" + result);
            if (result == 0){
                return CommonResult.success();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return CommonResult.fail(ResultCode.ERROR);

    }

}
