package com.responsevo;

import io.swagger.models.auth.In;

public class TrainStartVO {
    private Integer trainTaskAlgorithmId;
    private String trainTaskParams;
    private String trainTaskSpecification;
    private Integer trainTaskId;

    public Integer getTrainTaskAlgorithmId() {
        return trainTaskAlgorithmId;
    }

    public void setTrainTaskAlgorithmId(Integer trainTaskAlgorithmId) {
        this.trainTaskAlgorithmId = trainTaskAlgorithmId;
    }

    public String getTrainTaskParams() {
        return trainTaskParams;
    }

    public void setTrainTaskParams(String trainTaskParams) {
        this.trainTaskParams = trainTaskParams;
    }

    public String getTrainTaskSpecification() {
        return trainTaskSpecification;
    }

    public void setTrainTaskSpecification(String trainTaskSpecification) {
        this.trainTaskSpecification = trainTaskSpecification;
    }

    public Integer getTrainTaskId() {
        return trainTaskId;
    }

    public void setTrainTaskId(Integer trainTaskId) {
        this.trainTaskId = trainTaskId;
    }

    @Override
    public String toString() {
        return "TrainStartVO{" +
                "trainTaskAlgorithmId=" + trainTaskAlgorithmId +
                ", trainTaskParams='" + trainTaskParams + '\'' +
                ", trainTaskSpecification='" + trainTaskSpecification + '\'' +
                ", trainTaskId='" + trainTaskId + '\'' +
                '}';
    }
}
