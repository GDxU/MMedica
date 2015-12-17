package com.hkm.shake.Elements.box2d;

import com.hkm.shake.Elements.Indicators.UserDataType;

/**
 * Created by zJJ on 12/16/2015.
 */
public class GroundUserData extends UserData {

    public GroundUserData() {
        super();
        userDataType = UserDataType.GROUND;
    }


    public GroundUserData(float width, float height) {
        super(width, height);
        userDataType = UserDataType.GROUND;
    }

}