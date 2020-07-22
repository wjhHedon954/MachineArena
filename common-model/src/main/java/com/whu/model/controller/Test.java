package com.whu.model.controller;

import com.constants.ResultCode;
import com.responsevo.TrainStartVO;
import com.results.CommonResult;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.time.LocalDateTime;
import java.time.LocalTime;

@RestController
public class Test {
    //模拟研发端创建容器
    @PostMapping("/teststart")
    public CommonResult testStartTrain(@RequestBody TrainStartVO vo){
        if (vo==null)
            return CommonResult.fail(ResultCode.EMPTY_PARAM);
        return CommonResult.success().add("startContainer","be4ad49404e10b39441ecf4f2e9e6aed69d6e54666213db30819cd4c28c61d1f");
    }
    //模拟研发端删除容器
    @DeleteMapping("/testdelete/{id}")
    public CommonResult testDelete(@PathVariable("id") Integer id){

        return CommonResult.success().add("id",id);
    }

    @GetMapping("/testMkdir")
    public CommonResult testMkdir(@RequestParam("path") String path){
        try {
            File file=new File(path);
            file.mkdirs();
        }catch (Exception e){
            return CommonResult.fail(ResultCode.INSERT_ERROR);
        }
        return CommonResult.success();
    }
//    public static void main(String[] args) {
//        LocalDateTime now = LocalDateTime.now();
//        System.out.println(now);
//    }
}
