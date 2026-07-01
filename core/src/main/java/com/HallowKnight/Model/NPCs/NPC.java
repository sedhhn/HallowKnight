package com.HallowKnight.Model.NPCs;

import com.HallowKnight.HallowKnight;
import com.HallowKnight.Model.NPCs.Zote.State.State;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

public class NPC extends Sprite {
    protected Body b2Body;
    protected World world;
    protected State state;
    protected boolean facingRight;

    public NPC(TextureAtlas.AtlasRegion atlasRegion, World world, Vector2 position){
        super(atlasRegion);
        this.world=world;
        facingRight=true;
    }

    protected void defineNPC(Vector2 position){
        BodyDef bodyDef=new BodyDef();
        bodyDef.type= BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(position.x/ HallowKnight.PPM,position.y/HallowKnight.PPM);
        b2Body=world.createBody(bodyDef);
    }

    public void update(float dt){
        state.update(dt);
    }

    public void setState(State state){
        this.state=state;
        state.enter();
    }

    public Body getB2Body(){
        return b2Body;
    }

    public boolean isFacingRight(){
        return facingRight;
    }

    public void setFacingRight(boolean facingRight){
        this.facingRight=facingRight;
    }

    public World getWorld(){
        return world;
    }
}
