package com.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * <p>
 * 
 * </p>
 *
 * @author Hedon
 * @since 2020-07-11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
@TableName("tbl_algorithm")
@ApiModel(value="Algorithm对象", description="")
public class Algorithm implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "algorithm_id", type = IdType.AUTO)
    private Integer algorithmId;

    private String algorithmName;

    private String algorithmVersion;

    private Integer algorithmTypeId;

    private Integer algorithmEngineId;

    private Integer algorithmDescriptionId;

    private Integer algorithmInstanceTypeId;

    private String algorithmInputReflect;

    private String algorithmOutputReflect;

    @TableField("algorithm_starter_URL")
    private String algorithmStarterUrl;

    @TableField("algorithm_save_URL")
    private String algorithmSaveUrl;

    private Boolean algorithmCustomizeHyperPara;

    private Integer algorithmPythonVersionId;

    private Integer algorithmStatus;

    private LocalDateTime algorithmCreateTime;
}
