package com.HallowKnight.Model.Knight;

import com.HallowKnight.Controller.KnightController;
import com.HallowKnight.Controller.Managers.GameAssetManager;
import com.HallowKnight.HallowKnight;
import com.HallowKnight.Model.FixtureType;
import com.HallowKnight.Model.Knight.Nail.Nail;
import com.HallowKnight.Model.Knight.State.IdleState;
import com.HallowKnight.Model.Knight.State.State;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;


public class Knight extends Sprite {
    public static final float MAX_MOVEMENT_SPEED=4f;
    public static final int MAX_HP = 5;
    private static final float INVINCIBILITY_TIME = 1.5f;
    public static final float ATTACK_DURATION=0.3f;

    public World world;
    public Body b2Body;

    private boolean facingRight;

    private State state;

    SurroundSensors surroundSensors;

    private KnightController controller;
    private int hp;
    private boolean invincible;
    private float invincibleTimer;

    public Knight(World world, Vector2 spawnPos){
        super(GameAssetManager.knightIdleAtlas.findRegion("Idle"));
        facingRight=true;
        controller=new KnightController(this);
        state=new IdleState(this);
        surroundSensors=new SurroundSensors();
        this.world=world;
        hp = MAX_HP;
        invincible = false;
        invincibleTimer = 0;
        defineKnight(spawnPos);
        setBounds(0, 0
            ,349/HallowKnight.PPM
            ,186/HallowKnight.PPM);
    }

    public void defineKnight(Vector2 spawnPos){
        BodyDef bodyDef=new BodyDef();
        bodyDef.position.set(spawnPos.x/ HallowKnight.PPM,spawnPos.y/HallowKnight.PPM);
        bodyDef.type= BodyDef.BodyType.DynamicBody;
        b2Body=world.createBody(bodyDef);

        float hx=(getWidth()/17)/HallowKnight.PPM;
        float hy=(getHeight()/4)/HallowKnight.PPM;

        FixtureDef fixtureDef=new FixtureDef();
        PolygonShape shape=new PolygonShape();
        shape.setAsBox(hx, hy);

        fixtureDef.shape=shape;
        Fixture fixture = b2Body.createFixture(fixtureDef);
        fixture.setUserData(FixtureType.KNIGHT);
        b2Body.setUserData(this);

        surroundSensors.createSensors(this.b2Body,hx,hy);
    }

    public void update(float deltaTime){
        state.update(deltaTime);
        setPosition(b2Body.getPosition().x-getWidth()/2f,b2Body.getPosition().y-getHeight()/3.5f);

        if (invincible) {
            invincibleTimer -= deltaTime;
            if (invincibleTimer <= 0) {
                invincible = false;
            }
        }
    }

    public void takeDamage(int damage) {
        if (invincible) return;
        hp -= damage;
        if (hp < 0) hp = 0;
        invincible = true;
        invincibleTimer = INVINCIBILITY_TIME;
    }

    public int getHp() {
        return hp;
    }

    public KnightController getController(){
        return controller;
    }

    public void setFacingRight(boolean facingRight){
        this.facingRight=facingRight;

    }

    public void setState(State state){
        this.state.exit();
        this.state=state;
        state.enter();
    }

    public boolean isTouchingGround(){
        return surroundSensors.bottomSensor > 0;
    }

    public boolean isTouchingWall(){
        return surroundSensors.leftSensor>0 || surroundSensors.rightSensor>0;
    }

    public boolean isFacingRight(){
        return this.facingRight;
    }

    public SurroundSensors getSurroundSensors(){
        return surroundSensors;
    }

}
