package com.responsevo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.entity.AiEngine;
import com.entity.AlgorithmDescription;
import com.entity.AlgorithmType;
import com.entity.PythonVersion;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @author Hedon Wang
 * @create 2020-07-12 10:54
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="返回给前端的 Algorithm 完整对象", description="")
public class AlgorithmResponseVo {

    private static final long serialVersionUID = 1L;

    private Integer algorithmId;

    private String algorithmName;

    private String algorithmVersion;

    //联合 Type
    private Integer algorithmTypeId;
    private AlgorithmType algorithmType;

    private Integer algorithmStatus;

    private LocalDateTime algorithmCreateTime;

    private String algorithmImageId;

}
