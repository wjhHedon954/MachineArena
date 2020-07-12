package com.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
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
@TableName("tbl_hyper_parameters")
@ApiModel(value="HyperParameters对象", description="")
public class HyperParameters implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "hyper_para_id", type = IdType.AUTO)
    private Integer hyperParaId;

    private String hyperParaName;

    private String hyperParaDescription;

    private Integer hyperParaType;

    private Boolean hyperParaAllowAdjust;

    private String hyperParaRange;

    private String hyperParaDefaultValue;

    private Integer hyperParaIsNeeded;

    private Integer algorithmId;


}
