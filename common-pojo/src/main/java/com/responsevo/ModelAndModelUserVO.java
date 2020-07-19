package com.responsevo;

import com.entity.Model;
import com.entity.ModelUser;

public class ModelAndModelUserVO {
    private Model model;
    private ModelUser modelUser;

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public ModelUser getModelUser() {
        return modelUser;
    }

    public void setModelUser(ModelUser modelUser) {
        this.modelUser = modelUser;
    }

    @Override
    public String toString() {
        return "ModelAndModelUserVO{" +
                "model=" + model +
                ", modelUser=" + modelUser +
                '}';
    }
}
