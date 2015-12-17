package com.hkm.shake.Util;

/**
 * Created by zJJ on 12/17/2015.
 *
 * Game events that are platform specific (i.e. submitting a score or displaying an ad inn an
 * Android app is different than doing the same in a desktop app).
 */

public interface GameEventListener {

    /**
     * Displays an ad
     */
    public void displayAd();

    /**
     * Hides an ad
     */
    public void hideAd();

    /**
     * Submits a given score. Used every time the game is over
     *
     * @param score
     */
    public void submitScore(int score);

    /**
     * Displays the scores leaderboard
     */
    public void displayLeaderboard();

    /**
     * Displays the game's achievements
     */
    public void displayAchievements();

    /**
     * Shares the game's website
     */
    public void share();

    /**
     * Unlocks an achievement with the given ID
     *
     * @param id achievement ID
     * @see <a href="https://developers.google.com/games/services/">Google Play Game Services</a>
     */
    public void unlockAchievement(String id);

    /**
     * Increments an achievement with the given ID
     *
     * @param id    achievement ID
     * @param steps incremental steps
     * @see <a href="https://developers.google.com/games/services/">Google Play Game Services</a>
     */
    public void incrementAchievement(String id, int steps);

    /**
     * The following are getters for specific achievement IDs used in this game
     */

    /**
     * @return "Getting Started" achievement ID
     */
    public String getGettingStartedAchievementId();

    /**
     * @return "Like a Rover" achievement ID
     */
    public String getLikeARoverAchievementId();

    /**
     * @return "Spirit" achievement ID
     */
    public String getSpiritAchievementId();

    /**
     * @return "Curiosity" achievement ID
     */
    public String getCuriosityAchievementId();

    /**
     * @return "5k Club" achievement ID
     */
    public String get5kClubAchievementId();

    /**
     * @return "10k Club" achievement ID
     */
    public String get10kClubAchievementId();

    /**
     * @return "25k Club" achievement ID
     */
    public String get25kClubAchievementId();

    /**
     * @return "50k Club" achievement ID
     */
    public String get50kClubAchievementId();

    /**
     * @return "10 Jump Street" achievement ID
     */
    public String get10JumpStreetAchievementId();

    /**
     * @return "100 Jump Street" achievement ID
     */
    public String get100JumpStreetAchievementId();

    /**
     * @return "500 Jump Street" achievement ID
     */
    public String get500JumpStreetAchievementId();
}
