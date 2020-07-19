package com.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Hedon
 * @since 2020-07-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tbl_model_description")
@ApiModel(value="ModelDescription对象", description="")
public class ModelDescription implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "model_description_id", type = IdType.AUTO)
    private Integer modelDescriptionId;

    private Integer modelId;

    private String modelDescriptionContent;


}
