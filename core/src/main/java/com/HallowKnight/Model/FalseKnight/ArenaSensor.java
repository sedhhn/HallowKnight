package com.HallowKnight.Model.FalseKnight;

import com.HallowKnight.HallowKnight;
import com.HallowKnight.Model.FixtureType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class ArenaSensor {
    public int radar;

    public ArenaSensor(Body falseKnight, World world){
        BodyDef bodyDef=new BodyDef();
        bodyDef.type= BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(falseKnight.getPosition());
        bodyDef.gravityScale=0;

        Body sensorBody=world.createBody(bodyDef);
        radar=0;
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        fdef.friction = 0f;
        fdef.isSensor = true;
        fdef.shape = shape;

        float width=740/ HallowKnight.PPM;
        float height=400/HallowKnight.PPM;

        shape.setAsBox(width,height,new Vector2(-10/HallowKnight.PPM,300/HallowKnight.PPM),0);
        sensorBody.createFixture(fdef).setUserData(FixtureType.BOSS_ARENA_RADAR);

        shape.dispose();
    }
}
