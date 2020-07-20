package com.whu.controller;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.entity.Algorithm;
import com.responsevo.ImageVO;
import com.results.CommonResult;
import com.whu.service.ImageFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping("/frontstage")
public class ImageController {
    @Autowired
    ImageFeignService imageFeignService;

    /**
     * @author Huiri Tan
     * @description 创建镜像
     * @create 2020/7/20 12:16 下午
     * @update 2020/7/20 12:16 下午
     * @param [image]
     * @return com.results.CommonResult
     **/
    @PostMapping("/image")
    public CommonResult createImage(@RequestBody ImageVO image) {
        JSONObject imageJson = JSONUtil.parseObj(image, false);
        System.out.println(imageJson.toString());
        System.out.println(imageJson.toStringPretty());

//        String result = HttpRequest.post("47.107.164.39:8080/image")
//                .body(imageJson.toString())
//                .execute()
//                .body();
//        JSONObject response = JSONUtil.parseObj(result);
        Algorithm algorithm = imageFeignService.getAlgorithmObjectById(image.getAigorithmId());
//        algorithm.setAlgorithmImageId((String)response.get("imageID"));
        algorithm.setAlgorithmImageId("testid");

        return imageFeignService.updateAlgorithm(algorithm);
    }

}
