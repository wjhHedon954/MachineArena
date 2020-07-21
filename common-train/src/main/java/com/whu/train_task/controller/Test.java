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
import org.springframework.web.bind.annotation.*;

@RestController
public class Test {
    @Autowired
    private TrainTaskServiceImpl trainTaskService;

    //模拟测试后端创建容器
    @PostMapping("/teststart")
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
        System.out.println(result);
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

    //模拟后端删除容器
    @DeleteMapping("/testdelete/{trainTaskID}")
    public CommonResult deleteTaskIpContainerById(@PathVariable("trainTaskID") Integer trainTaskID){
        //检查ID是否为空
        if (trainTaskID == null) {
            return CommonResult.fail(ResultCode.EMPTY_PARAM);
        }
        //执行删除操作
        try {
            int j = trainTaskService.deleteTaskIpContainerByTrainTaskId(trainTaskID);
            if (j==0)
                return CommonResult.fail(ResultCode.DELETE_ERROR);
        } catch (Exception e) {
            return CommonResult.fail(ResultCode.DELETE_ERROR);
        }
        //向研发发送删除请求
        String result=null;
        try{
            result=HttpRequest.delete("http://localhost:30001/testdelete/"+trainTaskID)
                    .timeout(100000)
                    .execute().body();
        }catch (Exception e){
            return CommonResult.fail(ResultCode.FAIL_TO_SEND_REQUEST);
        }
        System.out.println(result);
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
//        String s = json.toString();
//        System.out.println(s);
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



//    public static void main(String[] args) {
//        String result=null;
//        try {
//            result = HttpRequest.post("http://202.114.66.76:8081/container")
//                    .timeout(10000)
//                    .body("{\n" +
//                            "  \"trainTaskAlgorithmId\": 4,\n" +
//                            "  \"trainTaskId\": 3,\n" +
//                            "  \"trainTaskParams\": \"10\",\n" +
//                            "  \"trainTaskSpecification\": \"4\"\n" +
//                            "}")
//                    .execute().body();
//        }catch (Exception e){
//            e.printStackTrace();
//            System.out.println("post请求出错");
//        }
//        System.out.println(result);
//    }
}
