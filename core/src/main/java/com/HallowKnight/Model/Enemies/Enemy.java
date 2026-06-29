package com.HallowKnight.Model.Enemies;

import com.HallowKnight.HallowKnight;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

public abstract class Enemy extends Sprite {
    protected Body b2Body;
    protected World world;
    protected boolean dead;
    protected float movementSpeed=1f;
    protected float hp;
    protected State state;
    protected boolean facingRight;

    public Enemy(TextureAtlas.AtlasRegion atlasRegion, World world, Vector2 position){
        super(atlasRegion);
        this.world=world;
        dead = false;
        facingRight=true;
    }

    protected void defineEnemy(Vector2 position){
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

    public float getMovementSpeed(){
        return movementSpeed;
    }

    public Body getB2Body(){
        return b2Body;
    }

    public boolean isDead(){
        return dead;
    }

    public void takeDamage() {
        dead = true;
    }

    public boolean isFacingRight(){
        return facingRight;
    }

    public void setFacingRight(boolean facingRight){
        this.facingRight=facingRight;
    }

    public void setMovementSpeed(float movementSpeed){
        this.movementSpeed=movementSpeed;
    }
}
