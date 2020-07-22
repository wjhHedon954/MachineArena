package com.responsevo;


public class TrainStartVO {
    private Integer trainTaskUserId;
    private Integer trainTaskAlgorithmId;
    private String trainTaskParams;
    private String trainTaskSpecification;
    private Integer trainTaskId;

    public Integer getTrainTaskUserId() {
        return trainTaskUserId;
    }

    public void setTrainTaskUserId(Integer trainTaskUserId) {
        this.trainTaskUserId = trainTaskUserId;
    }

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
                "trainTaskUserId=" + trainTaskUserId +
                ", trainTaskAlgorithmId=" + trainTaskAlgorithmId +
                ", trainTaskParams='" + trainTaskParams + '\'' +
                ", trainTaskSpecification='" + trainTaskSpecification + '\'' +
                ", trainTaskId=" + trainTaskId +
                '}';
    }
}
