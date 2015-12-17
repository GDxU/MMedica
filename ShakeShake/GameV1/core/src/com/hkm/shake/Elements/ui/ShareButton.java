package com.hkm.shake.Elements.ui;

import com.badlogic.gdx.math.Rectangle;
import com.hkm.shake.Util.Constants;

/**
 * Created by zJJ on 12/18/2015.
 */
public class ShareButton extends GameButton {

    public interface ShareButtonListener {
        public void onShare();
    }

    private ShareButtonListener listener;

    public ShareButton(Rectangle bounds, ShareButtonListener listener) {
        super(bounds);
        this.listener = listener;
    }

    @Override
    protected String getRegionName() {
        return Constants.SHARE_REGION_NAME;
    }

    @Override
    public void touched() {
        listener.onShare();
    }

}