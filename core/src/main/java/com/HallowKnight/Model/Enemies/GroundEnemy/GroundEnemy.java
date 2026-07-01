package com.HallowKnight.Model.Enemies.GroundEnemy;

import com.HallowKnight.Controller.ContactController;
import com.HallowKnight.Controller.GroundEnemyController;
import com.HallowKnight.Controller.Managers.GameAssetManager;
import com.HallowKnight.HallowKnight;
import com.HallowKnight.Model.Enemies.Enemy;
import com.HallowKnight.Model.Enemies.GroundEnemy.State.Walking;
import com.HallowKnight.Model.FixtureType;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class GroundEnemy extends Enemy {
    SurroundSensors surroundSensors;

    public GroundEnemy(TextureAtlas.AtlasRegion atlasRegion, World world, Vector2 position){
        super(atlasRegion, world, position);
        defineEnemy(position);
        this.state=new Walking(this);
        ContactManager contactManager=new ContactManager(this);
        ContactController.getInstance().contactListeners.add(contactManager);
        setBounds(0, 0
            ,getWidth()/1.3f/HallowKnight.PPM
            ,getHeight()/1.3f/HallowKnight.PPM);
    }

    @Override
    protected void defineEnemy(Vector2 position){
        super.defineEnemy(position);

        float hx=getWidth()/5f/ HallowKnight.PPM;
        float hy=getHeight()/3.75f/HallowKnight.PPM;

        FixtureDef fixtureDef=new FixtureDef();
        PolygonShape shape=new PolygonShape();
        shape.setAsBox(hx,hy);
        fixtureDef.shape=shape;

        b2Body.createFixture(fixtureDef).setUserData(FixtureType.ENEMY);
        b2Body.setUserData(this);
        surroundSensors=new SurroundSensors();
        surroundSensors.createSensors(b2Body,hx,hy);
    }

    public void update(float dt){
        b2Body.setLinearVelocity(movementSpeed,0);
        setPosition(b2Body.getPosition().x-getWidth()/2f,b2Body.getPosition().y-getHeight()/3.1f);
        state.update(dt);
    }

    public SurroundSensors getSurroundSensors(){
        return surroundSensors;
    }
}
