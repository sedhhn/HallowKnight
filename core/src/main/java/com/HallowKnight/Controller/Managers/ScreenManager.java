package com.HallowKnight.Controller.Managers;

import com.HallowKnight.HallowKnight;
import com.badlogic.gdx.Screen;

public class ScreenManager {
    private static ScreenManager instance;
    private HallowKnight game;

    public static ScreenManager getInstance(){
        if (instance==null){
            instance=new ScreenManager();
        }
        return instance;
    }

    public void initialize(HallowKnight game){
        this.game=game;
    }

    public void setScreen(Screen screen){
        game.setScreen(screen);
    }

}
