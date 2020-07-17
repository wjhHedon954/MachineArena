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
@TableName("tbl_train_task_conf")
@ApiModel(value = "TrainTaskConf对象", description = "")
public class TrainTaskConf implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "train_task_conf_id", type = IdType.AUTO)
    private Integer trainTaskConfId;

    private Integer trainTaskId;

    private Integer trainTaskAlgorithmId;

    private Integer trainTaskDatasetId;

    private String trainTaskDescription;

    private String trainTaskName;

    private Integer trainTaskStatus;

    private Integer trainTaskVersion;

    private LocalDateTime trainTaskStartTime;

    private String trainTaskRunningTime;

    private LocalDateTime trainTaskFinishTime;

    private String trainTaskParams;

    private String trainTaskSpecification;

    private String column14;

    private String trainTaskLogOutPath;

    private String trainTaskModelOutPath;


}
