package com.HallowKnight.Model;

import com.HallowKnight.Controller.KnightController;
import com.HallowKnight.Controller.Managers.GameAssetManager;
import com.HallowKnight.HallowKnight;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;


public class Knight extends Sprite {
    public static final float MAX_MOVEMENT_SPEED=4f;
    public World world;
    public Body b2Body;

    private KnightController controller;


    private Animation knightWalk;
    private Animation knightJump;

    public Knight (World world){
        super(GameAssetManager.knightIdleAtlas.findRegion("Idle"));
        controller=new KnightController();
        this.world=world;
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
        b2Body.createFixture(fixtureDef);
    }

    public void update(float deltaTime){
        controller.update(deltaTime);
        setRegion(controller.getCurrentFrame());
        setPosition(b2Body.getPosition().x-getWidth()/2f,b2Body.getPosition().y-getHeight()/3.5f);
    }

    public KnightController getController(){
        return controller;
    }
}
