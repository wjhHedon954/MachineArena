package com.responsevo;

import com.entity.TrainTask;
import com.entity.TrainTaskConf;

public class TrainTaskAndTrainTaskConfig {
    private TrainTask trainTask;
    private TrainTaskConf trainTaskConf;

    public TrainTaskAndTrainTaskConfig(TrainTask trainTask, TrainTaskConf trainTaskConf) {
        this.trainTask = trainTask;
        this.trainTaskConf = trainTaskConf;
    }

    public TrainTaskAndTrainTaskConfig() {
    }

    public TrainTask getTrainTask() {
        return trainTask;
    }

    public void setTrainTask(TrainTask trainTask) {
        this.trainTask = trainTask;
    }

    public TrainTaskConf getTrainTaskConf() {
        return trainTaskConf;
    }

    public void setTrainTaskConf(TrainTaskConf trainTaskConf) {
        this.trainTaskConf = trainTaskConf;
    }
}

