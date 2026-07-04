package com.HallowKnight.Model;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.Body;

public abstract class Effect extends Sprite {
    protected boolean over;
    protected Body b2Body;
    public Effect(TextureAtlas.AtlasRegion atlasRegion){
        super(atlasRegion);
        over=false;
    }

    public void update(float dt){

    }

    public boolean isOver(){
        return over;
    }

    public Body getB2Body(){
        return b2Body;
    }
}
