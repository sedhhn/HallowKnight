package com.HallowKnight.Model.Enemies.GroundEnemy;

import com.HallowKnight.HallowKnight;
import com.HallowKnight.Model.FixtureType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class SurroundSensors{
    public int rightSensor;
    public int bottomRightSensor;
    public int leftSensor;
    public int bottomLeftSensor;
    public int bottomSensor;

    public SurroundSensors(){
        rightSensor = 0;
        bottomRightSensor=0;
        leftSensor = 0;
        bottomLeftSensor=0;
        bottomSensor = 0;
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
        float cornerSensorWidth = 6f/HallowKnight.PPM;
        float cornerSensorHeight=6f/HallowKnight.PPM;


        shape.setAsBox(groundSensorWidth, groundSensorHeight, new Vector2(0, -hy -groundSensorHeight), 0);
        body.createFixture(fdef).setUserData(FixtureType.ENEMY_BOTTOM);

        shape.setAsBox(sideSensorWidth, sideSensorHeight, new Vector2(hx + sideSensorWidth, 0), 0);
        body.createFixture(fdef).setUserData(FixtureType.ENEMY_RIGHT);

        shape.setAsBox(sideSensorWidth, sideSensorHeight, new Vector2(-hx - sideSensorWidth, 0), 0);
        body.createFixture(fdef).setUserData(FixtureType.ENEMY_LEFT);

        shape.setAsBox(cornerSensorWidth, cornerSensorHeight, new Vector2(-hx - cornerSensorWidth, -hy-cornerSensorHeight), 0);
        body.createFixture(fdef).setUserData(FixtureType.ENEMY_BOTTOM_LEFT);

        shape.setAsBox(cornerSensorWidth, cornerSensorHeight, new Vector2(hx + cornerSensorWidth, -hy-cornerSensorHeight), 0);
        body.createFixture(fdef).setUserData(FixtureType.ENEMY_BOTTOM_RIGHT);

        shape.dispose();

    }
}
