package com.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2020-07-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tbl_instance_type")
@ApiModel(value="InstanceType对象", description="")
public class InstanceType implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer instanceTypeId;

    private String instanceTypeDescription;


}
