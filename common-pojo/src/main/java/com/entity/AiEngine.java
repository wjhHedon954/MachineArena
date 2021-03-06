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
@TableName("tbl_ai_engine")
@ApiModel(value="AiEngine对象", description="")
public class AiEngine implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "algorithm_engine_id", type = IdType.AUTO)
    private Integer algorithmEngineId;

    private String algorithmEngineName;

    private String algorithmEngineVersion;

    private String pythonVersionName;


}
