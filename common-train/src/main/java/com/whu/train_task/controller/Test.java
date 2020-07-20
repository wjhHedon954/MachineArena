package com.whu.train_task.controller;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.constants.ResultCode;
import com.entity.TaskIpContainer;
import com.responsevo.TrainStartVO;
import com.results.CommonResult;
import com.whu.train_task.service.impl.TrainTaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {
    @Autowired
    private TrainTaskServiceImpl trainTaskService;


    @PostMapping("/start")
    public CommonResult startTrainTask(@RequestBody TrainStartVO vo){
        //检查前端返回的数据是否为空
        if (vo==null)
            return CommonResult.fail(ResultCode.EMPTY_PARAM);
        //检查前端核心数据是否为空
        if (vo.getTrainTaskId()==null || vo.getTrainTaskAlgorithmId()==null)
            return CommonResult.fail(ResultCode.NO_TrainTaskId_OR_TaskAlgorithmId);

        //将从前端获取的数据转换成json格式字符串
        JSONObject json = JSONUtil.parseObj(vo, false);
        String s = json.toStringPretty();
        //向研发发请求，传递数据并等待返回数据
        String result=null;
        try {
            result = HttpRequest.post("http://localhost:30001/teststart")
                    .timeout(10000)
                    .body(s)
                    .execute().body();
        }catch (Exception e){
            e.printStackTrace();
            return CommonResult.fail(ResultCode.FAIL_TO_SEND_REQUEST);
        }
        //检查返回数据是否为空
        if (result==null)
            return CommonResult.fail(ResultCode.NO_RESPONSE_DATA);
        //System.out.println(result);
        //JSON解析获取容器ID
        JSON resultParse = JSONUtil.parse(result);
        String extend = resultParse.getByPath("extend",String.class);
        JSON extendParse = JSONUtil.parse(extend);
        String containerId=extendParse.getByPath("startContainer",String.class);
        //判断容器id是否为空
        if(containerId==null)
            return CommonResult.fail(ResultCode.FAILE_PARSE_JSON);
        //创建TaskIpContainer对象并且设值
        Integer trainTaskId=vo.getTrainTaskId();
        TaskIpContainer ipContainer=new TaskIpContainer();
        ipContainer.setTaskIpContainerId(5);
        ipContainer.setContainerId(containerId);
        ipContainer.setTrainTaskId(trainTaskId);
        //执行insert操作
        int i = trainTaskService.addTaskIpContainer(ipContainer);
        //判断insert操作是否成功
        if (i==0)
            return CommonResult.fail(ResultCode.INSERT_ERROR);

        return CommonResult.success();
    }
    
//    public static void main(String[] args) {
//        TrainStartVO vo=new TrainStartVO ();
//        vo.setTrainTaskAlgorithmId(4);
//        vo.setTrainTaskId(3);
//        vo.setTrainTaskParams("10");
//        vo.setTrainTaskSpecification("4");
//
//        JSONObject json = JSONUtil.parseObj(vo, false);
//        String s = json.toStringPretty();
//        //向研发发请求，传递数据并等待返回数据
//        String result=null;
//        try {
//            result = HttpRequest.post("http://47.113.97.26/")
//                    .body(s)
//                    .execute().body();
//        }catch (Exception e){
//            e.printStackTrace();
//            return CommonResult.fail(ResultCode.EMPTY_OBJECT);
//        }
//    }
}
