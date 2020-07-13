package com.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
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
@TableName("tbl_algorithm")
@ApiModel(value="Algorithm对象", description="")
public class Algorithm implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "algorithm_id", type = IdType.AUTO)
    private Integer algorithmId;

    private String algorithmName;

    private String algorithmVersion;

    private Integer algorithmTypeId;

    private Integer algorithmEngineId;

    private Integer algorithmDescriptionId;

    private Integer algorithmInstanceTypeId;

    private String algorithmInputReflect;

    private String algorithmOutputReflect;

    @TableField("algorithm_starter_URL")
    private String algorithmStarterUrl;

    @TableField("algorithm_save_URL")
    private String algorithmSaveUrl;

    private Boolean algorithmCustomizeHyperPara;

    private Integer algorithmPythonVersionId;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getAlgorithmId() {
        return algorithmId;
    }

    public void setAlgorithmId(Integer algorithmId) {
        this.algorithmId = algorithmId;
    }

    public String getAlgorithmName() {
        return algorithmName;
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public String getAlgorithmVersion() {
        return algorithmVersion;
    }

    public void setAlgorithmVersion(String algorithmVersion) {
        this.algorithmVersion = algorithmVersion;
    }

    public Integer getAlgorithmTypeId() {
        return algorithmTypeId;
    }

    public void setAlgorithmTypeId(Integer algorithmTypeId) {
        this.algorithmTypeId = algorithmTypeId;
    }

    public Integer getAlgorithmEngineId() {
        return algorithmEngineId;
    }

    public void setAlgorithmEngineId(Integer algorithmEngineId) {
        this.algorithmEngineId = algorithmEngineId;
    }

    public Integer getAlgorithmDescriptionId() {
        return algorithmDescriptionId;
    }

    public void setAlgorithmDescriptionId(Integer algorithmDescriptionId) {
        this.algorithmDescriptionId = algorithmDescriptionId;
    }

    public Integer getAlgorithmInstanceTypeId() {
        return algorithmInstanceTypeId;
    }

    public void setAlgorithmInstanceTypeId(Integer algorithmInstanceTypeId) {
        this.algorithmInstanceTypeId = algorithmInstanceTypeId;
    }

    public String getAlgorithmInputReflect() {
        return algorithmInputReflect;
    }

    public void setAlgorithmInputReflect(String algorithmInputReflect) {
        this.algorithmInputReflect = algorithmInputReflect;
    }

    public String getAlgorithmOutputReflect() {
        return algorithmOutputReflect;
    }

    public void setAlgorithmOutputReflect(String algorithmOutputReflect) {
        this.algorithmOutputReflect = algorithmOutputReflect;
    }

    public String getAlgorithmStarterUrl() {
        return algorithmStarterUrl;
    }

    public void setAlgorithmStarterUrl(String algorithmStarterUrl) {
        this.algorithmStarterUrl = algorithmStarterUrl;
    }

    public String getAlgorithmSaveUrl() {
        return algorithmSaveUrl;
    }

    public void setAlgorithmSaveUrl(String algorithmSaveUrl) {
        this.algorithmSaveUrl = algorithmSaveUrl;
    }

    public Boolean getAlgorithmCustomizeHyperPara() {
        return algorithmCustomizeHyperPara;
    }

    public void setAlgorithmCustomizeHyperPara(Boolean algorithmCustomizeHyperPara) {
        this.algorithmCustomizeHyperPara = algorithmCustomizeHyperPara;
    }

    public Integer getAlgorithmPythonVersionId() {
        return algorithmPythonVersionId;
    }

    public void setAlgorithmPythonVersionId(Integer algorithmPythonVersionId) {
        this.algorithmPythonVersionId = algorithmPythonVersionId;
    }
}
