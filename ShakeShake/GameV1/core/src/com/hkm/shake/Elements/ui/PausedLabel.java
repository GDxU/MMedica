package com.hkm.shake.Elements.ui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.hkm.shake.Elements.Indicators.GameState;
import com.hkm.shake.Util.AssetsManager;
import com.hkm.shake.Util.Constants;
import com.hkm.shake.Util.GameManager;

/**
 * Created by zJJ on 12/18/2015.
 */
public class PausedLabel extends Actor {

    private Rectangle bounds;
    private BitmapFont font;

    public PausedLabel(Rectangle bounds) {
        this.bounds = bounds;
        setWidth(bounds.width);
        setHeight(bounds.height);
        font = AssetsManager.getSmallFont();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (GameManager.getInstance().getGameState() == GameState.PAUSED) {
         //   font.draw(batch, Constants.PAUSED_LABEL, bounds.x, bounds.y, bounds.width, BitmapFont.HAlignment.CENTER);
        }
    }
}
