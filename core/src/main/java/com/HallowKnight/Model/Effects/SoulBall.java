package com.HallowKnight.Model.Effects;

import com.HallowKnight.Controller.Managers.GameAssetManager;
import com.HallowKnight.HallowKnight;
import com.HallowKnight.Model.FixtureType;
import com.HallowKnight.Model.Knight.Knight;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.*;

import java.util.Comparator;

public class SoulBall extends Effect{
    public static final float SOUL_COST=30f;
    public static final float MAX_DURATION=2f;
    public static final float SPEED=8f;
    Knight knight;
    World world;
    public SoulBall(TextureAtlas.AtlasRegion atlasRegion, Knight knight) {
        super(atlasRegion);
        this.knight=knight;
        this.world=knight.getWorld();
        frames= GameAssetManager.soulBall.findRegions("SoulBall");
        frames.sort(Comparator.comparingInt(a->a.index));
        animation=new Animation<>(1/10f,frames);
        rightDirection=knight.isFacingRight();
        defineSoulBall();
    }

    public void defineSoulBall(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(
            knight.b2Body.getPosition().x+(knight.isFacingRight()?50:-50)/HallowKnight.PPM
            , knight.b2Body.getPosition().y+15/ HallowKnight.PPM
        );
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.gravityScale=0;
        b2Body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(70 / HallowKnight.PPM, 50 / HallowKnight.PPM);
        fixtureDef.shape = shape;
        fixtureDef.isSensor = true;

        Fixture fixture = b2Body.createFixture(fixtureDef);
        fixture.setUserData(FixtureType.SOUL_BALL);
        b2Body.setUserData(this);
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        setPosition(b2Body.getPosition().x-getWidth()/2f,b2Body.getPosition().y-getHeight()/2.5f);
        b2Body.setLinearVelocity(rightDirection?SPEED:-SPEED,0);
        if (stateTime>MAX_DURATION){
            over=true;
        }
    }
}
