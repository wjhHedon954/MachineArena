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
 * @since 2020-07-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tbl_task_ip_container")
@ApiModel(value="TaskIpContainer对象", description="")
public class TaskIpContainer implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer trainTaskId;

    private String containerId;

    private String workerId;

    @TableId(value = "task_ip_container_id", type = IdType.AUTO)
    private Integer taskIpContainerId;


}
