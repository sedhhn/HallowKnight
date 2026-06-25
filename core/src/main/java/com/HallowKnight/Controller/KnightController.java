package com.HallowKnight.Controller;

import com.HallowKnight.Controller.Managers.GameAssetManager;
import com.HallowKnight.Model.Knight;
import com.HallowKnight.Model.KnightState;
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
    private Array<TextureAtlas.AtlasRegion> frames;

    private Animation<TextureRegion> knightIdle;
    private Animation<TextureRegion> knightRunning;
    private Animation<TextureRegion> knightJump;

    public KnightController(Knight knight){
        this.knight=knight;
        state=KnightState.IDLE;
        stateTimer=0;
        facingRight=true;

        //Knight Idle Animations
        frames= GameAssetManager.knightIdleAtlas.findRegions("Idle");
        frames.sort(Comparator.comparingInt(a -> a.index));
        knightIdle=new Animation<>(0.1f,frames, Animation.PlayMode.LOOP);

        //Knight Running Animation
        frames=GameAssetManager.knightRunningAtlas.findRegions("Run");
        frames.sort(Comparator.comparingInt(a -> a.index));
        knightRunning=new Animation<>(0.1f,frames, Animation.PlayMode.LOOP);

    }

    public void update(float dt){
        stateTimer+=dt;
    }

    public TextureRegion getCurrentFrame(){
        TextureRegion region=new TextureRegion();
        switch (state){
            case IDLE -> {
                region=knightIdle.getKeyFrame(stateTimer);
                if (facingRight && !region.isFlipX()) {
                    region.flip(true,false);
                }
                else if(!facingRight && region.isFlipX()){
                    region.flip(true,false);
                }
                knight.b2Body.setLinearVelocity(0,knight.b2Body.getLinearVelocity().y);
                return region;
            }
            case RUNNING -> {
                region=knightRunning.getKeyFrame(stateTimer);
                if (facingRight && !region.isFlipX()) {
                    region.flip(true,false);
                }
                else if(!facingRight && region.isFlipX()){
                    region.flip(true,false);
                }
                return region;
            }
            default -> {
                return null;
            }
        }
    }

    public KnightState getState(){
        return state;
    }

    public void setState(KnightState state){
        this.state=state;
    }

    public void setFacingRight(boolean facingRight){
        this.facingRight=facingRight;
    }
}
