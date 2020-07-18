package com.responsevo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @author Hedon Wang
 * @create 2020-07-18 15:59
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TrainTaskResponseVo {

    private static final long serialVersionUID = 1L;

    private Integer trainTaskId;

    private Integer trainTaskUserId;

    private String trainTaskName;

    private Integer trainTaskStatus;

    private Float trainTaskVersion;

    private LocalDateTime trainTaskCreateTime;

    private LocalDateTime trainTaskUpdateTime;

    private String trainTaskRunningTime;

}
