package service;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.TimeUtils;


/**
 * Created by Luke on 3/12/2017.
 */

public class ScoreService {

    public final static String GAME_PREFS = "pl.wajatos.pretomatorro.prefs";
    public final static String GAME_SCORE = "pl.wajatos.pretomatorro.prefs.score";
    public final static String GAME_PASSIVE_INCOME = "pl.wajatos.pretomatorro.prefs.passiveIncome";
    public final static String GAME_SAVED_TIMESTAMP = "pl.wajatos.pretomatorro.prefs.savedTimestamp";

    private Preferences prefs;

    private int points;
    private int passiveIncome;

    public ScoreService() {
        init();
    }

    private void init() {
        prefs = Gdx.app.getPreferences(GAME_PREFS);
        loadScore();
        loadPassiveIncome();
    }

    private void loadScore() {
        points = prefs.getInteger(GAME_SCORE);
    }

    private void loadPassiveIncome() { passiveIncome = prefs.getInteger(GAME_PASSIVE_INCOME);}

    public void addPoint(){
        points++;

    }

    public void addPoints(int pointsToAdd){
        points += pointsToAdd;

    }

    public void addPassiveIncome() {
        passiveIncome++;

    }

    public void resetGameScore() {
        points = 0;
        passiveIncome = 0;

    }

    public void saveCurrentGamestate() {
        prefs.putLong(GAME_SAVED_TIMESTAMP, TimeUtils.millis());
        prefs.putInteger(GAME_SCORE, points);
        prefs.putInteger(GAME_PASSIVE_INCOME, passiveIncome);
        prefs.flush();
    }

    /**
     *
     * Getters and Setters
     */
    public int getPoints() {
        return points;
    }

    public int getPassiveIncome() { return passiveIncome;}

    public long getSavedTimestamp(){
        return prefs.getLong(GAME_SAVED_TIMESTAMP);
    }
}
