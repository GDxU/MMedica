package com.hkm.shake.Elements.ui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.hkm.shake.Util.AssetsManager;
import com.hkm.shake.Util.Constants;

/**
 * Created by zJJ on 12/18/2015.
 */
public class GameLabel extends Actor {

    private Rectangle bounds;
    private BitmapFont font;

    public GameLabel(Rectangle bounds) {
        this.bounds = bounds;
        setWidth(bounds.width);
        setHeight(bounds.height);
        font = AssetsManager.getLargeFont();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
      //  font.draw(batch, Constants.GAME_NAME, bounds.x, bounds.y, bounds.width, BitmapFont.HAlignment.CENTER);
    }

}
