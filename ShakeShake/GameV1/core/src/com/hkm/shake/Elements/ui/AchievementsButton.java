package com.hkm.shake.Elements.ui;

import com.badlogic.gdx.math.Rectangle;
import com.hkm.shake.Util.Constants;

/**
 * Created by zJJ on 12/18/2015.
 */
public class AchievementsButton extends GameButton {

    public interface AchievementsButtonListener {
        public void onAchievements();
    }

    private AchievementsButtonListener listener;

    public AchievementsButton(Rectangle bounds, AchievementsButtonListener listener) {
        super(bounds);
        this.listener = listener;
    }

    @Override
    protected String getRegionName() {
        return Constants.ACHIEVEMENTS_REGION_NAME;
    }

    @Override
    public void touched() {
        listener.onAchievements();
    }
}
