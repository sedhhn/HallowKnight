package com.HallowKnight.Model.Enemies.HuskHornhead;

import com.HallowKnight.Controller.ContactController;
import com.HallowKnight.HallowKnight;
import com.HallowKnight.Model.Enemies.Enemy;
import com.HallowKnight.Model.Enemies.HuskHornhead.State.Walking;
import com.HallowKnight.Model.FixtureType;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class HuskHornhead extends Enemy {
    public static final float walkTime=5f;
    public static final float idleTime=2.5f;
    public static final float walkSpeed=1.25f;
    public static final float runSpeed=3.5f;

    private SurroundSensors surroundSensors;

    public HuskHornhead(TextureAtlas.AtlasRegion atlasRegion, World world, Vector2 position) {
        super(atlasRegion, world, position);
        this.state=new Walking(this);
        defineEnemy(position);
        ContactManager contactManager=new ContactManager(this);
        ContactController.getInstance().contactListeners.add(contactManager);
        setBounds(0, 0
            ,getWidth()/HallowKnight.PPM
            ,getHeight()/HallowKnight.PPM);
    }

    @Override
    protected void defineEnemy(Vector2 position) {
        super.defineEnemy(position);

        float hx=getWidth()/6f/ HallowKnight.PPM;
        float hy=getHeight()/3f/HallowKnight.PPM;

        FixtureDef fixtureDef=new FixtureDef();
        PolygonShape shape=new PolygonShape();
        shape.setAsBox(hx,hy);
        fixtureDef.shape=shape;

        b2Body.createFixture(fixtureDef).setUserData(FixtureType.ENEMY);
        b2Body.setUserData(this);
        surroundSensors=new SurroundSensors();
        surroundSensors.createSensors(b2Body,hx,hy);
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        setPosition(b2Body.getPosition().x-getWidth()/2f,b2Body.getPosition().y-getHeight()/2.5f);
    }

    public SurroundSensors getSurroundSensors(){
        return surroundSensors;
    }
}
