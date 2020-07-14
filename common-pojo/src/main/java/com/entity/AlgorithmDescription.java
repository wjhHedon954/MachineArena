package com.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.oracle.tools.packager.Log;
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
@TableName("tbl_algorithm_description")
@ApiModel(value="AlgorithmDescription对象", description="")
public class AlgorithmDescription implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "algorithm_description_id", type = IdType.AUTO)
    private Integer algorithmDescriptionId;

    private String algorithmDescriptionContent;

    public void setAlgorithmDescriptionId(int algorithmDescriptionId) {
        if(algorithmDescriptionId == -1) {
            Log.info("\n set id to null! \n");
            this.algorithmDescriptionId = null;
            return;
        }
        this.algorithmDescriptionId = algorithmDescriptionId;
    }

    public int getAlgorithmDescriptionId() {
        return this.algorithmDescriptionId;
    }

    public void setAlgorithmDescriptionContent(String content) {
        this.algorithmDescriptionContent = content;
    }

//    public String getAlgorithmDescriptionContent() {
//        return this.algorithmDescriptionContent;
//    }

}
