package com.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

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
@TableName("tbl_train_task_log")
@ApiModel(value = "TrainTaskLog对象", description = "")
public class TrainTaskLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "train_task_log_id", type = IdType.AUTO)
    private Integer trainTaskLogId;

    private Integer trainTaskId;

    private String trainTaskLogName;

    private String trainTaskLogPath;

    private String trainTaskContent;


}
