package com.HallowKnight.Model.FalseKnight;

import com.HallowKnight.HallowKnight;
import com.HallowKnight.Model.FixtureType;
import com.badlogic.gdx.physics.box2d.*;

public class Mace {
    private Body b2Body;
    private World world;
    private FalseKnight falseKnight;

    public Mace(World world, FalseKnight falseKnight) {
        this.world = world;
        this.falseKnight = falseKnight;
        defineMace();
    }

    private void defineMace() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(
            falseKnight.b2Body.getPosition().x + (falseKnight.isFacingRight() ? 300f : -300f) / HallowKnight.PPM
            , falseKnight.b2Body.getPosition().y-100f/HallowKnight.PPM
        );
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        b2Body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(70 / HallowKnight.PPM, 70 / HallowKnight.PPM);
        fixtureDef.shape = shape;
        fixtureDef.isSensor = true;

        Fixture fixture = b2Body.createFixture(fixtureDef);
        fixture.setUserData(FixtureType.MACE);
        b2Body.setUserData(this);
    }

    public void update(float dt) {
        b2Body.setTransform(falseKnight.b2Body.getPosition().x
                + (falseKnight.isFacingRight() ? 300f : -300f) / HallowKnight.PPM
            , falseKnight.b2Body.getPosition().y-100f/HallowKnight.PPM
            , 0);
    }

    public void destroy() {
        world.destroyBody(b2Body);
    }
}
