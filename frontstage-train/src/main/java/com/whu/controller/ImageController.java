package com.whu.controller;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.responsevo.ImageVO;
import com.results.CommonResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

/**
 * @ClassName ImageController
 * @Description 用于镜像的增删改查
 * @Author thomas
 * @Date 2020/7/20 1:29 上午
 * @Version 1.0
 */
@RestController
public class ImageController {
    @RequestMapping("/image")
    public CommonResult createImage(@RequestBody ImageVO image) {
        JSONObject imageJson = JSONUtil.parseObj(image, false);
        System.out.println(imageJson.toString());
        System.out.println(imageJson.toStringPretty());

        String result = HttpRequest.post("202.114.66.76:7777/image")
                .body(imageJson.toString())
                .execute()
                .body();
        System.out.println(result);

        return CommonResult.success();
    }

}
