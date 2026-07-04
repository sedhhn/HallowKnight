package com.HallowKnight.Model.FalseKnight.State;

import com.HallowKnight.Model.FalseKnight.FalseKnight;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public abstract class State {
    FalseKnight falseKnight;
    Animation<TextureRegion> stateAnimation;
    Array<TextureAtlas.AtlasRegion> frames;
    float stateTime=0;
    public State(FalseKnight falseKnight){
        this.falseKnight=falseKnight;
    }

    public void enter(){

    }

    public void update(float dt){
        if (falseKnight.isFacingRight() && !stateAnimation.getKeyFrame(stateTime).isFlipX()){
            stateAnimation.getKeyFrame(stateTime).flip(true,false);
        } else if(!falseKnight.isFacingRight() && stateAnimation.getKeyFrame(stateTime).isFlipX()){
            stateAnimation.getKeyFrame(stateTime).flip(true,false);
        }
        falseKnight.setRegion(stateAnimation.getKeyFrame(stateTime));
        stateTime+=dt;
    }

    public void exit(){

    }

    protected void handleInputs(){

    }
}
