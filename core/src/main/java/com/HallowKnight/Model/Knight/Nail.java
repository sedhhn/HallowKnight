package com.HallowKnight.Model.Knight;

import com.HallowKnight.HallowKnight;
import com.HallowKnight.Model.FixtureType;
import com.badlogic.gdx.physics.box2d.*;

public class Nail {
    private Body b2Body;
    private World world;
    private Knight knight;

    public Nail(World world, Knight knight){
        this.world=world;
        this.knight=knight;
        defineNail();
    }

    public void defineNail(){
        BodyDef bodyDef=new BodyDef();
        bodyDef.position.set(knight.b2Body.getPosition().x
            + (knight.isFacingRight() ? 30 : -30) / HallowKnight.PPM
            , knight.b2Body.getPosition().y);
        bodyDef.type= BodyDef.BodyType.KinematicBody;
        b2Body=world.createBody(bodyDef);

        FixtureDef fixtureDef=new FixtureDef();
        PolygonShape shape=new PolygonShape();
        shape.setAsBox(20/ HallowKnight.PPM, 20/HallowKnight.PPM);
        fixtureDef.shape=shape;
        fixtureDef.isSensor=true;

        Fixture fixture = b2Body.createFixture(fixtureDef);
        fixture.setUserData(FixtureType.NAIL);
    }

    public void update(float dt){
        b2Body.setTransform(knight.b2Body.getPosition().x
            + (knight.isFacingRight() ? 30 : -30) / HallowKnight.PPM
            , knight.b2Body.getPosition().y, 0);
    }

    public void destroy(){
        world.destroyBody(b2Body);
    }
}
