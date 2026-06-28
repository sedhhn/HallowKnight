package com.HallowKnight.Model.Knight.Nail;

import com.HallowKnight.Controller.Managers.GameAssetManager;
import com.HallowKnight.HallowKnight;
import com.HallowKnight.Model.FixtureType;
import com.HallowKnight.Model.Knight.Knight;
import com.HallowKnight.Model.Knight.Nail.State.NormalSlash;
import com.HallowKnight.Model.Knight.Nail.State.SlashState;
import com.HallowKnight.Model.Knight.Nail.State.SlashStates;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;

public class Nail extends Sprite {
    private Body b2Body;
    private World world;
    private Knight knight;
    private SlashState state;

    public Nail(World world, Knight knight, SlashStates state){
        super(GameAssetManager.knightSlashEffect.findRegion("SlashEffect"));
        this.world=world;
        this.knight=knight;
        defineNail();
        switch (state){
            case NORMAL -> this.state=new NormalSlash(this);
        }
    }

    public void defineNail(){
        BodyDef bodyDef=new BodyDef();
        bodyDef.position.set(knight.b2Body.getPosition().x
            + (knight.isFacingRight() ? 50 : -50) / HallowKnight.PPM
            , knight.b2Body.getPosition().y);
        bodyDef.type= BodyDef.BodyType.KinematicBody;
        b2Body=world.createBody(bodyDef);

        FixtureDef fixtureDef=new FixtureDef();
        PolygonShape shape=new PolygonShape();
        shape.setAsBox(70/ HallowKnight.PPM, 30/HallowKnight.PPM);
        fixtureDef.shape=shape;
        fixtureDef.isSensor=true;

        Fixture fixture = b2Body.createFixture(fixtureDef);
        fixture.setUserData(FixtureType.NAIL);
    }

    public void update(float dt){
        state.update(dt);
        setPosition(b2Body.getPosition().x-getWidth()/2,b2Body.getPosition().y-getHeight()/3.5f);
        HallowKnight.hallowKnight.getBatch().begin();
        draw(HallowKnight.hallowKnight.getBatch());
        HallowKnight.hallowKnight.getBatch().end();
    }

    public void destroy(){
        world.destroyBody(b2Body);
    }

    public Knight getKnight(){
        return knight;
    }

    public Body getB2Body(){
        return b2Body;
    }
}
