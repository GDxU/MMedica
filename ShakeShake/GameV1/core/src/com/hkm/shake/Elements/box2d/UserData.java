package com.hkm.shake.Elements.box2d;

import com.hkm.shake.Elements.Indicators.UserDataType;

/**
 * Created by zJJ on 12/16/2015.
 */
public class UserData {
    protected UserDataType userDataType;
    protected float width, height;

    public UserData() {

    }

    public UserDataType getUserDataType() {
        return userDataType;
    }

    public UserData(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

}
