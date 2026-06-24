package com.HallowKnight.Model;

import com.HallowKnight.HallowKnight;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;

public class Nail extends Sprite {
    private Body b2Body;
    private World world;
    private Knight knight;

    public Nail(World world,Knight knight){
        this.world=world;
        this.knight=knight;
    }

    public void defineNail(){
        BodyDef b2BodyDef=new BodyDef();
        b2BodyDef.position.set(knight.b2Body.getPosition());
        b2BodyDef.type= BodyDef.BodyType.DynamicBody;
        b2Body=world.createBody(b2BodyDef);

        FixtureDef fixtureDef=new FixtureDef();
        PolygonShape shape=new PolygonShape();
        shape.setAsBox(10/ HallowKnight.PPM,5/HallowKnight.PPM);
        fixtureDef.shape=shape;
        b2Body.createFixture(fixtureDef);
    }

    public void update(float dt){
    }
}
