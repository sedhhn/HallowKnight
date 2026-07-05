package com.HallowKnight.Model.FalseKnight;

import com.HallowKnight.Controller.Managers.GameAssetManager;
import com.HallowKnight.HallowKnight;
import com.HallowKnight.Model.Effects.Effect;
import com.HallowKnight.Model.FixtureType;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;

import java.util.Comparator;

public class Shockwave extends Effect {
    public static final float SPEED=7f;
    public static final float MAX_DURATION=6f;
    private World world;
    private FalseKnight falseKnight;

    public Shockwave(World world, FalseKnight falseKnight, boolean movingRight){
        super(GameAssetManager.shockwaveAtlas.findRegion("Shockwave"));
        this.world = world;
        this.falseKnight = falseKnight;
        this.rightDirection=movingRight;

        frames=GameAssetManager.shockwaveAtlas.findRegions("Shockwave");
        frames.sort(Comparator.comparingInt(a->a.index));
        animation=new Animation<>(1/9f,frames, Animation.PlayMode.LOOP);

        defineShockwave();

    }

    private void defineShockwave(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(
            falseKnight.b2Body.getPosition().x + (falseKnight.isFacingRight() ? 300f : -300f) / HallowKnight.PPM
            , falseKnight.b2Body.getPosition().y-100f/HallowKnight.PPM
        );
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.gravityScale=0;
        b2Body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(100 / HallowKnight.PPM, 40 / HallowKnight.PPM);
        fixtureDef.shape = shape;
        fixtureDef.isSensor = true;

        Fixture fixture = b2Body.createFixture(fixtureDef);
        fixture.setUserData(FixtureType.SHOCKWAVE);
        b2Body.setUserData(this);
    }

    @Override
    public void update(float dt){
        super.update(dt);
        b2Body.setLinearVelocity(rightDirection?SPEED:-SPEED,0);
        setPosition(b2Body.getPosition().x-getWidth()/2f,b2Body.getPosition().y-getHeight()/3.5f);
        if (stateTime>MAX_DURATION){
            over=true;
        }
    }
}
