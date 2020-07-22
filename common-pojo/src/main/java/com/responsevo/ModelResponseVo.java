package com.responsevo;

import com.entity.ModelType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Hedon Wang
 * @create 2020-07-22 23:05
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ModelResponseVo {

    private Integer modelId;

    private String modelName;

    //联合ModelType
    private Integer modelTypeId;
    private ModelType modelType;

    private Integer modelStatus;

    private String modelPhotoUrl;

    private String modelUrl;

    private String modelApi;

    private Integer modelIsSuccessful;
}
