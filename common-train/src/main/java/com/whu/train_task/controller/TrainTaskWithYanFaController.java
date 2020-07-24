package com.whu.train_task.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.constants.ResultCode;
import com.entity.TaskIpContainer;
import com.entity.TrainTask;
import com.entity.TrainTaskConf;
import com.google.gson.internal.$Gson$Preconditions;
import com.responsevo.ContainerStatusVo;
import com.responsevo.TrainProcessStatusVO;
import com.responsevo.TrainStartVO;
import com.responsevo.TrainTaskAndTrainTaskConfig;
import com.results.CommonResult;
import com.whu.train_task.service.impl.TrainTaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = "*")
public class TrainTaskWithYanFaController {
    @Autowired
    private TrainTaskServiceImpl trainTaskService;

    /**
     * 接口 6.2.1.10 接收前端数据返回给研发，再从研发获取数据存入数据库
     * @author Yi Zheng
     * @create 2020-07-19 00:25
     * @updator Yi Zheng
     * @upadte 2020-07-19 19:20
     * @param
     * @return
     */
    @GetMapping("/trainTask/start/{trainTaskId}")
    public CommonResult startTrainTask(@PathVariable("trainTaskId") Integer trainTaskId){
        //检查前端返回的数据是否为空
        if (trainTaskId==null)
            return CommonResult.fail(ResultCode.EMPTY_PARAM);

        //获取包装类
        TrainTaskAndTrainTaskConfig trainTaskFullInfo = trainTaskService.getTrainTaskFullInfoById(trainTaskId);
        if (trainTaskFullInfo==null)
            return CommonResult.fail(ResultCode.SELECT_CONTAINER_STATUS);

        //由包装类获取TrainTask和TrainTaskConf
        TrainTask trainTask = trainTaskFullInfo.getTrainTask();
        TrainTaskConf trainTaskConf = trainTaskFullInfo.getTrainTaskConf();
        if (trainTask==null || trainTaskConf==null)
            return CommonResult.fail(ResultCode.SELECT_CONTAINER_STATUS);

        //设置状态为开始训练,修改数据库
        trainTaskConf.setTrainTaskStatus(1);
        int trainTaskConfUpdate = trainTaskService.updateTrainTaskConfById(trainTaskConf);
        if (trainTaskConfUpdate==0)
            return CommonResult.fail(ResultCode.UPDATE_ERROR);

        //获取研发需要的属性
        Integer trainTaskUserId = trainTask.getTrainTaskUserId();
        Integer trainTaskAlgorithmId = trainTaskConf.getTrainTaskAlgorithmId();
        String trainTaskParams = trainTaskConf.getTrainTaskParams();
        String trainTaskSpecification = trainTaskConf.getTrainTaskSpecification();


        //新建一个研发需要的包装类
        TrainStartVO vo = new TrainStartVO();

        //设置属性
        vo.setTrainTaskParams(trainTaskParams);
        vo.setTrainTaskAlgorithmId(trainTaskAlgorithmId);
        vo.setTrainTaskId(trainTaskId);
        vo.setTrainTaskSpecification(trainTaskSpecification);
        vo.setTrainTaskUserId(trainTaskUserId);

        //将从前端获取的数据转换成json格式字符串
        JSONObject json = JSONUtil.parseObj(vo, false);
        String s = json.toString();
        //向研发发请求，传递数据并等待返回数据
        String result=null;
        try {
            result = HttpRequest.post("http://10.10.10.209:7777/container")
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
        //JSON解析获取容器ID
        JSON resultParse = JSONUtil.parse(result);
        String extend = resultParse.getByPath("extend",String.class);
        JSON extendParse = JSONUtil.parse(extend);
        String containerId=extendParse.getByPath("startContainer",String.class);
        //判断容器id是否为空
        if(containerId==null)
            return CommonResult.fail(ResultCode.FAILE_PARSE_JSON);
        //创建TaskIpContainer对象并且设值
        TaskIpContainer ipContainer=new TaskIpContainer();
        ipContainer.setContainerId(containerId);
        ipContainer.setTrainTaskId(trainTaskId);
        //执行insert操作
        int i = trainTaskService.addTaskIpContainer(ipContainer);
        //判断insert操作是否成功
        if (i==0)
            return CommonResult.fail(ResultCode.INSERT_ERROR);

        return CommonResult.success();
    }





    /**
     * 接口 6.2.1.9 根据ID删除训练有关的镜像
     * @author Yi Zheng
     * @create 2020-07-19 13:50
     * @updator Yi Zheng
     * @update
     * @param trainTaskID 删除的ID
     * @return  返回通用数据
     */
    @DeleteMapping("/trainTask/container/{trainTaskID}")
    public CommonResult deleteTaskIpContainerById(@PathVariable("trainTaskID") Integer trainTaskID){
        //检查ID是否为空
        if (trainTaskID == null) {
            return CommonResult.fail(ResultCode.EMPTY_PARAM);
        }

        //向研发发送删除请求
        try{
            HttpRequest.get("http://10.10.10.209:7777/container/"+trainTaskID)
                    .timeout(100000)
                    .execute().body();
        }catch (Exception e){
            return CommonResult.fail(ResultCode.FAIL_TO_SEND_REQUEST);
        }

        //执行删除操作
        try {
            int j = trainTaskService.deleteTaskIpContainerByTrainTaskId(trainTaskID);
            if (j==0)
                return CommonResult.fail(ResultCode.DELETE_ERROR);
        } catch (Exception e) {
            return CommonResult.fail(ResultCode.DELETE_ERROR);
        }
        return CommonResult.success();
    }

    //未与后台对接，还未进行详尽测试
    /**
     * 接口 6.2.1.11 接收前端返回的训练作业id发送给研发，从研发获取容器详细信息发送给前端
     * @author Yi Zheng
     * @create 2020-07-19 02:31
     * @updator Yi Zheng
     * @upadte
     * @param id  训练作业id
     * @return CommonResult  通用返回结果
     */
    @GetMapping("/trainTask/info/{id}")
    CommonResult showContainerInfo(@PathVariable("id") Integer id){
        //判断参数是否为空
        if (id == null){
            return CommonResult.fail(ResultCode.EMPTY_PARAM);
        }
        //向研发发请求，传递id并等待返回数据
        String result=null;
        try {
            result = HttpRequest.get("http://10.10.10.209:7777/container/info/"+id)
                    .timeout(100000)
                    .execute().body();
        }catch (Exception e){
            return CommonResult.fail(ResultCode.FAIL_TO_SEND_REQUEST);
        }


        //检查返回结果是不是为空
        if (result==null)
            return CommonResult.fail(ResultCode.NO_RESPONSE_DATA);

        //打印下研发返回了啥
        System.out.println("研发返回的数据: "+result);

        //JSON解析获取容器详细信息
        JSONObject containerInfoObject=null;
        try {
            containerInfoObject = JSONUtil.parseObj(result).getJSONObject("extend").getJSONObject("containerInfo");
        }catch (Exception e){
            return CommonResult.fail(ResultCode.FAIL_TO_PARSE_JSON);
        }

        return CommonResult.success().add("containerInfo",containerInfoObject);
    }


    /**
     * 接口 6.2.1.12 接收前端返回的训练作业id发送给研发，再从研发获取容器详细日志发送给前端
     * @author Yi Zheng
     * @create 2020-07-19 02:40
     * @updator Yi Zheng
     * @upadte  2020-7-22 10:30
     * @param id  训练作业id
     * @return CommonResult  通用返回结果
     */
    @GetMapping("/trainTask/logs/{id}")
    CommonResult showContainerLogs(@PathVariable("id") Integer id){
        //判断参数是否为空
        if (id == null){
            return CommonResult.fail(ResultCode.EMPTY_PARAM);
        }
        //向研发发请求，传递id并等待返回数据
        String result=null;
        try {
            result = HttpRequest.get("http://10.10.10.209:7777/container/logs/"+id)
                    .timeout(100000)
                    .execute().body();
        }catch (Exception e){
            return CommonResult.fail(ResultCode.FAIL_TO_SEND_REQUEST);
        }
        //检查返回结果是不是为空
        if (result==null)
            return CommonResult.fail(ResultCode.NO_RESPONSE_DATA);

        //打印研发返回了啥
        System.out.println("研发返回的内容:  "+result);

        //JSON解析获取详细日志信息
        String extendContent=null;
        String logContent=null;
        try {
            extendContent= JSONUtil.parseObj(result).getByPath("extend",String.class);
            logContent = JSONUtil.parseObj(extendContent).get("log", String.class);
        }catch (Exception e){
            return CommonResult.fail(ResultCode.FAIL_TO_PARSE_JSON);
        }
        return CommonResult.success().add("logs",logContent);
    }


    //数据库操作已测试正常，但未与后台对接，未进行对接详尽测试。
    /**
     * 接口 6.2.1.13 接收前端返回的训练作业id发送给研发，再从研发获取服务器运行状态发送给前端
     * @author Yi Zheng
     * @create 2020-07-20 11:40
     * @updator Yi Zheng
     * @upadte  2020-7-22 10:20
     * @param id  训练作业id
     * @return CommonResult  通用返回结果
     */
    @GetMapping("/trainTask/workerStatus/{id}")
    CommonResult showWorkStatus(@PathVariable("id") Integer id){
        //判断参数是否为空
        if (id == null){
            return CommonResult.fail(ResultCode.EMPTY_PARAM);
        }


        //向研发发请求，传递id并等待返回数据
        String result=null;
        try {
            result = HttpRequest.get("http://10.10.10.209:7777/worker/"+id)
                    .timeout(100000)
                    .execute().body();
        }catch (Exception e){
            return CommonResult.fail(ResultCode.FAIL_TO_SEND_REQUEST);
        }


        //检查返回结果是不是为空
        if (result==null)
            return CommonResult.fail(ResultCode.NO_RESPONSE_DATA);
        //JSON解析获取详细日志信息
        JSONObject memoryObject=null;
        JSONArray GPUsArray=null;
        try {
            memoryObject = JSONUtil.parseObj(result).getJSONObject("extend").getJSONObject("serverInfo").getJSONObject("memory");
            GPUsArray = JSONUtil.parseObj(result).getJSONObject("extend").getJSONObject("serverInfo").getJSONArray("GPUs");
        }catch (Exception e){
            return CommonResult.fail(ResultCode.FAIL_TO_PARSE_JSON);
        }

        return CommonResult.success().add("memory",memoryObject).add("GPUS",GPUsArray);
    }




    /**
     * 接口 6.2.1.14 接收前端返回的训练作业id,数据库查询数据封装传给研发，研发返回信息发给前端
     * @author Yi Zheng
     * @create 2020-07-22 10:20
     * @updator Yi Zheng
     * @upadte
     * @param trainTaskId  训练作业id
     * @return CommonResult  通用返回结果
     */
    @GetMapping("/trainTask/container/status/{trainTaskId}")
    public CommonResult showContainerStatus(@PathVariable("trainTaskId") Integer trainTaskId){
        if (trainTaskId==null)
            return CommonResult.fail(ResultCode.EMPTY_PARAM);

        //获取TrainTask和TrainTaskConfig的包装类
        TrainTaskAndTrainTaskConfig trainTaskAndTrainTaskConfig = trainTaskService.getTrainTaskFullInfoById(trainTaskId);
        System.out.println(trainTaskAndTrainTaskConfig);
        if (trainTaskAndTrainTaskConfig==null)
            return CommonResult.fail(ResultCode.SELECT_CONTAINER_STATUS);

        //从包装类获取TrainTask
        TrainTask trainTask = trainTaskAndTrainTaskConfig.getTrainTask();
        System.out.println(trainTask);
        if (trainTask==null)
            return CommonResult.fail(ResultCode.SELECT_CONTAINER_STATUS);

        //从trainTask获取user_id》
        Integer trainTaskUserId = trainTask.getTrainTaskUserId();
        System.out.println(trainTaskUserId);
        if (trainTaskUserId==null)
            return CommonResult.fail(ResultCode.SELECT_CONTAINER_STATUS);

        //从包装类获取TrainTaskConf
        TrainTaskConf trainTaskConf = trainTaskAndTrainTaskConfig.getTrainTaskConf();
        System.out.println(trainTaskConf);
        if (trainTaskConf==null)
            return CommonResult.fail(ResultCode.SELECT_CONTAINER_STATUS);

        //从TrainTaskConf获取算法id
        Integer trainTaskAlgorithmId = trainTaskConf.getTrainTaskAlgorithmId();
        System.out.println(trainTaskAlgorithmId);
        if (trainTaskAlgorithmId==null)
            return CommonResult.fail(ResultCode.SELECT_CONTAINER_STATUS);

        //从TrainTaskConf获取日志输出路径
        String trainTaskLogOutPath = trainTaskConf.getTrainTaskLogOutPath();
        System.out.println(trainTaskLogOutPath);
        if (trainTaskLogOutPath==null)
            return CommonResult.fail(ResultCode.SELECT_CONTAINER_STATUS);

        //从TrainTaskConf获取模型输出路径
        String trainTaskModelOutPath = trainTaskConf.getTrainTaskModelOutPath();
        System.out.println(trainTaskModelOutPath);
        if (trainTaskModelOutPath==null)
            return CommonResult.fail(ResultCode.SELECT_CONTAINER_STATUS);


        //根据训练任务id获取TaskIpContainer
        TaskIpContainer taskIpContainer = trainTaskService.selectTaskIpContainerByTrainTaskId(trainTaskId);
        System.out.println(taskIpContainer);
        if (taskIpContainer==null)
            return CommonResult.fail(ResultCode.SELECT_CONTAINER_STATUS);

        //从taskIpContainer获取容器id
        String containerId = taskIpContainer.getContainerId();
        System.out.println(taskIpContainer);
        if (containerId==null)
            return CommonResult.fail(ResultCode.SELECT_CONTAINER_STATUS);

        //创建一个新的研发需求的返回类型包装类
        ContainerStatusVo containerStatusVo = new ContainerStatusVo();

        //设置包装类属性
        containerStatusVo.setTrainTaskUserId(trainTaskUserId);
        containerStatusVo.setTrainTaskLogOutPath(trainTaskLogOutPath);
        containerStatusVo.setTrainTaskModelOutPath(trainTaskModelOutPath);
        containerStatusVo.setContainerId(containerId);
        containerStatusVo.setTrainTaskAlgorithmId(trainTaskAlgorithmId);
        containerStatusVo.setTrainTaskId(trainTaskId);

        //把包装类转换成json字符串
        JSONObject jsonObject = JSONUtil.parseObj(containerStatusVo,false);
        String json=jsonObject.toString();
        System.out.println(json);

        //定义一个变量接收从研发返回的数据
        String dataFromYanFa =null;

        //向研发发送请求
        try{
            dataFromYanFa=HttpRequest.post("http://10.10.10.209:7777/container/status")
                    .body(json)
                    .timeout(10000)
                    .execute().body();
        }catch (Exception e){
            e.printStackTrace();
            return CommonResult.fail(ResultCode.FAIL_TO_SEND_REQUEST);
        }

        //判断研发的请求是否为空
        if (dataFromYanFa==null)
            return CommonResult.fail(ResultCode.NO_RESPONSE_DATA);

        //打印研发到底返回了啥
        System.out.println(dataFromYanFa);

        //提取研发里面的有用信息
        String extendContent=null;
        String messageContent=null;
        try{
            extendContent = JSONUtil.parse(dataFromYanFa).getByPath("extend", String.class);
            messageContent = JSONUtil.parse(extendContent).getByPath("Message", String.class);
        }catch (Exception e){
            return CommonResult.fail(ResultCode.FAIL_TO_PARSE_JSON);
        }


        //根据message内容修改训练任务的状态值
        //判断是否训练完成
        if (messageContent.indexOf("运行完成")!=-1){
            trainTaskConf.setTrainTaskStatus(2);
            int trainTaskConfUpdate = trainTaskService.updateTrainTaskConfById(trainTaskConf);
            if (trainTaskConfUpdate==0)
                return CommonResult.fail(ResultCode.UPDATE_ERROR);
        }

        return CommonResult.success().add("Message",messageContent);
    }


    /**
     * 接口 6.2.1.15 接收前端返回的训练作业id,从研发获取日志信息进行字符串处理，处理后根据结果返给前端不同的数据
     * @author Yi Zheng
     * @create 2020-07-23 16:00
     * @updator Yi Zheng
     * @upadte
     * @param trainTaskId  训练作业id
     * @return CommonResult  通用返回结果
     */
    @GetMapping("/trainTask/processdata/{trainTaskId}")
    CommonResult showTrainTaskProcessdata(@PathVariable("trainTaskId") Integer trainTaskId){
        //判断参数是否为空
        if (trainTaskId==null)
            return CommonResult.fail(ResultCode.EMPTY_PARAM);


        //向研发发送请求
        String dataFromYanFa=null;
        try{
            dataFromYanFa=HttpRequest.get("http://10.10.10.209:7777/container/logs/short/"+trainTaskId)
                    .timeout(10000)
                    .execute().body();
        }catch (Exception e){
            e.printStackTrace();
            return CommonResult.fail(ResultCode.FAIL_TO_SEND_REQUEST);
        }

        //判断返回的数据是否为空
        if(dataFromYanFa==null)
            return CommonResult.fail(ResultCode.NO_RESPONSE_DATA);
        System.out.println("到底是啥"+dataFromYanFa);

        //全部转化为小写
        String dataFromYanFaLowerCase = dataFromYanFa.toLowerCase();

        //去掉换行和空格
        String dataWithoutBackspace = dataFromYanFaLowerCase.replaceAll("\\s", "");

        //获取最后一个test字样的位置
        int testIndex = dataWithoutBackspace.lastIndexOf("test");

        //判断是否有test
        if (testIndex!=-1){
            //省略对test的处理
            return CommonResult.success().add("test","暂时不知道test是什么");
        }


        //获取train字样的index
        int trainIndex = dataWithoutBackspace.lastIndexOf("trainepoch");

        //如果没有train
        if (trainIndex==-1)
            return CommonResult.success().add("message","没有正在训练的作业");

        String trainData = dataWithoutBackspace.substring(trainIndex);


        //分隔
        String[] splitData = trainData.split(":|\\[|/|\\(|%|:");

        //从分隔中获取值
        Integer epoch = Integer.valueOf(splitData[1]);
        Integer batchIndex = Integer.valueOf(splitData[2]);
        Integer len = Integer.valueOf(splitData[3]);
        Double finishRate = Double.valueOf(splitData[4]) / 100;
        Double trainLoss = Double.valueOf(Double.valueOf(splitData[6].substring(0,4)));

        //创建一个返回对象VO
        TrainProcessStatusVO vo = new TrainProcessStatusVO();
        vo.setBatchIndex(batchIndex);
        vo.setEpoch(epoch);
        vo.setFinishRate(finishRate);
        vo.setLen(len);
        vo.setTrainLoss(trainLoss);

        //转换成json
        JSONObject trainObject = JSONUtil.parseObj(vo);
        return CommonResult.success().add("train",trainObject);
    }

    @PostMapping("/processdata/test")
    public CommonResult testProcess(@RequestBody  String dataFromYanFa){
        //全部转化为小写
        String dataFromYanFaLowerCase = dataFromYanFa.toLowerCase();

        //去掉换行和空格
        String dataWithoutBackspace = dataFromYanFaLowerCase.replaceAll("\\s", "");

        //获取最后一个test字样的位置
        int testIndex = dataWithoutBackspace.lastIndexOf("test");

        //判断是否有test
        if (testIndex!=-1){
            //省略对test的处理
            return CommonResult.success().add("test","暂时不知道test是什么");
        }


        //获取train字样的index
        int trainIndex = dataWithoutBackspace.lastIndexOf("trainepoch");

        //如果没有train
        if (trainIndex==-1)
            return CommonResult.success().add("message","没有正在训练的作业");

        String trainData = dataWithoutBackspace.substring(trainIndex);


        //分隔
        String[] splitData = trainData.split(":|\\[|/|\\(|%|:");

        //从分隔中获取值
        Integer epoch = Integer.valueOf(splitData[1]);
        Integer batchIndex = Integer.valueOf(splitData[2]);
        Integer len = Integer.valueOf(splitData[3]);
        Double finishRate = Double.valueOf(splitData[4]) / 100;
        Double trainLoss = Double.valueOf(Double.valueOf(splitData[6].substring(0,4)));

        //创建一个返回对象VO
        TrainProcessStatusVO vo = new TrainProcessStatusVO();
        vo.setBatchIndex(batchIndex);
        vo.setEpoch(epoch);
        vo.setFinishRate(finishRate);
        vo.setLen(len);
        vo.setTrainLoss(trainLoss);

        //转换成json
        JSONObject trainObject = JSONUtil.parseObj(vo);
        return CommonResult.success().add("train",trainObject);
    }
}
