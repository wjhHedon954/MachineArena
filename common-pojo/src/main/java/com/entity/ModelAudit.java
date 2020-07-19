package com.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
@TableName("tbl_model_audit")
@ApiModel(value="ModelAudit对象", description="")
public class ModelAudit implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "model_audit_id", type = IdType.AUTO)
    private Integer modelAuditId;

    private Integer modelId;

    private Integer modelAuditorId;

    private Integer modelBelongerId;

    private Integer modelAuditStatus;

    private LocalDateTime modelAuditSubmitTime;

    private LocalDateTime modelAuditTime;


}
