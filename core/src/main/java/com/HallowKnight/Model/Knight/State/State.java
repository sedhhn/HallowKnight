package com.HallowKnight.Model.Knight.State;

import com.HallowKnight.Model.Knight.Knight;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public abstract class State {
    Knight knight;
    Animation<TextureRegion> stateAnimation;
    Array<TextureAtlas.AtlasRegion> frames;
    float stateTime=0;

    public State(Knight knight){
        this.knight=knight;
    }

    public void enter(){

    }

    public void update(float dt){
        if (knight.isFacingRight() && !stateAnimation.getKeyFrame(stateTime).isFlipX()){
            stateAnimation.getKeyFrame(stateTime).flip(true,false);
        } else if(!knight.isFacingRight() && stateAnimation.getKeyFrame(stateTime).isFlipX()){
            stateAnimation.getKeyFrame(stateTime).flip(true,false);
        }
        knight.setRegion(stateAnimation.getKeyFrame(stateTime));
        stateTime+=dt;
    }

    public void exit(){

    }

    protected void handleInputs(){

    }
}
