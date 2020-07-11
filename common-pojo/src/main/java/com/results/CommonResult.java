package com.results;

import io.swagger.annotations.Api;

import java.util.Map;

@Api
public class CommonResult{
    private String code;
    private String message;
    private Map<String,Object> extend;

    public CommonResult(String code, String message, Map<String, Object> extend) {
        this.code = code;
        this.message = message;
        this.extend = extend;
    }

    public CommonResult(String code, String message) {
        this.code = code;
        this.message = message;
        this.extend = null;
    }

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
