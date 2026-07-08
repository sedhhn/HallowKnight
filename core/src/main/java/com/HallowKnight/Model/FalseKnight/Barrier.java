package com.HallowKnight.Model.FalseKnight;

import com.HallowKnight.Controller.Managers.GameAssetManager;
import com.HallowKnight.HallowKnight;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Barrier extends Sprite {
    private Body barrier;
    World world;
    public Barrier(Vector2 spawnPos, World world){
        super(GameAssetManager.bossArenaBarrier);
        setBounds(0,0,getWidth()/HallowKnight.PPM,getHeight()/HallowKnight.PPM);
        setRegion(GameAssetManager.bossArenaBarrier);
        this.world=world;
        defineBarrier(spawnPos);
        setPosition(barrier.getPosition().x-getWidth()/2f,barrier.getPosition().y-getHeight()/8f);
    }

    private void defineBarrier(Vector2 spawnPos){

        BodyDef bodyDef=new BodyDef();
        bodyDef.type= BodyDef.BodyType.StaticBody;
        bodyDef.position.set(spawnPos.x/HallowKnight.PPM,spawnPos.y/HallowKnight.PPM);
        bodyDef.gravityScale=0;
        barrier=world.createBody(bodyDef);

        FixtureDef fixtureDef=new FixtureDef();
        PolygonShape shape=new PolygonShape();
        shape.setAsBox(63/ HallowKnight.PPM,90/HallowKnight.PPM,new Vector2(0,58/HallowKnight.PPM),0);
        fixtureDef.shape=shape;
        barrier.createFixture(fixtureDef);
    }
}
