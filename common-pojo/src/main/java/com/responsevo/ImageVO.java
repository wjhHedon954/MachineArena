package com.responsevo;

import io.swagger.annotations.ApiModel;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @ClassName ImageVO
 * @Description 创建和修改镜像时使用的VO
 * @Author thomas
 * @Date 2020/7/20 1:39 上午
 * @Version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel(value = "创建镜像时传递的参数")
public class ImageVO {

    Integer userId;

    Integer algorithmId;

    Integer algorithm_engine_id;

    String algorithm_input_reflect;

    String algorithm_output_reflect;

    String algorithm_starter_URL;

}
