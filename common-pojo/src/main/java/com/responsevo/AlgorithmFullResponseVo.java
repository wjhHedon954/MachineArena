package com.responsevo;

import com.entity.AiEngine;
import com.entity.AlgorithmDescription;
import com.entity.AlgorithmType;
import com.entity.InstanceType;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @author Hedon Wang
 * @create 2020-07-18 14:17
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="返回给前端的 Algorithm 完整对象", description="")
public class AlgorithmFullResponseVo {

    private static final long serialVersionUID = 1L;

    private Integer algorithmId;

    private String algorithmName;

    private String algorithmVersion;

    //联合 Type
    private Integer algorithmTypeId;
    private AlgorithmType algorithmType;

    //联合 AIEngine
    private Integer algorithmEngineId;
    private AiEngine aiEngine;

    //联合 Description
    private Integer algorithmDescriptionId;
//    private AlgorithmDescription algorithmDescription;

    //联合 InstanceType
    private Integer algorithmInstanceTypeId;
    private InstanceType instanceType;

    private String algorithmInputReflect;

    private String algorithmOutputReflect;

    private String algorithmStarterUrl;

    private String algorithmSaveUrl;

    private Integer algorithmCustomizeHyperPara;

    private Integer algorithmStatus;

    private LocalDateTime algorithmCreateTime;

    private String algorithmImageId;

}
