package com.whu.controller;

import com.responsevo.TrainStartVO;
import com.responsevo.TrainTaskAndTrainTaskConfig;
import com.results.CommonResult;
import com.whu.service.TrainFeignService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/frontstage")
public class TrainTaskController {


    @Autowired
    private TrainFeignService service;


    /**
     * 接口 6.2.1.1 创建训练作业
     *
     * @param param 一个包装类，因为@RequestBody不能使用两次。这个类里封装了TrainTask和TrainTaskConf
     * @return 返回通用数据
     * @author Yi Zheng
     * @create 2020-07-18 13:00
     * @updator
     * @update
     */
    @ApiOperation(value = "接口6.2.1.1", httpMethod = "POST", notes = "创建训练作业")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "param", value = "训练作业和训练作业参数", paramType = "body", dataType = "TrainTaskAndTrainTaskConfig", required = true),
    })
    @PostMapping("/trainTask")
    CommonResult addTrainTask(@RequestBody TrainTaskAndTrainTaskConfig param) {
        return service.addTrainTask(param);
    }


    /**
     * 接口 6.2.1.2 根据ID删除训练作业
     *
     * @param trainTaskID 删除的ID
     * @return 返回通用数据
     * @author Yi Zheng
     * @create 2020-07-18 13:00
     * @updator
     * @update
     */
    @ApiOperation(value = "接口6.2.1.1", httpMethod = "DELETE", notes = "根据ID删除训练作业")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "trainTaskId", value = "训练作业Id", paramType = "body", dataType = "Integer", required = true)
    })
    @DeleteMapping("/trainTask/{trainTaskID}")
    CommonResult deleteTrainTaskById(@PathVariable("trainTaskID") Integer trainTaskID) {
        return service.deleteTrainTaskById(trainTaskID);
    }


    /**
     * 接口 6.2.1.4 根据训练作业ID同时更新train_task和train_task_conf
     *
     * @param param 一个包装类，因为@RequestBody不能使用两次。这个类里封装了TrainTask和TrainTaskConf
     * @return 返回通用数据
     * @author Yi Zheng
     * @create 2020-07-18 13:00
     * @updator
     * @update
     */
    @ApiOperation(value = "接口6.2.1.4", httpMethod = "PUT", notes = "根据训练作业ID同时更新train_task和train_task_conf")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "param", value = "训练作业和训练作业参数", paramType = "body", dataType = "TrainTaskAndTrainTaskConfig", required = true),
    })
    @PutMapping("/trainTask")
    CommonResult updateTrainTask(@RequestBody TrainTaskAndTrainTaskConfig param) {
        return service.updateTrainTask(param);
    }


    /**
     * 接口 6.2.1.8 分页查询当前用户的训练作业
     *
     * @param userId   用户ID
     * @param pageNum  当前页吗
     * @param pageSize 页面大小
     * @param keyWord  搜索关键字
     * @return
     * @author Jiahan Wang
     * @create 2020-07-18 15:59
     * @updator Jiahan Wang
     * @upadte 2020-07-18 15:59
     */
    @ApiOperation(value = "接口 6.2.1.8 分页查询当前用户的训练作业 ", httpMethod = "GET", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "当前用户ID", paramType = "path", dataType = "Integer", required = true),
            @ApiImplicitParam(name = "pageNum", value = "当前页码", paramType = "query", dataType = "Integer", required = true),
            @ApiImplicitParam(name = "pageSize", value = "页面大小", paramType = "query", dataType = "Integer", required = true),
            @ApiImplicitParam(name = "keyWord", value = "搜索关键字", paramType = "query", dataType = "String", required = true)
    })
    @GetMapping("/trainTasks/{userId}")
    public CommonResult getUserTrainTasks(@PathVariable(value = "userId") Integer userId,
                                          @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                          @RequestParam(value = "pageSize", defaultValue = "6") Integer pageSize,
                                          @RequestParam(value = "keyWord", defaultValue = "") String keyWord) {
        return service.getUserTrainTasks(userId, pageNum, pageSize, keyWord);
    }


    /**
     * 6.2.1.3 按ID查询作业
     *
     * @param trainTaskId
     * @return
     * @author Jiahan Wang
     * @create 2020-07-18 17:59
     * @updator Jiahan Wang
     * @upadte 2020-07-18 17:59
     */
    @ApiOperation(value = "6.2.1.3 按ID查询作业", httpMethod = "GET")
    @ApiImplicitParam(name = "trainTaskId", value = "作业ID", paramType = "path", dataType = "Integer", required = true)
    @GetMapping("/trainTask/{trainTaskId}")
    public CommonResult getTrainTaskById(@PathVariable("trainTaskId") Integer trainTaskId) {

        return service.getTrainTaskById(trainTaskId);
    }

    /**
     * 接口 6.2.1.7 分页查询训练作业
     *
     * @param pageNum  当前页吗
     * @param pageSize 页面大小
     * @param keyWord  搜索关键字
     * @return
     * @author Jiahan Wang
     * @create 2020-07-18 18:59
     * @updator Jiahan Wang
     * @upadte 2020-07-18 18:59
     */
    @ApiOperation(value = "接口 6.2.1.8 分页查询当前用户的训练作业 ", httpMethod = "GET", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页码", paramType = "query", dataType = "Integer", required = true),
            @ApiImplicitParam(name = "pageSize", value = "页面大小", paramType = "query", dataType = "Integer", required = true),
            @ApiImplicitParam(name = "keyWord", value = "搜索关键字", paramType = "query", dataType = "String", required = true)
    })
    @GetMapping("/trainTasks")
    public CommonResult getUserTrainTasks(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                          @RequestParam(value = "pageSize", defaultValue = "6") Integer pageSize,
                                          @RequestParam(value = "keyWord", defaultValue = "") String keyWord) {
        return service.getUserTrainTasks(pageNum, pageSize, keyWord);
    }

    /**
     * 接口 6.2.1.5 查看数据库日志表的内容
     *
     * @param trainTaskId
     * @return
     * @author Jiahan Wang
     * @create 2020-07-18 19:10
     * @updator Jiahan Wang
     * @upadte 2020-07-18 19:10
     */
    @ApiOperation(value = "接口 6.2.1.5 查看日志", httpMethod = "GET", notes = "")
    @ApiImplicitParam(name = "trainTaskId", value = "作业ID", paramType = "path", dataType = "Integer", required = true)
    @GetMapping("/trainTask/log/{trainTaskId}")
    public CommonResult getTrainTaskLog(@PathVariable("trainTaskId") Integer trainTaskId) {
        return service.getTrainTaskLog(trainTaskId);
    }

    /**
     * 接口 6.2.1.6 查询资源占用情况
     *
     * @param trainTaskId
     * @return
     * @author Jiahan Wang
     * @create 2020-07-18 19:20
     * @updator Jiahan Wang
     * @upadte 2020-07-18 19:20
     */
    @ApiOperation(value = "接口 6.2.1.6 查询资源占用情况", httpMethod = "GET", notes = "")
    @ApiImplicitParam(name = "trainTaskId", value = "作业ID", paramType = "path", dataType = "Integer", required = true)
    @GetMapping("/trainTask/resources/{trainTaskId}")
    public CommonResult getTrainTaskResources(@PathVariable("trainTaskId") Integer trainTaskId) {
        return service.getTrainTaskResources(trainTaskId);
    }


    /**
     * 接口 6.2.1.10 接收前端数据返回给研发，再从研发获取数据存入数据库
     * @author Yi Zheng
     * @create 2020-07-21 10:00
     * @updator Yi Zheng
     * @upadte
     * @param vo  研发训练需要的参数封装类
     * @return
     */
    @PostMapping("/trainTask/start")
    public CommonResult startTrainTask(@RequestBody TrainStartVO vo){
        return service.startTrainTask(vo);
    }


    /**
     * 接口 6.2.1.9 根据ID删除训练有关的镜像
     * @author Yi Zheng
     * @create 2020-07-21 10:10
     * @updator Yi Zheng
     * @update
     * @param trainTaskID 删除的ID
     * @return  返回通用数据
     */
    @DeleteMapping("/trainTask/container/{trainTaskID}")
    public CommonResult deleteTaskIpContainerById(@PathVariable("trainTaskID") Integer trainTaskID){
        return service.deleteTrainTaskById(trainTaskID);
    }


    /**
     * 接口 6.2.1.11 接收前端返回的训练作业id发送给研发，从研发获取容器详细信息发送给前端
     * @author Yi Zheng
     * @create 2020-07-21 10:10
     * @updator Yi Zheng
     * @upadte
     * @param id  训练作业id
     * @return CommonResult  通用返回结果
     */
    @GetMapping("/trainTask/info/{id}")
    CommonResult showContainerInfo(@PathVariable("id") Integer id){
        return service.showContainerInfo(id);
    }


    /**
     * 接口 6.2.1.12 接收前端返回的训练作业id发送给研发，再从研发获取容器详细日志发送给前端
     * @author Yi Zheng
     * @create 2020-07-21 10:10
     * @updator Yi Zheng
     * @upadte
     * @param id  训练作业id
     * @return CommonResult  通用返回结果
     */
    @GetMapping("/trainTask/logs/{id}")
    CommonResult showContainerLogs(@PathVariable("id") Integer id){
        return service.showContainerLogs(id);
    }



    /**
     * 接口 6.2.1.13 接收前端返回的训练作业id发送给研发，再从研发获取服务器运行状态发送给前端
     * @author Yi Zheng
     * @create 2020-07-21 10:10
     * @updator Yi Zheng
     * @upadte
     * @param id  训练作业id
     * @return CommonResult  通用返回结果
     */
    @GetMapping("/trainTask/workerStatus/{id}")
    CommonResult showWorkStatus(@PathVariable("id") Integer id){
        return showWorkStatus(id);
    }
}
