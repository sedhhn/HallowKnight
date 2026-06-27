package com.HallowKnight.Model;

import com.HallowKnight.Controller.KnightController;
import com.HallowKnight.Controller.Managers.GameAssetManager;
import com.HallowKnight.HallowKnight;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;


public class Knight extends Sprite {
    public static final float MAX_MOVEMENT_SPEED=4f;
    public static final int MAX_HP = 5;
    private static final float INVINCIBILITY_TIME = 1.5f;

    public World world;
    public Body b2Body;

    private KnightController controller;
    private int hp;
    private boolean invincible;
    private float invincibleTimer;

    private Animation knightWalk;
    private Animation knightJump;

    public Knight(World world){
        super(GameAssetManager.knightIdleAtlas.findRegion("Idle"));
        controller=new KnightController(this);
        this.world=world;
        hp = MAX_HP;
        invincible = false;
        invincibleTimer = 0;
        defineKnight();
        setBounds(0, 0
            ,349/HallowKnight.PPM
            ,186/HallowKnight.PPM);
    }

    public void defineKnight(){
        BodyDef bodyDef=new BodyDef();
        bodyDef.position.set(100/ HallowKnight.PPM,700/HallowKnight.PPM);
        bodyDef.type= BodyDef.BodyType.DynamicBody;
        b2Body=world.createBody(bodyDef);

        FixtureDef fixtureDef=new FixtureDef();
        PolygonShape shape=new PolygonShape();
        shape.setAsBox((getWidth()/17)/HallowKnight.PPM,(getHeight()/4)/HallowKnight.PPM);

        fixtureDef.shape=shape;
        Fixture fixture = b2Body.createFixture(fixtureDef);
        fixture.setUserData(FixtureType.KNIGHT);
    }

    public void update(float deltaTime){
        controller.update(deltaTime);
        setRegion(controller.getCurrentFrame());
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

    public boolean isInvincible() {
        return invincible;
    }

    public KnightController getController(){
        return controller;
    }
}
