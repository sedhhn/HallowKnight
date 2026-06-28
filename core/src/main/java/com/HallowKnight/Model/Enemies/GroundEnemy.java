package com.HallowKnight.Model.Enemies;

import com.HallowKnight.Controller.GroundEnemyController;
import com.HallowKnight.Controller.Managers.GameAssetManager;
import com.HallowKnight.HallowKnight;
import com.HallowKnight.Model.FixtureType;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class GroundEnemy extends Sprite {
    private GroundEnemyController controller;
    private Body b2Body;
    private World world;
    private float speed=1f;
    private float startX;
    private float endX;
    private boolean dead;

    public GroundEnemy(World world, Vector2 position, float startX, float endX){
        super(GameAssetManager.crawlidAtlas.findRegion("Walk"));
        controller=new GroundEnemyController(this);
        this.world=world;
        this.startX=startX;
        this.endX=endX;
        dead = false;
        defineGroundEnemy(position);
        setBounds(0, 0
            ,301/HallowKnight.PPM
            ,149/HallowKnight.PPM);
    }

    private void defineGroundEnemy(Vector2 position){
        BodyDef bodyDef=new BodyDef();
        bodyDef.type= BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(position.x/HallowKnight.PPM,position.y/HallowKnight.PPM);
        b2Body=world.createBody(bodyDef);

        FixtureDef fixtureDef=new FixtureDef();
        PolygonShape shape=new PolygonShape();
        shape.setAsBox(getWidth()/4.5f/ HallowKnight.PPM,getHeight()/4.5f/HallowKnight.PPM);
        fixtureDef.shape=shape;

        b2Body.createFixture(fixtureDef).setUserData(FixtureType.ENEMY);
        b2Body.setUserData(this);
    }

    public void takeDamage() {
        dead = true;
    }

    public boolean isDead() {
        return dead;
    }

    public Body getBody() {
        return b2Body;
    }

    public void update(float dt){
        b2Body.setLinearVelocity(speed,0);
        if (b2Body.getPosition().x<=(startX/HallowKnight.PPM)){
            speed=1.5f;
            controller.setFacingRight(true);;
        }
        if (b2Body.getPosition().x>=(endX/HallowKnight.PPM)){
            speed=-1.5f;
            controller.setFacingRight(false);
        }
        setRegion(controller.getCurrentFrame());
        setPosition(b2Body.getPosition().x-getWidth()/2f,b2Body.getPosition().y-getHeight()/3.5f);
        controller.update(dt);
    }
}
