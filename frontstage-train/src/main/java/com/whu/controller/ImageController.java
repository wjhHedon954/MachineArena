package com.whu.controller;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.constants.ResultCode;
import com.entity.Algorithm;
import com.responsevo.ImageVO;
import com.results.CommonResult;
import com.whu.service.ImageFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

        String result = HttpRequest.post("10.10.10.209:7777/image")
                .body(imageJson.toString())
                .execute()
                .body();
        System.out.println(result);
        JSONObject response = JSONUtil.parseObj(result);
        Algorithm algorithm = imageFeignService.getAlgorithmObjectById(image.getAigorithmId());
        algorithm.setAlgorithmImageId((String)response.getJSONObject("extend").get("imageID"));
//        algorithm.setAlgorithmImageId("testid");

        return imageFeignService.updateAlgorithm(algorithm);
    }

    /**
     * @author Huiri Tan
     * @description TODO
     * @create 2020/7/20 7:51 下午
     * @update 2020/7/20 7:51 下午
     * @param
     * @return
     **/
    @PutMapping("/image")
    public CommonResult modifyImage(@RequestBody ImageVO image) {
        JSONObject imageJson = JSONUtil.parseObj(image, false);
        System.out.println(imageJson.toString());
        System.out.println(imageJson.toStringPretty());

        String result = HttpRequest.put("10.10.10.209:7777/image")
                .body(imageJson.toString())
                .execute()
                .body();
        System.out.println(result);
        JSONObject response = JSONUtil.parseObj(result);
        Algorithm algorithm = imageFeignService.getAlgorithmObjectById(image.getAigorithmId());
        algorithm.setAlgorithmImageId((String)response.getJSONObject("extend").get("imageID"));
//        algorithm.setAlgorithmImageId("testid");

        return imageFeignService.updateAlgorithm(algorithm);
    }

    /**
     * @author Huiri Tan
     * @description 删除镜像 要把对应字段置空
     * @create 2020/7/20 7:50 下午
     * @update 2020/7/20 7:50 下午
     * @param [userId, alogrithomId, imageId]
     * @return com.results.CommonResult
     **/
    @DeleteMapping("/image/{userId}/{alogrithomId}/{imageId}")
    public CommonResult deleteImage(@PathVariable(value = "userId")Integer userId,
                                    @PathVariable(value = "alogrithomId")Integer alogrithomId,
                                    @PathVariable(value = "imageId")String imageId) {
        String result = HttpRequest.delete("10.10.10.209:7777/image" + "/" + userId.toString()
                + "/" + alogrithomId.toString() + "/" + imageId)
                .execute()
                .body();
        JSONObject response = JSONUtil.parseObj(result);
        if (response.get("code").equals("00000")) {
            Algorithm algorithm = imageFeignService.getAlgorithmObjectById(alogrithomId);
            algorithm.setAlgorithmImageId("");
            return imageFeignService.updateAlgorithm(algorithm);
        }

        return CommonResult.fail(ResultCode.NO_RESPONSE_DATA);

    }


}
