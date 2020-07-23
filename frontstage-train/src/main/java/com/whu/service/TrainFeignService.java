package com.whu.service;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.constants.ResultCode;
import com.entity.TrainTask;
import com.entity.TrainTaskConf;
import com.responsevo.TrainStartVO;
import com.responsevo.TrainTaskAndTrainTaskConfig;
import com.results.CommonResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

/**
 * OpenFeign跨服务调用层，指向 common-train
 *
 * @author Hedon Wang
 * @create 2020-07-17 11:38
 */

@Service
@FeignClient(value = "common-train")
public interface TrainFeignService {

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
    @PostMapping("/trainTask")
    CommonResult addTrainTask(@RequestBody TrainTaskAndTrainTaskConfig param);


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
    @DeleteMapping("/trainTask/{trainTaskID}")
    CommonResult deleteTrainTaskById(@PathVariable("trainTaskID") Integer trainTaskID);


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
    @PutMapping("/trainTask")
    CommonResult updateTrainTask(@RequestBody TrainTaskAndTrainTaskConfig param);


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
    @GetMapping("/trainTasks/{userId}")
    CommonResult getUserTrainTasks(@PathVariable(value = "userId") Integer userId,
                                   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                   @RequestParam(value = "pageSize", defaultValue = "6") Integer pageSize,
                                   @RequestParam(value = "keyWord", defaultValue = "") String keyWord);


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
    CommonResult getTrainTaskById(@PathVariable("trainTaskId") Integer trainTaskId);

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
    @GetMapping("/trainTasks")
    CommonResult getUserTrainTasks(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                   @RequestParam(value = "pageSize", defaultValue = "6") Integer pageSize,
                                   @RequestParam(value = "keyWord", defaultValue = "") String keyWord);


    /**
     * 接口 6.2.1.5 查看日志
     *
     * @param trainTaskId
     * @return
     * @author Jiahan Wang
     * @create 2020-07-18 19:10
     * @updator Jiahan Wang
     * @upadte 2020-07-18 19:10
     */
    @GetMapping("/trainTask/log/{trainTaskId}")
    CommonResult getTrainTaskLog(@PathVariable("trainTaskId") Integer trainTaskId);


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
    @GetMapping("/trainTask/resources/{trainTaskId}")
    CommonResult getTrainTaskResources(@PathVariable("trainTaskId") Integer trainTaskId);

    /**
     * 接口 6.2.1.10 接收前端数据返回给研发，再从研发获取数据存入数据库
     * @author Yi Zheng
     * @create 2020-07-21 10:00
     * @updator Yi Zheng
     * @upadte  2020-7-22 10:20
     * @param trainTaskId 训练任务id
     * @return
     */
    @GetMapping("/trainTask/start/{trainTaskId}")
    CommonResult startTrainTask(@PathVariable("trainTaskId") Integer trainTaskId);

    /**
     * 接口 6.2.1.9 根据ID删除训练有关的镜像
     * @author Yi Zheng
     * @create 2020-07-21 10:20
     * @updator Yi Zheng
     * @update
     * @param trainTaskID 删除的ID
     * @return  返回通用数据
     */
    @DeleteMapping("/trainTask/container/{trainTaskID}")
    public CommonResult deleteTaskIpContainerById(@PathVariable("trainTaskID") Integer trainTaskID);


    /**
     * 接口 6.2.1.11 接收前端返回的训练作业id发送给研发，从研发获取容器详细信息发送给前端
     * @author Yi Zheng
     * @create 2020-07-21 10:20
     * @updator Yi Zheng
     * @upadte
     * @param id  训练作业id
     * @return CommonResult  通用返回结果
     */
    @GetMapping("/trainTask/info/{id}")
    CommonResult showContainerInfo(@PathVariable("id") Integer id);



    /**
     * 接口 6.2.1.12 接收前端返回的训练作业id发送给研发，再从研发获取容器详细日志发送给前端
     * @author Yi Zheng
     * @create 2020-07-21 10:30
     * @updator Yi Zheng
     * @upadte
     * @param id  训练作业id
     * @return CommonResult  通用返回结果
     */
    @GetMapping("/trainTask/logs/{id}")
    CommonResult showContainerLogs(@PathVariable("id") Integer id);



    /**
     * 接口 6.2.1.13 接收前端返回的训练作业id发送给研发，再从研发获取服务器运行状态发送给前端
     * @author Yi Zheng
     * @create 2020-07-21 10:30
     * @updator Yi Zheng
     * @upadte
     * @param id  训练作业id
     * @return CommonResult  通用返回结果
     */
    @GetMapping("/trainTask/workerStatus/{id}")
    CommonResult showWorkStatus(@PathVariable("id") Integer id);


    /**
     * 接口 6.2.1.14 接收前端返回的训练作业id,数据库查询数据封装传给研发，研发返回信息发给前端
     * @author Yi Zheng
     * @create 2020-07-22 10:40
     * @updator Yi Zheng
     * @upadte
     * @param trainTaskId  训练作业id
     * @return CommonResult  通用返回结果
     */
    @GetMapping("/trainTask/container/status/{trainTaskId}")
    CommonResult showContainerStatus(@PathVariable("trainTaskId") Integer trainTaskId);


    /**
     * 接口 6.2.1.15 接收前端返回的训练作业id,从研发获取日志信息进行字符串处理，处理后根据结果返给前端不同的数据
     * @author Yi Zheng
     * @create 2020-07-23 20:10
     * @updator Yi Zheng
     * @upadte
     * @param trainTaskId  训练作业id
     * @return CommonResult  通用返回结果
     */
    @GetMapping("/trainTask/processdata/{trainTaskId}")
    CommonResult showTrainTaskProcessdata(@PathVariable("trainTaskId") Integer trainTaskId);
}
