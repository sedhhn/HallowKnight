package com.HallowKnight.Model.Effects;

import com.HallowKnight.Controller.Managers.GameAssetManager;
import com.HallowKnight.HallowKnight;
import com.HallowKnight.Model.FixtureType;
import com.HallowKnight.Model.Knight.Knight;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.*;

import java.util.Comparator;

public class SoulScream extends Effect {
    public static final float SOUL_COST=30f;
    public static final float DAMAGE=6;
    Knight knight;
    World world;
    public SoulScream(TextureAtlas.AtlasRegion atlasRegion, Knight knight) {
        super(atlasRegion);
        this.knight=knight;
        this.world=knight.getWorld();
        frames= GameAssetManager.soulScream.findRegions("SoulScream");
        frames.sort(Comparator.comparingInt(a->a.index));
        animation=new Animation<>(1/40f,frames);
        defineSoulScream();
    }

    private void defineSoulScream(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(
            knight.b2Body.getPosition().x
            , knight.b2Body.getPosition().y+100/HallowKnight.PPM
        );
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.gravityScale=0;
        b2Body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(100 / HallowKnight.PPM, 150 / HallowKnight.PPM);
        fixtureDef.shape = shape;
        fixtureDef.isSensor = true;

        Fixture fixture = b2Body.createFixture(fixtureDef);
        fixture.setUserData(FixtureType.SOUL_SCREAM);
        b2Body.setUserData(this);
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        setPosition(b2Body.getPosition().x-getWidth()/2f,b2Body.getPosition().y-getHeight()/3.5f);
        if (stateTime>animation.getAnimationDuration()){
            over=true;
        }
    }
}
