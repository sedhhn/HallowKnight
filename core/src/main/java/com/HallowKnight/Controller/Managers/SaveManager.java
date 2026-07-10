package com.HallowKnight.Controller.Managers;

import com.HallowKnight.Model.GameState;
import com.HallowKnight.Model.Knight.Knight;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import java.util.Map;

public class SaveManager {
    private static final String PREFS_NAME_0 = "HollowKnightSave0";
    private static final String PREFS_NAME_1 = "HollowKnightSave1";
    private static final String PREFS_NAME_2 = "HollowKnightSave2";
    private static final String PREFS_NAME_3 = "HollowKnightSave3";
    private static final String PREFS_NAME_ACHIEVEMENTS = "HollowKnightAchievements";
    public static final String ACHIEVEMENT_COMPLETION="ACHIEVEMENT_COMPLETION";
    public static final String ACHIEVEMENT_SPEEDRUN="ACHIEVEMENT_SPEEDRUN";
    public static final String ACHIEVEMENT_TRUE_HUNTER="ACHIEVEMENT_TRUE_HUNTER";
    public static final String ACHIEVEMENT_DEFEAT_FALSE_KNIGHT="ACHIEVEMENT_DEFEAT_FALSE_KNIGHT";
    public static final String ACHIEVEMENT_NO_DAMAGE="ACHIEVEMENT_NO_DAMAGE";
    private static final String KEY_POS_X = "posX";
    private static final String KEY_POS_Y = "posY";
    private static final String KEY_HP = "hp";
    private static final String KEY_SOUL = "soul";
    private static final String KEY_TIME= "time";
    private static final String KEY_TOTAL_DAMAGE_TAKEN="totalDamageTaken";
    private static final String KEY_TOTAL_CRYSTALLIZED_KILLED="totalCrystallizedKilled";
    private static final String KEY_TOTAL_MOSQUITOES_KILLED="totalMosquitoesKilled";
    private static final String KEY_TOTAL_HUSK_KILLED="totalHuskKilled";
    private static final String KEY_TOTAL_CRAWLER_KILLED="totalCrawlerKilled";

    private Preferences prefs0;
    private Preferences prefs1;
    private Preferences prefs2;
    private Preferences prefs3;
    private Preferences prefAchievements;

    public SaveManager() {
        prefs0 = Gdx.app.getPreferences(PREFS_NAME_0);
        prefs1 = Gdx.app.getPreferences(PREFS_NAME_1);
        prefs2 = Gdx.app.getPreferences(PREFS_NAME_2);
        prefs3 = Gdx.app.getPreferences(PREFS_NAME_3);
        prefAchievements=Gdx.app.getPreferences(PREFS_NAME_ACHIEVEMENTS);
    }

    public void saveAchievement(String achievement){
        switch (achievement){
            case ACHIEVEMENT_COMPLETION -> prefAchievements.putBoolean(ACHIEVEMENT_COMPLETION,true);
            case ACHIEVEMENT_TRUE_HUNTER -> prefAchievements.putBoolean(ACHIEVEMENT_TRUE_HUNTER,true);
            case ACHIEVEMENT_SPEEDRUN -> prefAchievements.putBoolean(ACHIEVEMENT_SPEEDRUN,true);
            case ACHIEVEMENT_DEFEAT_FALSE_KNIGHT -> prefAchievements.putBoolean(ACHIEVEMENT_DEFEAT_FALSE_KNIGHT,true);
            case ACHIEVEMENT_NO_DAMAGE -> prefAchievements.putBoolean(ACHIEVEMENT_NO_DAMAGE,true);
        }
        prefAchievements.flush();
    }

    public void resetAchievements(){
        prefAchievements.clear();
        prefAchievements.flush();
    }

    public boolean hasAchievement(String name){
        if (prefAchievements.contains(name)){
            return prefAchievements.getBoolean(name);
        }
        return false;
    }

    public void saveGame(int currentSave,float x, float y, int hp, int soul, float time
        , int totalDamageTaken, int crystallized, int mosquito, int husk, int crawler) {
        Preferences prefs;
        switch (currentSave){
            case 0->prefs=prefs0;
            case 1->prefs=prefs1;
            case 2->prefs=prefs2;
            case 3->prefs=prefs3;
            default -> {
                return;
            }
        }
        prefs.putFloat(KEY_POS_X, x);
        prefs.putFloat(KEY_POS_Y, y);
        prefs.putFloat(KEY_TIME, time);
        prefs.putInteger(KEY_HP, hp);
        prefs.putInteger(KEY_SOUL, soul);
        prefs.putInteger(KEY_TOTAL_DAMAGE_TAKEN, totalDamageTaken);
        prefs.putInteger(KEY_TOTAL_CRAWLER_KILLED, crawler);
        prefs.putInteger(KEY_TOTAL_CRYSTALLIZED_KILLED,crystallized);
        prefs.putInteger(KEY_TOTAL_HUSK_KILLED,husk);
        prefs.putInteger(KEY_TOTAL_MOSQUITOES_KILLED,mosquito);
        prefs.flush();
    }

    public boolean hasSave(int save) {
        Preferences prefs;
        switch (save){
            case 0->prefs=prefs0;
            case 1->prefs=prefs1;
            case 2->prefs=prefs2;
            case 3->prefs=prefs3;
            default -> {
                return false;
            }
        }
        return prefs.contains(KEY_POS_X);
    }

    public GameState loadGame(int save) {
        Preferences prefs;
        switch (save){
            case 0->prefs=prefs0;
            case 1->prefs=prefs1;
            case 2->prefs=prefs2;
            case 3->prefs=prefs3;
            default -> {
                return null;
            }
        }
        float x = prefs.getFloat(KEY_POS_X, 0);
        float y = prefs.getFloat(KEY_POS_Y, 0);
        float time = prefs.getFloat(KEY_TIME,0);
        int hp = prefs.getInteger(KEY_HP, Knight.MAX_HP);
        int soul = prefs.getInteger(KEY_SOUL, 0);
        int totalDamageTaken= prefs.getInteger(KEY_TOTAL_DAMAGE_TAKEN, 0);
        int mosquito= prefs.getInteger(KEY_TOTAL_MOSQUITOES_KILLED, 0);
        int crystallized= prefs.getInteger(KEY_TOTAL_CRYSTALLIZED_KILLED, 0);
        int crawler= prefs.getInteger(KEY_TOTAL_CRAWLER_KILLED, 0);
        int husk= prefs.getInteger(KEY_TOTAL_HUSK_KILLED, 0);
        return new GameState(save,x, y, hp, soul,time,totalDamageTaken,husk,crystallized,mosquito,crawler);
    }

    public void clearSave(int save) {
        Preferences prefs;
        switch (save){
            case 0->prefs=prefs0;
            case 1->prefs=prefs1;
            case 2->prefs=prefs2;
            case 3->prefs=prefs3;
            default -> {
                return ;
            }
        }
        prefs.clear();
        prefs.flush();
    }
}
