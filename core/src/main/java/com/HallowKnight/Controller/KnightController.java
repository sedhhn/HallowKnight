package com.HallowKnight.Controller;

import com.HallowKnight.Controller.Managers.GameAssetManager;
import com.HallowKnight.Model.Knight.Knight;
import com.HallowKnight.Model.Knight.KnightState;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import java.util.Comparator;

public class KnightController {
    private Knight knight;
    private KnightState state;
    private float stateTimer;
    private boolean facingRight;


    public KnightController(Knight knight){
        this.knight=knight;
        state=KnightState.IDLE;
        stateTimer=0;
        facingRight=true;
    }

    public void update(float dt){

    }

    private void handleFlip(TextureRegion region) {
        if (facingRight && !region.isFlipX()) {
            region.flip(true, false);
        } else if (!facingRight && region.isFlipX()) {
            region.flip(true, false);
        }
    }



    public KnightState getState(){
        return state;
    }

    public void setFacingRight(boolean facingRight){
        this.facingRight=facingRight;
    }

    public boolean isFacingRight(){
        return facingRight;
    }
}
