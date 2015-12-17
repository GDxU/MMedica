package com.hkm.shake.android;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.hkm.shake.Util.GameEventListener;
import com.hkm.shake.stages.ShakStage;

public class AndroidLauncher extends AndroidApplication implements GameEventListener {
    private boolean mLeaderboardRequested;
    private boolean mAchievementsRequested;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create the layout
        //   RelativeLayout layout = new RelativeLayout(this);
/*
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().clearFlags(
                WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);*/

        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        initialize(new ShakStage(this), config);

        //   View gameView = initializeForView(new ShakStage(this), config);
        //   layout.addView(gameView);

        //  setContentView(gameView);

/*
google ad
		mAdView = createAdView();
		mAdView.loadAd(createAdRequest());

		layout.addView(mAdView, getAdParams());

		setContentView(layout);

		gameHelper = new GameHelper(this, GameHelper.CLIENT_GAMES);
		gameHelper.setup(this);
		gameHelper.setMaxAutoSignInAttempts(0);*/


    }
/*


    private AdView createAdView() {
        AdView adView = new AdView(this);

        adView.setAdSize(AdSize.SMART_BANNER);
        adView.setAdUnitId(getAdMobUnitId());

        return adView;
    }

       @Override
    public void onSignInFailed() {
        // handle sign-in failure (e.g. show Sign In button)
        mLeaderboardRequested = false;
        mAchievementsRequested = false;
    }
 @Override
    public void onSignInSucceeded() {
        // handle sign-in success
        if (GameManager.getInstance().hasSavedMaxScore()) {
            GameManager.getInstance().submitSavedMaxScore();
        }

        if (mLeaderboardRequested) {
            displayLeaderboard();
            mLeaderboardRequested = false;
        }

        if (mAchievementsRequested) {
            displayAchievements();
            mAchievementsRequested = false;
        }
    }

*/

    private RelativeLayout.LayoutParams getAdParams() {
        RelativeLayout.LayoutParams adParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);

        adParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);

        return adParams;
    }


    @Override
    public void displayAd() {

    }

    @Override
    public void hideAd() {

    }

    @Override
    public void submitScore(int score) {

    }

    @Override
    public void displayLeaderboard() {

    }

    @Override
    public void displayAchievements() {

    }

    @Override
    public void share() {

    }

    @Override
    public void unlockAchievement(String id) {

    }

    @Override
    public void incrementAchievement(String id, int steps) {

    }

    @Override
    public String getGettingStartedAchievementId() {
        return null;
    }

    @Override
    public String getLikeARoverAchievementId() {
        return null;
    }

    @Override
    public String getSpiritAchievementId() {
        return null;
    }

    @Override
    public String getCuriosityAchievementId() {
        return null;
    }

    @Override
    public String get5kClubAchievementId() {
        return null;
    }

    @Override
    public String get10kClubAchievementId() {
        return null;
    }

    @Override
    public String get25kClubAchievementId() {
        return null;
    }

    @Override
    public String get50kClubAchievementId() {
        return null;
    }

    @Override
    public String get10JumpStreetAchievementId() {
        return null;
    }

    @Override
    public String get100JumpStreetAchievementId() {
        return null;
    }

    @Override
    public String get500JumpStreetAchievementId() {
        return null;
    }
}
