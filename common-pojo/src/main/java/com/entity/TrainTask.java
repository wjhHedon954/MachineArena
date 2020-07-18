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
@TableName("tbl_train_task")
@ApiModel(value = "TrainTask对象", description = "")
public class TrainTask implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "train_task_id", type = IdType.AUTO)
    private Integer trainTaskId;

    private Integer trainTaskUserId;

    private String trainTaskName;

    private Integer trainTaskStatus;

    private Float trainTaskVersion;

    private LocalDateTime trainTaskCreateTime;

    private LocalDateTime trainTaskUpdateTime;

    private String trainTaskRunningTime;

}
