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

import com.h6ah4i.android.media.audiofx.IBassBoost;

public class BassBoostStateStore extends BaseAudioEffectStateStore {
    private IBassBoost.Settings mSettings;

    // === Parcelable ===
    public static final Parcelable.Creator<BassBoostStateStore> CREATOR = new Parcelable.Creator<BassBoostStateStore>() {
        @Override
        public BassBoostStateStore createFromParcel(Parcel in) {
            return new BassBoostStateStore(in);
        }

        @Override
        public BassBoostStateStore[] newArray(int size) {
            return new BassBoostStateStore[size];
        }
    };

    private BassBoostStateStore(Parcel in) {
        super(in);

        mSettings = new IBassBoost.Settings(in.readString());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(mSettings.toString());
    }

    // === Parcelable ===
    public BassBoostStateStore() {
        mSettings = new IBassBoost.Settings();
    }

    public IBassBoost.Settings getSettings() {
        return mSettings;
    }

    /* package */void setSettings(IBassBoost.Settings settings) {
        mSettings = settings.clone();
    }

    public float getNormalizedStrength() {
        return sNormalizerStrength.normalize(mSettings.strength);
    }

    /* package */void setNormalizedStrength(float value) {
        mSettings.strength = sNormalizerStrength.denormalize(value);
    }

    private static final ShortParameterNormalizer sNormalizerStrength =
            new ShortParameterNormalizer((short) 0, (short) 1000);
}
