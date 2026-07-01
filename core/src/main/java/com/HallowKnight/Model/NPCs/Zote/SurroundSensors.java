package com.HallowKnight.Model.NPCs.Zote;

import com.HallowKnight.HallowKnight;
import com.HallowKnight.Model.FixtureType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class SurroundSensors {
    public int radarSensor;

    public SurroundSensors(){
        radarSensor=0;
    }

    public void createSensors(Body body , float hx , float hy){
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        fdef.friction = 0f;
        fdef.isSensor = true;
        fdef.shape = shape;

        float radarWidth= 50f/HallowKnight.PPM;
        float radarHeight=50f/HallowKnight.PPM;


        shape.setAsBox(radarWidth, radarHeight, new Vector2(0, 0), 0);
        body.createFixture(fdef).setUserData(FixtureType.ENEMY_BOTTOM);

        shape.dispose();

    }
}
