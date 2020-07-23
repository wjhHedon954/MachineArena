package com.responsevo;

public class ContainerStatusVo {
    private Integer trainTaskUserId;
    private Integer trainTaskAlgorithmId;
    private Integer trainTaskId;
    private String containerId;
    private String trainTaskLogOutPath;
    private String trainTaskModelOutPath;

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

    public Integer getTrainTaskId() {
        return trainTaskId;
    }

    public void setTrainTaskId(Integer trainTaskId) {
        this.trainTaskId = trainTaskId;
    }

    public String getContainerId() {
        return containerId;
    }

    public void setContainerId(String containerId) {
        this.containerId = containerId;
    }

    public String getTrainTaskLogOutPath() {
        return trainTaskLogOutPath;
    }

    public void setTrainTaskLogOutPath(String trainTaskLogOutPath) {
        this.trainTaskLogOutPath = trainTaskLogOutPath;
    }

    public String getTrainTaskModelOutPath() {
        return trainTaskModelOutPath;
    }

    public void setTrainTaskModelOutPath(String trainTaskModelOutPath) {
        this.trainTaskModelOutPath = trainTaskModelOutPath;
    }

    @Override
    public String toString() {
        return "ContainerStatusVo{" +
                "trainTaskUserId=" + trainTaskUserId +
                ", trainTaskAlgorithmId=" + trainTaskAlgorithmId +
                ", trainTaskId=" + trainTaskId +
                ", containerId='" + containerId + '\'' +
                ", trainTaskLogOutPath='" + trainTaskLogOutPath + '\'' +
                ", trainTaskModelOutPath='" + trainTaskModelOutPath + '\'' +
                '}';
    }
}
