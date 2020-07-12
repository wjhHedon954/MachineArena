package com.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
@TableName("tbl_algorithm_user")
@ApiModel(value="AlgorithmUser对象", description="")
public class AlgorithmUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "algorithm_user_id", type = IdType.AUTO)
    private Integer algorithmUserId;

    private Integer algorithmId;

    private Integer userId;

    private Boolean isOwner;

    private LocalDateTime subscribeStartTime;

    private LocalDateTime subscribeEndTime;


}
