package com.constants;

import lombok.Getter;

/**
 * 返回给前端的提示码
 * @author Hedon Wang
 * @create 2020-07-12 01:07
 */

@Getter
public enum ResultCode {

    /**
     * 默认成功值
     */
    SUCCESS("00000","操作成功！"),

    /**
     * 通用错误值
     */
    ERROR("E0000","未知错误"),
    EMPTY_OBJECT("E0001","对象不能为空"),
    EMPTY_PARAM("E0003","参数不能为空"),
    EMPTY_USER_ID("E0002","用户ID不能为空"),

    /**
     * 数据库操作部分错误码： DB = DataBase
     * @author Jiahan Wang
     */
    INSERT_ERROR("DB001", "新增错误"),
    UPDATE_ERROR("DB002", "更新错误"),
    DELETE_ERROR("DB003", "删除错误"),
    PARAMETER_ERROR("DB004", "参数错误"),
    INVALID_PARAMETER("DB005", "不合法的参数"),
    MISS_PARAMETER("DB006", "缺少参数"),
    REPEAT_RECORD("DB007","重复记录"),

    /**
     * 算法模块错误码
     */
    ALGORITHM_NOT_EXIST("AL001","算法不存在"),
    EMPTY_ALGORITHM_ID("AL002","算法ID不能为空"),
    EMPTY_HYPER_PARA_ID("AL003","算法超参ID不能为空"),
    EMPTY_HYPER_PARA("AL004","该算法未定义超参规范"),

    /**
     * Python 版本错误码
     */
    NO_PYTHON_VERSION_DATA("PY001","没有 python 版本的数据"),

    /**
     * 训练管理模块
     */
    NO_TrainTask_OR_TrainTaskConf("T0001","数据库中无训练作业或训练作业参数"),
    TRAINTASK_NOT_EXIST("T0002","数据库中无此训练作业"),
    TRAIN_TASK_ID_NULL("T0003","训练作业ID不能为空"),
    TRAIN_TASK_NO_LOGS("T0004","该训练作业下没有日志"),
    TRAIN_TASK_NO_RESOURCES("T0005","该训练作业下没有资源使用记录"),
    NO_TrainTaskId_OR_TaskAlgorithmId("T0006","前端返回数据无算法ID或训练作业ID"),
    FAIL_TO_SEND_REQUEST("T0007","向研发发送请求失败"),
    NO_RESPONSE_DATA("T0088","研发返回数据失败"),
    FAILE_PARSE_JSON("T0009","解析json失败"),
    SELECT_CONTAINER_STATUS("T0010","数据库连锁查询中某一步出错，详情看控制台打印"),
     FAIL_MKDIRS("T0011","linux创建文件夹失败"),
    FAIL_TO_PARSE_JSON("T0012","研发返回的json数据不对，解析失败"),

    /**
     * 训练管理模块
     */
    NO_MODEL_IN_DATABASE("M0001","数据库中无此模型"),


    /**
     * 个人中心模块
     */
    USER_EXISTS("U0001","用户已存在"),
    USER_NOT_EXISTS("U0002","用户不存在"),
    PASSWORD_WRONG("U0003","密码错误"),;

    private String code;     //错误码
    private String message;  //对应的信息

    ResultCode(String code, String msg) {
        this.code = code;
        this.message = msg;
    }

}
