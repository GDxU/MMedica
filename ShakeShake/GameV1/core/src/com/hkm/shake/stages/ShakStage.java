package com.hkm.shake.stages;

import com.badlogic.gdx.Game;
import com.hkm.shake.screen.GameScreen;

/**
 * Created by zJJ on 12/16/2015.
 */
public class ShakStage extends Game {
    @Override
    public void create() {
        setScreen(new GameScreen());
    }
}
