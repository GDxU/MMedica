package com.hkm.shake.Elements;

import com.badlogic.gdx.physics.box2d.Body;

/**
 * Created by zJJ on 12/16/2015.
 */
public class Runner extends GameActor {

    private boolean dodging;
    private boolean jumping;
    private boolean hit;

    public Runner(Body body) {
        super(body);
    }

    /**
     * to control the jumping action
     *
     * @return user data
     */
    @Override
    public RunnerUserData getUserData() {
        return (RunnerUserData) userData;
    }

    public void jump() {
        if (!(jumping || dodging)) {
            body.applyLinearImpulse(getUserData().getJumpingLinearImpulse(), body.getWorldCenter(), true);
            jumping = true;
        }

    }

    public void landed() {
        jumping = false;
    }
    //ended jumping action

    public void dodge() {
        if (!jumping) {
            body.setTransform(getUserData().getDodgePosition(), getUserData().getDodgeAngle());
            dodging = true;
        }
    }

    public void stopDodge() {
        dodging = false;
        body.setTransform(getUserData().getRunningPosition(), 0f);
    }

    public boolean isDodging() {
        return dodging;
    }


    public void hit() {
        body.applyAngularImpulse(getUserData().getHitAngularImpulse(), true);
        hit = true;
    }

    public boolean isHit() {
        return hit;
    }
}
