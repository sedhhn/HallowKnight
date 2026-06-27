package com.HallowKnight.Controller;

import com.HallowKnight.Controller.Managers.GameAssetManager;
import com.HallowKnight.Model.Enemies.GroundEnemy;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import java.util.Comparator;

public class GroundEnemyController {
    GroundEnemy enemy;
    private Array<TextureAtlas.AtlasRegion> frames;
    private Animation<TextureRegion> walkAnimation;
    private float stateTime;
    private boolean facingRight;
    public GroundEnemyController(GroundEnemy enemy){
        facingRight=false;
        this.enemy=enemy;
        stateTime=0;
        frames=GameAssetManager.crawlidAtlas.findRegions("Walk");
        frames.sort(Comparator.comparingInt(a -> a.index));
        walkAnimation=new Animation<>(1/12f,frames, Animation.PlayMode.LOOP);
    }

    public void update(float dt){
        stateTime+=dt;
    }

    public TextureRegion getCurrentFrame(){
        if (facingRight && !walkAnimation.getKeyFrame(stateTime).isFlipX()){
            walkAnimation.getKeyFrame(stateTime).flip(true,false);
        }
        if (!facingRight && walkAnimation.getKeyFrame(stateTime).isFlipX()){
            walkAnimation.getKeyFrame(stateTime).flip(true,false);
        }
        return walkAnimation.getKeyFrame(stateTime);
    }

    public void setFacingRight(boolean facingRight){
        this.facingRight=facingRight;
    }
}
