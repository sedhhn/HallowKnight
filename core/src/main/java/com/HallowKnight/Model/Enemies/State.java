package com.HallowKnight.Model.Enemies;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public abstract class State {
    protected Enemy enemy;

    protected Array<TextureAtlas.AtlasRegion> frames;
    protected Animation<TextureRegion> stateAnimation;
    protected float stateTime=0f;

    public State(Enemy enemy){
        this.enemy=enemy;
    }

    public void enter(){

    }

    public void update(float dt){
        if (enemy.facingRight && !stateAnimation.getKeyFrame(stateTime).isFlipX()){
            stateAnimation.getKeyFrame(stateTime).flip(true,false);
        } else if(!enemy.facingRight && stateAnimation.getKeyFrame(stateTime).isFlipX()){
            stateAnimation.getKeyFrame(stateTime).flip(true,false);
        }
        enemy.setRegion(stateAnimation.getKeyFrame(stateTime));
        stateTime+=dt;
    }
}
