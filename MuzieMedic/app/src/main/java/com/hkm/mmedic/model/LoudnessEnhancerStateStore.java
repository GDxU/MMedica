/*
 *    Copyright (C) 2014 Haruki Hasegawa
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.hkm.mmedic.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.h6ah4i.android.media.audiofx.ILoudnessEnhancer;

public class LoudnessEnhancerStateStore extends BaseAudioEffectStateStore {
    private ILoudnessEnhancer.Settings mSettings;

    // === Parcelable ===
    public static final Parcelable.Creator<LoudnessEnhancerStateStore> CREATOR = new Parcelable.Creator<LoudnessEnhancerStateStore>() {
        @Override
        public LoudnessEnhancerStateStore createFromParcel(Parcel in) {
            return new LoudnessEnhancerStateStore(in);
        }

        @Override
        public LoudnessEnhancerStateStore[] newArray(int size) {
            return new LoudnessEnhancerStateStore[size];
        }
    };

    private LoudnessEnhancerStateStore(Parcel in) {
        super(in);

        mSettings = new ILoudnessEnhancer.Settings(in.readString());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(mSettings.toString());
    }

    // === Parcelable ===

    public LoudnessEnhancerStateStore() {
        mSettings = new ILoudnessEnhancer.Settings();
    }

    public ILoudnessEnhancer.Settings getSettings() {
        return mSettings;
    }

    /* package */void setSettings(ILoudnessEnhancer.Settings settings) {
        mSettings = settings.clone();
    }

    public float getNormalizedTargetGainmB() {
        return sNormalizerTargetGainmB.normalize(mSettings.targetGainmB);
    }

    /* package */void setNormalizedTargetGainmB(float value) {
        mSettings.targetGainmB = sNormalizerTargetGainmB.denormalize(value);
    }

    private static final IntParameterNormalizer sNormalizerTargetGainmB =
            new IntParameterNormalizer(0, 1000);
}
