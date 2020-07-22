package com.whu.train_task.controller;


import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.constants.ResultCode;
import com.entity.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.responsevo.ContainerStatusVo;
import com.responsevo.TrainStartVO;
import com.responsevo.TrainTaskAndTrainTaskConfig;
import com.responsevo.TrainTaskResponseVo;
import com.results.CommonResult;
import com.whu.train_task.service.impl.TrainTaskServiceImpl;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Hedon
 * @since 2020-07-17
 */
@RestController
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = "*")
public class TrainTaskController {
    @Autowired
    private TrainTaskServiceImpl trainTaskService;



    /**
     * 接口 6.2.1.1 创建训练作业
     * @author Yi Zheng
     * @create 2020-07-17 13:00
     * @updator Yi Zheng
     * @update 2020-7-17 16:00
     * @param param 一个包装类，因为@RequestBody不能使用两次。这个类里封装了TrainTask和TrainTaskConf
     * @return  返回通用数据
     */
    @ApiOperation(value = "接口6.2.1.1", httpMethod = "POST", notes = "创建训练作业")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "param", value = "训练作业和训练作业参数", paramType = "body", dataType = "TrainTaskAndTrainTaskConfig", required = true),
    })
    @PostMapping("/trainTask")
    CommonResult addTrainTask(@RequestBody TrainTaskAndTrainTaskConfig param){
        //参数不能为空
        if (param==null)
            return CommonResult.fail(ResultCode.EMPTY_PARAM);

        //从包装类中获取两个真正的对象
        TrainTask trainTask = param.getTrainTask();
        TrainTaskConf trainTaskConf = param.getTrainTaskConf();

        //判断对象是否为空
        if (trainTask == null || trainTaskConf==null) {
            return CommonResult.fail(ResultCode.EMPTY_OBJECT);
        }
        try {
            //执行insert
            int[] ints = trainTaskService.addTrainTask(trainTask,trainTaskConf);
            System.out.println(ints);
            if (ints[0] == 0 || ints[1]==0) {
                //如果更新条数为0，则说明该训练作业或训练作业参数不在数据库中，返回数据不存在信息
                return CommonResult.fail(ResultCode.NO_TrainTask_OR_TrainTaskConf);
            } else {
                //因为是根据ID来更改，所以情况只有 0 和 1，如果不为 0 那必定是成功
                return CommonResult.success();
            }
        } catch (Exception e) {
            return CommonResult.fail(ResultCode.ERROR);
        }
    }


    /**
     * 接口 6.2.1.2 根据ID删除训练作业
     * @author Yi Zheng
     * @create 2020-07-17 14:00
     * @updator Yi Zheng
     * @update 2020-07-19 00:30
     * @param trainTaskID 删除的ID
     * @return  返回通用数据
     */
//    @ApiOperation(value = "接口6.2.1.1", httpMethod = "DELETE", notes = "根据ID删除训练作业")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "trainTaskId", value = "训练作业Id", paramType = "body", dataType = "Integer", required = true)
//    })
    @DeleteMapping("/trainTask/{trainTaskID}")
    public CommonResult deleteTrainTaskById(@PathVariable("trainTaskID") Integer trainTaskID){
        //检查ID是否为空
        if (trainTaskID == null) {
            return CommonResult.fail(ResultCode.EMPTY_PARAM);
        }
        //执行删除操作
        try {
            int i = trainTaskService.deleteTrainTaskById(trainTaskID);
            if (i == 0)
                return CommonResult.fail(ResultCode.DELETE_ERROR);
        } catch (Exception e) {
            return CommonResult.fail(ResultCode.DELETE_ERROR);
        }
        return CommonResult.success();
    }


    /**
     * 接口 6.2.1.4 根据训练作业ID同时更新train_task和train_task_conf
     * @author Yi Zheng
     * @create 2020-07-18 10:00
     * @updator
     * @update
     * @param param 一个包装类，因为@RequestBody不能使用两次。这个类里封装了TrainTask和TrainTaskConf
     * @return  返回通用数据
     */
    @ApiOperation(value = "接口6.2.1.4", httpMethod = "PUT", notes = "根据训练作业ID同时更新train_task和train_task_conf")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "param", value = "训练作业和训练作业参数", paramType = "body", dataType = "TrainTaskAndTrainTaskConfig", required = true),
    })
    @PutMapping("/trainTask")
    public CommonResult updateTrainTask(@RequestBody TrainTaskAndTrainTaskConfig param) {
        //参数不能为空
        if (param==null)
            return CommonResult.fail(ResultCode.EMPTY_PARAM);

        //从包装类中获取两个真正的对象
        TrainTask trainTask = param.getTrainTask();
        TrainTaskConf trainTaskConf = param.getTrainTaskConf();

        //判断对象是否为空
        if (trainTask == null || trainTaskConf==null) {
            return CommonResult.fail(ResultCode.EMPTY_OBJECT);
        }

        try {
            //执行更新
            int[] updates = trainTaskService.updateTrainTask(trainTask, trainTaskConf);
            if (updates[0] == 0 || updates[1]==0) {
                //如果更新条数为0，则说明该update失败，返回update失败信息
                return CommonResult.fail(ResultCode.UPDATE_ERROR);
            } else {
                //因为是根据ID来更改，所以情况只有 0 和 1，如果不为 0 那必定是成功
                return CommonResult.success();
            }
        } catch (Exception e) {
            return CommonResult.fail(ResultCode.ERROR);
        }
    }


    /**
     * 接口 6.2.1.8 分页查询当前用户的训练作业
     * @author Jiahan Wang
     * @create 2020-07-18 15:59
     * @updator Jiahan Wang
     * @upadte 2020-07-18 15:59
     * @param userId    用户ID
     * @param pageNum   当前页吗
     * @param pageSize  页面大小
     * @param keyWord   搜索关键字
     * @return
     */
    @ApiOperation(value = "接口 6.2.1.8 分页查询当前用户的训练作业 ",httpMethod = "GET",notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "当前用户ID",paramType = "path",dataType = "Integer",required = true),
            @ApiImplicitParam(name = "pageNum",value = "当前页码",paramType = "query",dataType = "Integer",required = true),
            @ApiImplicitParam(name = "pageSize",value = "页面大小",paramType = "query",dataType = "Integer",required = true),
            @ApiImplicitParam(name = "keyWord",value = "搜索关键字",paramType = "query",dataType = "String",required = true)
    })
    @GetMapping("/trainTasks/{userId}")
    public CommonResult getUserTrainTasks(@PathVariable(value = "userId")Integer userId,
                                         @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                                         @RequestParam(value = "pageSize",defaultValue = "6")Integer pageSize,
                                         @RequestParam(value = "keyWord",defaultValue = "")String keyWord){
        //1. 检查用户ID是否为空
        if (userId == null){
            return CommonResult.fail(ResultCode.EMPTY_USER_ID);
        }
        //1. 开启分页查询
        PageHelper.startPage(pageNum,pageSize);
        //2. 从数据库中拉取数据
        List<TrainTaskResponseVo> trainTaskResponseVos= trainTaskService.getTrainTasksByUserId(userId,keyWord);
        //3. 封装到 PageInfo 中
        PageInfo pageInfo = new PageInfo(trainTaskResponseVos,5);
        //4. 传给前端
        return CommonResult.success().add("pageInfo",pageInfo);

    }


    /**
     * 6.2.1.3 按ID查询作业
     * @param trainTaskId
     * @return
     */
    @ApiOperation(value = "6.2.1.3 按ID查询作业",httpMethod = "GET")
    @ApiImplicitParam(name = "trainTaskId",value = "作业ID",paramType = "path",dataType = "Integer",required = true)
    @GetMapping("/trainTask/{trainTaskId}")
    public CommonResult getTrainTaskById(@PathVariable("trainTaskId") Integer trainTaskId){
        //检查ID是否为空
        if (trainTaskId == null){
            return CommonResult.fail(ResultCode.TRAIN_TASK_ID_NULL);
        }
        //查询
        TrainTaskAndTrainTaskConfig  trainTaskAndTrainTaskConfig = trainTaskService.getTrainTaskFullInfoById(trainTaskId);
        return CommonResult.success().add("trainTaskAndTrainTaskConfig",trainTaskAndTrainTaskConfig);

    }

    /**
     * 接口 6.2.1.7 分页查询训练作业
     * @author Jiahan Wang
     * @create 2020-07-18 18:59
     * @updator Jiahan Wang
     * @upadte 2020-07-18 18:59
     * @param pageNum   当前页吗
     * @param pageSize  页面大小
     * @param keyWord   搜索关键字
     * @return
     */
    @ApiOperation(value = "接口 6.2.1.8 分页查询当前用户的训练作业 ",httpMethod = "GET",notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "当前页码",paramType = "query",dataType = "Integer",required = true),
            @ApiImplicitParam(name = "pageSize",value = "页面大小",paramType = "query",dataType = "Integer",required = true),
            @ApiImplicitParam(name = "keyWord",value = "搜索关键字",paramType = "query",dataType = "String",required = true)
    })
    @GetMapping("/trainTasks")
    public CommonResult getUserTrainTasks(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                                          @RequestParam(value = "pageSize",defaultValue = "6")Integer pageSize,
                                          @RequestParam(value = "keyWord",defaultValue = "")String keyWord){
        //1. 开启分页查询
        PageHelper.startPage(pageNum,pageSize);
        //2. 从数据库中拉取数据
        List<TrainTaskResponseVo> trainTaskResponseVos= trainTaskService.getTrainTasks(keyWord);
        //3. 封装到 PageInfo 中
        PageInfo pageInfo = new PageInfo(trainTaskResponseVos,5);
        //4. 传给前端
        return CommonResult.success().add("pageInfo",pageInfo);

    }

    /**
     * 接口 6.2.1.5 查看数据库日志表的内容
     * @author Jiahan Wang
     * @create 2020-07-18 19:10
     * @updator Jiahan Wang
     * @upadte 2020-07-18 19:10
     * @param trainTaskId
     * @return
     */
    @ApiOperation(value = "接口 6.2.1.5 查看日志",httpMethod = "GET",notes = "")
    @ApiImplicitParam(name = "trainTaskId",value = "作业ID",paramType = "path",dataType = "Integer",required = true)
    @GetMapping("/trainTask/log/{trainTaskId}")
    public CommonResult getTrainTaskLog(@PathVariable("trainTaskId")Integer trainTaskId){
        if (trainTaskId == null){
            return CommonResult.fail(ResultCode.TRAIN_TASK_ID_NULL);
        }
        List<TrainTaskLog> trainTaskLogs = trainTaskService.getTrainTaskLog(trainTaskId);
        if (trainTaskLogs == null || trainTaskLogs.size()==0){
            return CommonResult.fail(ResultCode.TRAIN_TASK_NO_LOGS);
        }
        return CommonResult.success().add("trainTaskLogs",trainTaskLogs);
    }

    /**
     * 接口 6.2.1.6 查询资源占用情况
     * @author Jiahan Wang
     * @create 2020-07-18 19:20
     * @updator Jiahan Wang
     * @upadte 2020-07-18 19:20
     * @param trainTaskId
     * @return
     */
    @ApiOperation(value = "接口 6.2.1.6 查询资源占用情况",httpMethod = "GET",notes = "")
    @ApiImplicitParam(name = "trainTaskId",value = "作业ID",paramType = "path",dataType = "Integer",required = true)
    @GetMapping("/trainTask/resources/{trainTaskId}")
    public CommonResult getTrainTaskResources(@PathVariable("trainTaskId")Integer trainTaskId){
        if (trainTaskId == null){
            return CommonResult.fail(ResultCode.TRAIN_TASK_ID_NULL);
        }
        List<TrainTaskResource> trainTaskResources = trainTaskService.getTrainTaskResources(trainTaskId);
        if (trainTaskResources == null || trainTaskResources.size() == 0){
            return CommonResult.fail(ResultCode.TRAIN_TASK_NO_RESOURCES);
        }
        return CommonResult.success().add("trainTaskResources",trainTaskResources);
    }


    /**
     * 接口 6.2.1.10 接收前端数据返回给研发，再从研发获取数据存入数据库
     * @author Yi Zheng
     * @create 2020-07-19 00:25
     * @updator Yi Zheng
     * @upadte 2020-07-19 19:20
     * @param vo  研发训练需要的参数封装类
     * @return
     */
    @PostMapping("/trainTask/start")
    public CommonResult startTrainTask(@RequestBody TrainStartVO vo){
        //检查前端返回的数据是否为空
        if (vo==null)
            return CommonResult.fail(ResultCode.EMPTY_PARAM);
        //检查前端核心数据是否为空
        if (vo.getTrainTaskId()==null || vo.getTrainTaskAlgorithmId()==null)
            return CommonResult.fail(ResultCode.NO_TrainTaskId_OR_TaskAlgorithmId);

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
        Integer trainTaskId=vo.getTrainTaskId();
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

        //JSON解析获取容器详细信息
        String extendContent = JSONUtil.parse(result).getByPath("extend", String.class);
        String containerInfoContent = JSONUtil.parse(extendContent).getByPath("containerInfo", String.class);

        return CommonResult.success().add("containerInfo",containerInfoContent);
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

        //JSON解析获取详细日志信息
        JSON resultParse = JSONUtil.parse(result);
        String logs = resultParse.getByPath("extend",String.class);


        return CommonResult.success().add("logs",logs);
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
        String extendContent = JSONUtil.parse(result).getByPath("extend", String.class);
        String serverInfoContent = JSONUtil.parse(extendContent).getByPath("serverInfo", String.class);
        String memoryContent = JSONUtil.parse(serverInfoContent).getByPath("memory", String.class);


        return CommonResult.success().add("memory",memoryContent);
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
        Integer userId = trainTask.getTrainTaskUserId();
        System.out.println(userId);
        if (userId==null)
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

        //根据算法id获取算法
        Algorithm algorithm = trainTaskService.selectAlgorithmById(trainTaskAlgorithmId);
        System.out.println(algorithm);
        if (algorithm==null)
            return CommonResult.fail(ResultCode.SELECT_CONTAINER_STATUS);

        //根据算法获取模型输出路径
        String algorithmOutputReflect = algorithm.getAlgorithmOutputReflect();
        System.out.println(algorithmOutputReflect);
        if (algorithmOutputReflect==null)
            return CommonResult.fail(ResultCode.SELECT_CONTAINER_STATUS);

        //根据训练任务id获取TaskIpContainer
        TaskIpContainer taskIpContainer = trainTaskService.selectTaskIpContainerByTrainTaskId(trainTaskId);
        System.out.println(taskIpContainer);
        if (taskIpContainer==null)
            return CommonResult.fail(ResultCode.SELECT_CONTAINER_STATUS);

        //从taskIpContainer获取容器id
        String containerId = taskIpContainer.getContainerId();
        System.out.println(taskIpContainer);
        if (taskIpContainer==null)
            return CommonResult.fail(ResultCode.SELECT_CONTAINER_STATUS);

        //创建一个新的研发需求的返回类型包装类
        ContainerStatusVo containerStatusVo = new ContainerStatusVo();

        //设置包装类属性
        containerStatusVo.setUserId(userId);
        containerStatusVo.setAlgorithmOutputReflect(algorithmOutputReflect);
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

        //提取研发里面的有用信息
        String extendContent = JSONUtil.parse(dataFromYanFa).getByPath("extend", String.class);
        String messageContent = JSONUtil.parse(extendContent).getByPath("Message", String.class);

        return CommonResult.success().add("status",messageContent);
    }
}
