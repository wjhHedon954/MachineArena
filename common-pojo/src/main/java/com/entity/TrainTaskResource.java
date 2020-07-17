package com.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author Hedon
 * @since 2020-07-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tbl_train_task_resource")
@ApiModel(value = "TrainTaskResource对象", description = "")
public class TrainTaskResource implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "train_task_resource_id", type = IdType.AUTO)
    private Integer trainTaskResourceId;

    private Integer trainTaskId;

    private LocalDateTime timePoint;

    private Float trainTaskCpu;

    private Float trainTaskMem;


}
