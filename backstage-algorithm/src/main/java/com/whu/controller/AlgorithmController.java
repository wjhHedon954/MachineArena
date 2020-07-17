package com.whu.controller;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.constants.ResultCode;
import com.entity.Algorithm;
import com.entity.AlgorithmDescription;
import com.entity.HyperParameters;
import com.results.CommonResult;
import com.whu.service.AlgorithmFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Hedon Wang
 * @create 2020-07-14 10:50
 */

@RestController
@RequestMapping("/backstage")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = "*")
public class AlgorithmController {


    @Autowired
    AlgorithmFeignService algorithmFeignService;

    /**
     * 分页查询算法
     * @param pageNum
     * @param pageSize
     * @param keyWord
     * @return
     */
    @GetMapping("/algorithms")
    public CommonResult selectAllAlgorithms(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                                            @RequestParam(value = "pageSize",defaultValue = "6")Integer pageSize,
                                            @RequestParam(value = "keyWord",defaultValue = "")String keyWord){

        return algorithmFeignService.selectAllAlgorithms(pageNum,pageSize,keyWord);
    }

    /**
     * @author Huiri Tan
     * @description 创建算法
     * @create 2020/7/14 5:01 下午
     * @update 2020/7/15 1:50 上午
     * @param request
     * @return com.results.CommonResult
     **/
    @PostMapping("/algorithm")
    public CommonResult addAlgorithm(HttpServletRequest request) {
        Algorithm algorithm = new Algorithm(); // 要创建的算法对象
        List<MultipartFile> files = null;
        MultipartHttpServletRequest params =  (MultipartHttpServletRequest)request;
        try {
            files  =  ((MultipartHttpServletRequest)request).getFiles("myfile");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        // 从传入的数据中获取data并转换为JSONObject 顺便获取超参数
        JSONObject data = null;
        JSONArray hyperParameters = null;
        try {
//            JSONObject tmp = new JSONObject(params.getParameter("data"));
            data =  new JSONObject(params.getParameter("data"));
            hyperParameters = JSONUtil.parseArray(data.get("hyperparameters"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        assert data != null;
        // 首先保存算法描述
        AlgorithmDescription algorithmDescription = new AlgorithmDescription();
        try{
            algorithmDescription.setAlgorithmDescriptionContent(data.get("algorithm_description").toString());
            algorithmDescription.setAlgorithmDescriptionId(0);  // 给ID丢个值，不然请求转发的时候报错
            algorithmDescription = algorithmFeignService.addDescription(algorithmDescription);    // 添加算法描述
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        algorithm.setAlgorithmName(data.get("algorithm_name").toString());
        algorithm.setAlgorithmVersion(data.get("algorithm_version").toString());
        algorithm.setAlgorithmTypeId((int)data.get("algorithm_type_id"));
        algorithm.setAlgorithmEngineId((int)data.get("algorithm_engine_id"));
        algorithm.setAlgorithmDescriptionId(algorithmDescription.getAlgorithmDescriptionId());
        algorithm.setAlgorithmInstanceTypeId((int)data.get("algorithm_instance_type_id"));
        algorithm.setAlgorithmInputReflect(data.get("algorithm_input_reflect").toString());
        algorithm.setAlgorithmOutputReflect(data.get("algorithm_output_reflect").toString());
        algorithm.setAlgorithmStarterUrl(data.get("algorithm_starter_URL").toString());
        algorithm.setAlgorithmSaveUrl("/Users/thomas/Desktop/Data");    // 暂时写死
        algorithm.setAlgorithmCustomizeHyperPara((boolean)data.get("algorithm_customize_hyper_para"));
        algorithm.setAlgorithmStatus(0);
        algorithm.setAlgorithmCreateTime(LocalDateTime.now());
        algorithm.setAlgorithmId(0);    // 丢个数给ID 免得转发会报错

        // 保存算法
        algorithm = algorithmFeignService.addAlgorithm(algorithm); //添加算法

        // 若有超参数则保存
        if(hyperParameters != null) {
            for(int i = 0; i < hyperParameters.size(); i++) {
                HyperParameters hyperParameter = new HyperParameters();
                hyperParameter.setHyperParaName(hyperParameters.getJSONObject(i).get("hyper_para_name").toString());
                hyperParameter.setHyperParaDescription(hyperParameters.getJSONObject(i).get("hyper_para_description").toString());
                hyperParameter.setHyperParaType((int)hyperParameters.getJSONObject(i).get("hyper_para_type"));
                hyperParameter.setHyperParaAllowAdjust((boolean)hyperParameters.getJSONObject(i).get("hyper_para_allow_adjust"));
                hyperParameter.setHyperParaRange(hyperParameters.getJSONObject(i).get("hyper_para_range").toString());
                hyperParameter.setHyperParaDefaultValue(hyperParameters.getJSONObject(i).get("hyper_para_default_value").toString());
                hyperParameter.setHyperParaIsNeeded((boolean)hyperParameters.getJSONObject(i).get("hyper_para_is_needed"));
                hyperParameter.setAlgorithmId(algorithm.getAlgorithmId());
                hyperParameter.setHyperParaId(0);       // 丢个数给ID 免得转发会报错
                algorithmFeignService.addHyperParameters(hyperParameter);
            }
        }

        if(files == null)
            return CommonResult.fail(ResultCode.EMPTY_OBJECT);
        // 循环保存文件
        for (MultipartFile file : files) {
            String originName = file.getOriginalFilename();
            String fileName = originName;
            String originPath = "";
            if (originName == null) {
                return CommonResult.fail(ResultCode.ERROR); // 文件名为空暂时返回未知错误;
            }
            if(originName.contains("/")) {
                fileName = originName.substring(originName.lastIndexOf('/'));
                originPath = originName.substring(0, originName.lastIndexOf('/'));
            }

            String filePath = "/Users/thomas/Desktop/Data/" + originPath;
            File newFile = new File(filePath);
            if(!newFile.exists()) {
                newFile.mkdirs();
            }
            try {
                file.transferTo(new File(newFile + "/" + fileName));
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        return CommonResult.success();
    }

}
