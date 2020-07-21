package com.responsevo;

public class ContainerStatusVo {
    private Integer userId;
    private Integer trainTaskAlgorithmId;
    private Integer trainTaskId;
    private String containerId;
    private String algorithmOutputReflect;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public String getAlgorithmOutputReflect() {
        return algorithmOutputReflect;
    }

    public void setAlgorithmOutputReflect(String algorithmOutputReflect) {
        this.algorithmOutputReflect = algorithmOutputReflect;
    }

    @Override
    public String toString() {
        return "ContainerStatusVo{" +
                "userId=" + userId +
                ", trainTaskAlgorithmId=" + trainTaskAlgorithmId +
                ", trainTaskId=" + trainTaskId +
                ", containerId='" + containerId + '\'' +
                ", algorithmOutputReflect='" + algorithmOutputReflect + '\'' +
                '}';
    }
}
