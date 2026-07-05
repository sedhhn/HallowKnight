package com.HallowKnight.Model.Effects;

import com.HallowKnight.HallowKnight;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;

public abstract class Effect extends Sprite {
    protected boolean over;
    protected Body b2Body;
    protected Array<TextureAtlas.AtlasRegion> frames;
    protected Animation<TextureRegion> animation;
    protected float stateTime;
    protected boolean rightDirection;
    public Effect(TextureAtlas.AtlasRegion atlasRegion){
        super(atlasRegion);
        over=false;
        stateTime=0;
        setBounds(0,0,getWidth()/ HallowKnight.PPM,getHeight()/HallowKnight.PPM);
    }

    public void update(float dt){
        if (rightDirection && animation.getKeyFrame(stateTime).isFlipX()){
            animation.getKeyFrame(stateTime).flip(true,false);
        } else if(!rightDirection && !animation.getKeyFrame(stateTime).isFlipX()){
            animation.getKeyFrame(stateTime).flip(true,false);
        }
        setRegion(animation.getKeyFrame(stateTime));
        stateTime+=dt;
    }

    public boolean isOver(){
        return over;
    }

    public Body getB2Body(){
        return b2Body;
    }

    public void setOver(boolean over){
        this.over=over;
    }
}
