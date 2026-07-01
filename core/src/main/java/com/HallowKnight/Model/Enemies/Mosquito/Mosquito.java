package com.HallowKnight.Model.Enemies.Mosquito;

import com.HallowKnight.Controller.ContactController;
import com.HallowKnight.HallowKnight;
import com.HallowKnight.Model.Enemies.Enemy;
import com.HallowKnight.Model.Enemies.Mosquito.State.Idle;
import com.HallowKnight.Model.FixtureType;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Mosquito extends Enemy {
    public static final float ATTACK_ANTICIPATE_DURATION=1f;
    public static final float MAX_ATTACK_DURATION=1f;
    public static final float ATTACK_MOVEMENT_SPEED=7f;
    private SurroundSensors surroundSensors;
    private Vector2 targetPos;
    public Mosquito(TextureAtlas.AtlasRegion atlasRegion, World world, Vector2 position) {
        super(atlasRegion, world, position);
        defineEnemy(position);
        ContactManager contactManager=new ContactManager(this);
        ContactController.getInstance().contactListeners.add(contactManager);
        this.state=new Idle(this);
        setBounds(0, 0
            ,getWidth()/HallowKnight.PPM
            ,getHeight()/HallowKnight.PPM);
        targetPos=new Vector2(position.x,position.y);
    }

    @Override
    protected void defineEnemy(Vector2 position) {
        BodyDef bodyDef=new BodyDef();
        bodyDef.type= BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(position.x/ HallowKnight.PPM,position.y/HallowKnight.PPM);
        bodyDef.gravityScale=0;
        b2Body=world.createBody(bodyDef);

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

    public void setTargetPos(Vector2 targetPos){
        this.targetPos=targetPos;
    }

    public Vector2 getTargetPos(){
        return targetPos;
    }
}
