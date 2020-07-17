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
    NO_PYTHON_VERSION_DATA("PY001","没有 python 版本的数据");

    /**
     * 训练管理模块
     */


    private String code;     //错误码
    private String message;  //对应的信息

    ResultCode(String code, String msg) {
        this.code = code;
        this.message = msg;
    }

}
