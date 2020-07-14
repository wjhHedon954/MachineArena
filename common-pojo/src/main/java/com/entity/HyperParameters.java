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
 * @updator Huiri Tan
 * @update 2020-7-13 22:43
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

    private Boolean hyperParaIsNeeded;

    private Integer algorithmId;

    public void setHyperParaId(Integer hyperParaId) {
        if(hyperParaId == -1) {
            this.hyperParaId = null;
            return;
        }
        this.hyperParaId = hyperParaId;
    }

    public void setHyperParaName(String hyperParaName) {
        this.hyperParaName = hyperParaName;
    }

    public void setHyperParaDescription(String hyperParaDescription) {
        this.hyperParaDescription = hyperParaDescription;
    }

    public void setHyperParaType(Integer hyperParaType) {
        this.hyperParaType = hyperParaType;
    }

    public void setHyperParaAllowAdjust(Boolean hyperParaAllowAdjust) {
        this.hyperParaAllowAdjust = hyperParaAllowAdjust;
    }

    public void setHyperParaRange(String hyperParaRange) {
        this.hyperParaRange = hyperParaRange;
    }

    public void setHyperParaDefaultValue(String hyperParaDefaultValue) {
        this.hyperParaDefaultValue = hyperParaDefaultValue;
    }

    public void setHyperParaIsNeeded(Boolean hyperParaIsNeeded) {
        this.hyperParaIsNeeded = hyperParaIsNeeded;
    }

    public void setAlgorithmId(Integer algorithmId) {
        this.algorithmId = algorithmId;
    }

    public Integer getHyperParaId() {
        return hyperParaId;
    }

    public String getHyperParaName() {
        return hyperParaName;
    }

    public String getHyperParaDescription() {
        return hyperParaDescription;
    }

    public Integer getHyperParaType() {
        return hyperParaType;
    }

    public Boolean getHyperParaAllowAdjust() {
        return hyperParaAllowAdjust;
    }

    public String getHyperParaRange() {
        return hyperParaRange;
    }

    public String getHyperParaDefaultValue() {
        return hyperParaDefaultValue;
    }

    public Boolean getHyperParaIsNeeded() {
        return hyperParaIsNeeded;
    }

    public Integer getAlgorithmId() {
        return algorithmId;
    }
}
