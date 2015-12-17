package com.hkm.shake.Elements.ui;

import com.badlogic.gdx.math.Rectangle;
import com.hkm.shake.Elements.Indicators.GameState;
import com.hkm.shake.Util.Constants;
import com.hkm.shake.Util.GameManager;

/**
 * Created by zJJ on 12/18/2015.
 */
public class LeaderboardButton extends GameButton {

    public interface LeaderboardButtonListener {
        public void onLeaderboard();
    }

    private LeaderboardButtonListener listener;

    public LeaderboardButton(Rectangle bounds, LeaderboardButtonListener listener) {
        super(bounds);
        this.listener = listener;
    }

    @Override
    protected String getRegionName() {
        return Constants.LEADERBOARD_REGION_NAME;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (GameManager.getInstance().getGameState() != GameState.OVER) {
            remove();
        }
    }

    @Override
    public void touched() {
        listener.onLeaderboard();
    }
}
