package com.hkm.shake.Elements.ui;

import com.badlogic.gdx.math.Rectangle;
import com.hkm.shake.Util.AudioUtils;

/**
 * Created by zJJ on 12/18/2015.
 */
public class MusicButton extends GameButton {

    public MusicButton(Rectangle bounds) {
        super(bounds);
    }

    protected String getRegionName() {
        return AudioUtils.getInstance().getMusicRegionName();
    }

    public void touched() {
        if (AudioUtils.getInstance().getMusic().isPlaying()) {
            AudioUtils.getInstance().pauseMusic();
        }
        AudioUtils.getInstance().toggleMusic();
        AudioUtils.getInstance().playMusic();
    }
}
