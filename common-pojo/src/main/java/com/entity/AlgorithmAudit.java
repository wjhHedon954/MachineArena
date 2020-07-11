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
 * @since 2020-07-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tbl_algorithm_audit")
@ApiModel(value="AlgorithmAudit对象", description="")
public class AlgorithmAudit implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "algorithm_audit_id", type = IdType.AUTO)
    private Integer algorithmAuditId;

    private Integer algorithmId;

    private Integer algorithmAuditorId;

    private Integer algorithmBelongerId;

    private Integer algorithmAuditStatus;

    private LocalDateTime algorithmAuditSubmitTime;

    private LocalDateTime algorithmAuditTime;


}
