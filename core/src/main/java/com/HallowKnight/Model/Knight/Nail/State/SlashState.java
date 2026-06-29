package com.HallowKnight.Model.Knight.Nail.State;

import com.HallowKnight.HallowKnight;
import com.HallowKnight.Model.Knight.Knight;
import com.HallowKnight.Model.Knight.Nail.Nail;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public abstract class SlashState {
    protected Nail nail;
    protected Knight knight;
    protected Animation<TextureRegion> stateAnimation;
    protected Array<TextureAtlas.AtlasRegion> frames;
    protected float stateTime=0;

    public SlashState(Nail nail){
        this.nail=nail;
        this.knight=nail.getKnight();
    }

    public void update(float dt){
        if (nail.getKnight().isFacingRight() && !stateAnimation.getKeyFrame(stateTime).isFlipX()){
            stateAnimation.getKeyFrame(stateTime).flip(true,false);
        } else if(!nail.getKnight().isFacingRight() && stateAnimation.getKeyFrame(stateTime).isFlipX()){
            stateAnimation.getKeyFrame(stateTime).flip(true,false);
        }

        nail.setRegion(stateAnimation.getKeyFrame(stateTime));
        stateTime+=dt;
    }

    public void onContactWithDeadly(){

    }
}
