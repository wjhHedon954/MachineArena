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
@TableName("tbl_model_user")
@ApiModel(value="ModelUser对象", description="")
public class ModelUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "model_user_id", type = IdType.AUTO)
    private Integer modelUserId;

    private Integer modelOwnerId;

    private Integer modelId;

    private Integer isModelUploader;


}
