package com.HallowKnight.Model.Knight.State;

import com.HallowKnight.Controller.Managers.GameAssetManager;
import com.HallowKnight.Controller.Managers.ScreenManager;
import com.HallowKnight.HallowKnight;
import com.HallowKnight.Model.GameState;
import com.HallowKnight.Model.Knight.Knight;
import com.HallowKnight.View.GameScreen;
import com.HallowKnight.View.Modals.PauseMenu;
import com.badlogic.gdx.graphics.g2d.Animation;

import java.util.Comparator;

public class Death extends State{
    public Death(Knight knight) {
        super(knight);
        frames= GameAssetManager.knightAtlas.findRegions("Death");
        frames.sort(Comparator.comparingInt(a->a.index));
        stateAnimation=new Animation<>(1/9f,frames);
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        if (stateTime>stateAnimation.getAnimationDuration()){
            knight.getGameScreen().getPauseMenu().saveGame(0,0);
            GameState gameState=knight.getGameScreen().getGameState();
            gameState.x=0;
            gameState.y=0;
            GameScreen.resetGameScreen();
            ScreenManager.getInstance().setScreen(GameScreen.getInstance(HallowKnight.hallowKnight,gameState));
        }
    }
}
