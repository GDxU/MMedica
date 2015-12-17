package com.hkm.shake.Elements.ui;

import com.badlogic.gdx.math.Rectangle;
import com.hkm.shake.Elements.Indicators.GameState;
import com.hkm.shake.Util.Constants;
import com.hkm.shake.Util.GameManager;

/**
 * Created by zJJ on 12/18/2015.
 */
public class PauseButton extends GameButton {

    public interface PauseButtonListener {
        public void onPause();

        public void onResume();
    }

    private PauseButtonListener listener;

    public PauseButton(Rectangle bounds, PauseButtonListener listener) {
        super(bounds);
        this.listener = listener;
    }

    @Override
    protected String getRegionName() {
        return GameManager.getInstance().getGameState() == GameState.PAUSED ? Constants.PLAY_REGION_NAME : Constants.PAUSE_REGION_NAME;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (GameManager.getInstance().getGameState() == GameState.OVER) {
            remove();
        }
    }

    @Override
    public void touched() {
        if (GameManager.getInstance().getGameState() == GameState.PAUSED) {
            listener.onResume();
        } else {
            listener.onPause();
        }
    }
}
