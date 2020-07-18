package com.whu.train_task.controller;


import com.constants.ResultCode;
import com.entity.TrainTask;
import com.entity.TrainTaskConf;
import com.responsevo.TrainTaskAndTrainTaskConfig;
import com.results.CommonResult;
import com.whu.train_task.service.impl.TrainTaskServiceImpl;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Hedon
 * @since 2020-07-17
 */
@RestController
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
     * @updator
     * @update
     * @param trainTaskID 删除的ID
     * @return  返回通用数据
     */
    @ApiOperation(value = "接口6.2.1.1", httpMethod = "DELETE", notes = "根据ID删除训练作业")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "trainTaskId", value = "训练作业Id", paramType = "body", dataType = "Integer", required = true)
    })
    @DeleteMapping("/trainTask/{trainTaskID}")
    public CommonResult deleteTrainTaskById(@PathVariable("trainTaskID") Integer trainTaskID){
        //检查ID是否为空
        if (trainTaskID == null) {
            return CommonResult.fail(ResultCode.EMPTY_PARAM);
        }
        //执行删除操作
        try {
            int deleteCount = trainTaskService.deleteTrainTaskById(trainTaskID);
            if (deleteCount == 0) {
                return CommonResult.fail(ResultCode.TRAINTASK_NOT_EXIST);
            } else {
                return CommonResult.success();
            }
        } catch (Exception e) {
            return CommonResult.fail(ResultCode.ERROR);
        }
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
}
