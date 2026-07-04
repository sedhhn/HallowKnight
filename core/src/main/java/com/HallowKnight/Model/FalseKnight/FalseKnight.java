package com.HallowKnight.Model.FalseKnight;

import com.HallowKnight.Controller.ContactController;
import com.HallowKnight.Controller.Managers.GameAssetManager;
import com.HallowKnight.HallowKnight;
import com.HallowKnight.Model.FalseKnight.State.*;
import com.HallowKnight.Model.FixtureType;
import com.HallowKnight.Model.Knight.Knight;
import com.HallowKnight.View.GameScreen;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class FalseKnight extends Sprite {
    public static final float MAX_HP=200f;
    public static final float CHARGE_RUN_MOVEMENT_SPEED=6f;
    public static final float CHARGE_RUN_MAX_DURATION=2f;
    public static final float MACE_SLAM_DEFAULT_DURATION=0.6f;
    public static final Vector2 OFFENSIVE_LEAP_LINEAR_IMPULSE=new Vector2(3,9);
    public static final Vector2 DEFENSIVE_LEAP_LINEAR_IMPULSE=new Vector2(3,6);
    public static final Vector2 HEAVY_MACE_SLAM_LINEAR_IMPULSE=new Vector2(4f,4.5f);
    public static final float STUN_DURATION=5f;
    public static final float PHASE1_COOLDOWN=3.5f;
    public static final float PHASE2_COOLDOWN=1.25f;

    private float cooldown;

    private boolean phase2;

    private Action lastAction;

    Knight knight;

    public World world;
    public Body b2Body;

    private boolean facingRight;
    private float movementSpeed=0;
    private GameScreen gameScreen;

    private State state;

    private SurroundSensors surroundSensors;
    ContactManager contactManager;

    private boolean dead=false;

    private float hp;

    public FalseKnight(World world, Vector2 spawnPos, Knight knight, GameScreen gameScreen){
        super(GameAssetManager.falseKnight.findRegion("Idle"));
        this.knight=knight;
        this.gameScreen=gameScreen;
        facingRight=false;
        state=new Idle(this);
        surroundSensors=new SurroundSensors();
        this.world=world;
        hp=MAX_HP;
        defineFalseKnight(spawnPos);
        contactManager=new ContactManager(this);
        ContactController.getInstance().contactListeners.add(contactManager);
        setBounds(0,0,getWidth()/HallowKnight.PPM,getHeight()/HallowKnight.PPM);
        cooldown=PHASE1_COOLDOWN;
        phase2=false;
    }

    public void defineFalseKnight(Vector2 spawnPos){
        BodyDef bodyDef=new BodyDef();
        bodyDef.position.set(spawnPos.x/ HallowKnight.PPM,spawnPos.y/HallowKnight.PPM);
        bodyDef.type= BodyDef.BodyType.DynamicBody;
        b2Body=world.createBody(bodyDef);

        float hx=(getWidth()/7)/HallowKnight.PPM;
        float hy=(getHeight()/4.5f)/HallowKnight.PPM;

        FixtureDef fixtureDef=new FixtureDef();
        PolygonShape shape=new PolygonShape();
        shape.setAsBox(hx, hy);

        fixtureDef.shape=shape;
        Fixture fixture = b2Body.createFixture(fixtureDef);
        fixture.setUserData(FixtureType.BOSS);
        b2Body.setUserData(this);

        surroundSensors.createSensors(this.b2Body,hx,hy);
    }

    public void update(float dt){
        state.update(dt);
        setPosition(b2Body.getPosition().x-getWidth()/2f,b2Body.getPosition().y-getHeight()/3.5f);
        if (hp<MAX_HP*0.5 && !phase2){
            setState(new StunState(this));
            phase2=true;
            cooldown=PHASE2_COOLDOWN;
        }
        if (hp<=0 && !dead){
            setState(new Dead(this));
            ContactController.getInstance().contactListeners.remove(contactManager);
            dead=true;
        }
    }

    public void setState(State state){
        this.state.exit();
        this.state=state;
        this.state.enter();
    }

    public void takeDamage(float amount){
        hp-=amount;
        if (hp<0){
            hp=0;
        }
    }

    public void setFacingRight(boolean facingRight){
        this.facingRight=facingRight;
    }

    public boolean isFacingRight(){
        return this.facingRight;
    }

    public SurroundSensors getSurroundSensors(){
        return surroundSensors;
    }

    public Vector2 vecToKnight(){
        float vecX=knight.b2Body.getPosition().x-b2Body.getPosition().x;
        float vecY=knight.b2Body.getPosition().y=b2Body.getPosition().y;
        return new Vector2(vecX,vecY);
    }

    public Knight getKnight(){
        return knight;
    }

    public void setMovementSpeed(float movementSpeed){
        this.movementSpeed=movementSpeed;
    }

    public float getMovementSpeed(){
        return movementSpeed;
    }

    public GameScreen getGameScreen(){
        return gameScreen;
    }

    public float getCooldown(){
        return cooldown;
    }

    public boolean isPhase2(){
        return phase2;
    }

    public Action getLastAction(){
        return lastAction;
    }

    public void setLastAction(Action lastAction){
        this.lastAction=lastAction;
    }

    public float getRecentDamageAmount(){
        return 1;
    }
}
