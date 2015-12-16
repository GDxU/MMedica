package com.hkm.shake.Elements;

import com.badlogic.gdx.physics.box2d.Body;

/**
 * Created by zJJ on 12/16/2015.
 */
public class Enemy extends GameActor {
    public Enemy(Body body) {
        super(body);
    }

    @Override
    public EnemyUserData getUserData() {
        return (EnemyUserData) userData;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        body.setLinearVelocity(getUserData().getLinearVelocity());
    }
}
