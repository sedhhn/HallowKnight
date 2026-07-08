package com.HallowKnight.Model.FalseKnight;

import com.HallowKnight.HallowKnight;
import com.HallowKnight.Model.FixtureType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class SurroundSensors {
    public int rightSensor;
    public int leftSensor;
    public int bottomSensor;
    public int radarSensor;

    public SurroundSensors(){
        rightSensor = 0;
        leftSensor = 0;
        bottomSensor = 0;
        radarSensor=0;
    }

    public void createSensors(Body body , float hx , float hy){
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        fdef.friction = 0f;
        fdef.isSensor = true;
        fdef.shape = shape;

        float sideSensorWidth = 4f / HallowKnight.PPM;
        float sideSensorHeight = hy * 0.8f;
        float groundSensorWidth = hx * 0.8f;
        float groundSensorHeight = 4f / HallowKnight.PPM;

        float radarSensorWidth=800f/HallowKnight.PPM;


        shape.setAsBox(groundSensorWidth, groundSensorHeight, new Vector2(0, -hy -groundSensorHeight), 0);
        body.createFixture(fdef).setUserData(FixtureType.BOSS_BOTTOM);

        shape.setAsBox(sideSensorWidth, sideSensorHeight, new Vector2(hx + sideSensorWidth, 0), 0);
        body.createFixture(fdef).setUserData(FixtureType.BOSS_RIGHT);

        shape.setAsBox(sideSensorWidth, sideSensorHeight, new Vector2(-hx - sideSensorWidth, 0), 0);
        body.createFixture(fdef).setUserData(FixtureType.BOSS_LEFT);

        shape.setAsBox(radarSensorWidth, sideSensorHeight, new Vector2(0, 0), 0);
        body.createFixture(fdef).setUserData(FixtureType.BOSS_RADAR);

        shape.dispose();

    }
}
