package com.HallowKnight.Model.Enemies;

import com.HallowKnight.Controller.GroundEnemyController;
import com.HallowKnight.Controller.Managers.GameAssetManager;
import com.HallowKnight.HallowKnight;
import com.HallowKnight.Model.FixtureType;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class GroundEnemy extends Enemy {
    private GroundEnemyController controller;
    private float startX;
    private float endX;

    public GroundEnemy(World world, Vector2 position, float startX, float endX){
        super(GameAssetManager.crawlidAtlas.findRegion("Walk"),world,position);
        controller=new GroundEnemyController(this);
        this.startX=startX;
        this.endX=endX;
        defineEnemy(position);
        setBounds(0, 0
            ,301/HallowKnight.PPM
            ,149/HallowKnight.PPM);
    }

    @Override
    protected void defineEnemy(Vector2 position){
        super.defineEnemy(position);

        FixtureDef fixtureDef=new FixtureDef();
        PolygonShape shape=new PolygonShape();
        shape.setAsBox(getWidth()/4.5f/ HallowKnight.PPM,getHeight()/4.5f/HallowKnight.PPM);
        fixtureDef.shape=shape;

        b2Body.createFixture(fixtureDef).setUserData(FixtureType.ENEMY);
        b2Body.setUserData(this);
    }

    public void update(float dt){
        b2Body.setLinearVelocity(movementSpeed,0);
        if (b2Body.getPosition().x<=(startX/HallowKnight.PPM)){
            movementSpeed=1.5f;
            controller.setFacingRight(true);;
        }
        if (b2Body.getPosition().x>=(endX/HallowKnight.PPM)){
            movementSpeed=-1.5f;
            controller.setFacingRight(false);
        }
        setRegion(controller.getCurrentFrame());
        setPosition(b2Body.getPosition().x-getWidth()/2f,b2Body.getPosition().y-getHeight()/3.5f);
        controller.update(dt);
    }
}
