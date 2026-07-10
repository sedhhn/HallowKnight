package com.HallowKnight.Model;

public class GameState {
    public float x, y, time;
    public int save, hp, soul, totalDamageTaken, husk, crystallized, mosquito, crawler;
    public GameState(int save,float x, float y, int hp, int soul, float time, int totalDamageTaken
    , int husk, int crystallized, int mosquito, int crawler) {
        this.save=save; this.x = x; this.y = y; this.hp = hp; this.soul = soul; this.time=time;
        this.totalDamageTaken=totalDamageTaken;
        this.husk=husk; this.crystallized=crystallized; this.mosquito=mosquito; this.crawler=crawler;
    }
}
