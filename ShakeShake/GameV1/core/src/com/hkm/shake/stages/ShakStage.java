package com.hkm.shake.stages;

import com.badlogic.gdx.Game;
import com.hkm.shake.Util.AssetsManager;
import com.hkm.shake.Util.AudioUtils;
import com.hkm.shake.Util.GameEventListener;
import com.hkm.shake.Util.GameManager;
import com.hkm.shake.screen.GameScreen;

/**
 * Created by zJJ on 12/16/2015.
 */
public class ShakStage extends Game {

    public ShakStage(GameEventListener listener) {
        GameManager.getInstance().setGameEventListener(listener);
    }


    @Override
    public void create() {
        AssetsManager.loadAssets();
        setScreen(new GameScreen());
    }


    @Override
    public void dispose() {
        super.dispose();
        AudioUtils.dispose();
        AssetsManager.dispose();
    }

}
