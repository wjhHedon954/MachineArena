package com.results;

import com.constants.ResultCode;
import io.swagger.annotations.Api;

import java.util.HashMap;
import java.util.Map;

/**
 * 统一传递给前端的数据的实体类
 * @author Yi Zheng
 * @create 2020-07-11 20:59
 * @updater Jiahan Wang
 * @update 2020-07-12 08:13
 */
@Api
public class CommonResult{

    private String code;                                  //状态码
    private String message;                               //提示信息
    private Map<String,Object> extend = new HashMap<>();  //返回给前端的其他数据

    //成功
    public static CommonResult success(){
        CommonResult result = new CommonResult();
        result.setCode("00000");
        result.setMessage("处理成功！");
        return result;
    }


    //失败
    public static CommonResult fail(ResultCode resultCode){
        CommonResult result = new CommonResult();
        result.setCode(resultCode.getCode());
        result.setMessage(resultCode.getMessage());
        return result;
    }

    //追加其他数据
    public CommonResult add(String key,Object keyValue){
        this.getExtend().put(key,keyValue);
        return this;
    }

    //全参构造
    public CommonResult(String code, String message, Map<String, Object> extend) {
        this.code = code;
        this.message = message;
        this.extend = extend;
    }

    //从 ResultCode 中读取数据来确定 code  和 message 的值
    public CommonResult(ResultCode resultCode){
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.extend = null;
    }

    //半参构造
    public CommonResult(String code, String message) {
        this.code = code;
        this.message = message;
        this.extend = null;
    }


    //无参构造
    public CommonResult() {}

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getExtend() {
        return extend;
    }

    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }

    @Override
    public String toString() {
        return "CommonResult{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", extend=" + extend +
                '}';
    }
}
