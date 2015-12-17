package com.hkm.shake.Util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

/**
 * Created by zJJ on 12/17/2015.
 */
public class GeneralUtil {
    boolean getUil() {
        final boolean available = Gdx.input.isPeripheralAvailable(Input.Peripheral.Accelerometer);
        if(available) {
            final int orientation = Gdx.input.getRotation();
            final Input.Orientation nativeOrientation = Gdx.input.getNativeOrientation();
            final float accelX = Gdx.input.getAccelerometerX();
            final float accelY = Gdx.input.getAccelerometerY();
            final float accelZ = Gdx.input.getAccelerometerZ();
        }

        return available;
    }
}
