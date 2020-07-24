package com.responsevo;

public class TrainProcessStatusVO {
    private Integer epoch;
    private Integer batchIndex;
    private Integer len;
    private Double finishRate;
    private Double trainLoss;

    public Integer getEpoch() {
        return epoch;
    }

    public void setEpoch(Integer epoch) {
        this.epoch = epoch;
    }

    public Integer getBatchIndex() {
        return batchIndex;
    }

    public void setBatchIndex(Integer batchIndex) {
        this.batchIndex = batchIndex;
    }

    public Integer getLen() {
        return len;
    }

    public void setLen(Integer len) {
        this.len = len;
    }

    public Double getFinishRate() {
        return finishRate;
    }

    public void setFinishRate(Double finishRate) {
        this.finishRate = finishRate;
    }

    public Double getTrainLoss() {
        return trainLoss;
    }

    public void setTrainLoss(Double trainLoss) {
        this.trainLoss = trainLoss;
    }

    @Override
    public String toString() {
        return "TrainProcessStatusVO{" +
                "epoch=" + epoch +
                ", batchIndex=" + batchIndex +
                ", len=" + len +
                ", finishRate=" + finishRate +
                ", trainLoss=" + trainLoss +
                '}';
    }
}
