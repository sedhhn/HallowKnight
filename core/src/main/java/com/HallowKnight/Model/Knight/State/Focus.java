package com.HallowKnight.Model.Knight.State;

import com.HallowKnight.Controller.Managers.GameAssetManager;
import com.HallowKnight.Model.Knight.Knight;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;

import java.util.Comparator;

public class Focus extends State{
    public Focus(Knight knight) {
        super(knight);
        frames= GameAssetManager.knightAtlas.findRegions("Focus");
        frames.sort(Comparator.comparingInt(a->a.index));
        stateAnimation=new Animation<>(1/10f,frames, Animation.PlayMode.LOOP);
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        knight.decreaseSoul(10*dt);
        if (knight.getSoul()<=0){
            knight.setState(new IdleState(knight));
        }
        if (stateTime>knight.getFocusDuration()){
            knight.setState(new IdleState(knight));
            knight.increaseHp(1);
        }
        handleInputs();
    }

    @Override
    protected void handleInputs() {
        super.handleInputs();
        if (!Gdx.input.isKeyPressed(Input.Keys.A)){
            knight.setState(new IdleState(knight));
        }
    }
}
