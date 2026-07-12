package com.HallowKnight.Model.Knight;

import com.HallowKnight.Controller.ContactController;
import com.HallowKnight.Controller.KnightController;
import com.HallowKnight.Controller.Managers.AudioManager;
import com.HallowKnight.Controller.Managers.GameAssetManager;
import com.HallowKnight.HallowKnight;
import com.HallowKnight.Model.Charms.*;
import com.HallowKnight.Model.FixtureType;
import com.HallowKnight.Model.Knight.State.Death;
import com.HallowKnight.Model.Knight.State.IdleState;
import com.HallowKnight.Model.Knight.State.State;
import com.HallowKnight.View.GameScreen;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import java.util.ArrayList;
import java.util.List;


public class Knight extends Sprite {
    public static final float MAX_MOVEMENT_SPEED=4f;
    public static final int MAX_HP = 5;
    private static final float INVINCIBILITY_TIME = 1.5f;
    private static final float BLINK_INTERVAL = 0.1f;
    public static final float ATTACK_DURATION=0.3f;
    public static final float DASH_TIME=0.35f;
    public static final float DASH_SPEED=7f;
    public static final float DASH_COOLDOWN=1.5f;
    public static final float MAX_SOUL=99f;
    public static final float FOCUS_DURATION=1.5f;
    public static final float SCREAM_DURATION=1f;
    public static final float FIREBALL_CAST_DURATION=0.7f;
    public static final float SLASH_COOLDOWN=0.5f;
    public static final float BASE_NAIL_DAMAGE=1f;
    public static final float BASE_SOUL_INCREASE=11f;

    private float damage;

    public World world;
    public Body b2Body;

    private boolean facingRight;

    private State state;

    private GameScreen gameScreen;

    SurroundSensors surroundSensors;
    private ContactManager contactManager;

    private KnightController controller;
    private int hp;
    private boolean invincible;
    private float invincibleTimer;
    private float dashCooldown;
    private float dashCooldownRemaining;
    private float soul;
    private float slashCooldown;
    private float slashCooldownRemaining;
    private float focusDuration;
    private float soulIncrease;
    public boolean godMode;

    private Vector2 lastSafePos;
    private boolean shouldTeleport;

    private List<Charm> charms;

    public Knight(World world, Vector2 spawnPos, GameScreen gameScreen){
        super(GameAssetManager.knightIdleAtlas.findRegion("Idle"));
        this.gameScreen=gameScreen;
        facingRight=true;
        controller=new KnightController(this);
        state=new IdleState(this);
        setContactManager(new ContactManager(this));
        ContactController.getInstance().contactListeners.add(getContactManager());
        surroundSensors=new SurroundSensors();
        this.world=world;
        setHp(MAX_HP);
        invincible = false;
        invincibleTimer = 0;
        defineKnight(spawnPos);
        setBounds(0, 0
            ,349/HallowKnight.PPM
            ,186/HallowKnight.PPM);

        dashCooldownRemaining=0;
        slashCooldownRemaining=0;
        charms=new ArrayList<>();
        dashCooldown=DASH_COOLDOWN;
        slashCooldown=SLASH_COOLDOWN;
        focusDuration=FOCUS_DURATION;
        soulIncrease=BASE_SOUL_INCREASE;
        damage=BASE_NAIL_DAMAGE;
        lastSafePos=new Vector2(b2Body.getPosition());
    }

    public void defineKnight(Vector2 spawnPos){
        BodyDef bodyDef=new BodyDef();
        bodyDef.position.set(spawnPos.x/ HallowKnight.PPM,spawnPos.y/HallowKnight.PPM);
        bodyDef.type= BodyDef.BodyType.DynamicBody;
        b2Body=world.createBody(bodyDef);

        float hx=(getWidth()/17)/HallowKnight.PPM;
        float hy=(getHeight()/4)/HallowKnight.PPM;

        FixtureDef fixtureDef=new FixtureDef();
        PolygonShape shape=new PolygonShape();
        shape.setAsBox(hx, hy);

        fixtureDef.shape=shape;
        Fixture fixture = b2Body.createFixture(fixtureDef);
        fixture.setUserData(FixtureType.KNIGHT);
        b2Body.setUserData(this);

        surroundSensors.createSensors(this.b2Body,hx,hy);
    }

    public void update(float deltaTime){
        state.update(deltaTime);
        setPosition(b2Body.getPosition().x-getWidth()/2f,b2Body.getPosition().y-getHeight()/3.5f);

        if (invincible) {
            invincibleTimer -= deltaTime;
            int blinkPhase = (int)(invincibleTimer / BLINK_INTERVAL);
            setAlpha(blinkPhase % 2 == 0 ? 1f : 0f);
            if (invincibleTimer <= 0) {
                invincible = false;
                setAlpha(1f);
            }
        }

        if (dashCooldownRemaining>0){
            dashCooldownRemaining-=deltaTime;
        }

        if(slashCooldownRemaining>0){
            slashCooldownRemaining-=deltaTime;
        }

        if (hp<=0 && !(state instanceof Death)){
            setState(new Death(this));
        }
    }

    public void takeDamage(int damage) {
        if (invincible || godMode) return;
        AudioManager.getInstance().playSFX(GameAssetManager.knightDamage);
        setHp(getHp() - damage);
        if (getHp() < 0) setHp(0);
        invincible = true;
        invincibleTimer = INVINCIBILITY_TIME;
    }

    public int getHp() {
        return hp;
    }

    public KnightController getController(){
        return controller;
    }

    public void setFacingRight(boolean facingRight){
        this.facingRight=facingRight;
    }

    public void setState(State state){
        this.state.exit();
        this.state=state;
        state.enter();
    }

    public boolean isTouchingGround(){
        return surroundSensors.bottomSensor > 0;
    }

    public boolean isTouchingWall(){
        return surroundSensors.leftSensor>0 || surroundSensors.rightSensor>0;
    }

    public boolean isFacingRight(){
        return this.facingRight;
    }

    public SurroundSensors getSurroundSensors(){
        return surroundSensors;
    }

    public void resetDashCooldown(){
        dashCooldownRemaining=dashCooldown;
    }

    public float getDashCooldown(){
        return dashCooldown;
    }

    public float getSoul(){
        return soul;
    }

    public void increaseSoul(float amount){
        setSoul(getSoul() + amount);
        if (getSoul() >MAX_SOUL){
            setSoul(MAX_SOUL);
        }
    }

    public void decreaseSoul(float amount){
        setSoul(getSoul() - amount);
        if (getSoul() <0){
            setSoul(0);
        }
    }

    public void increaseHp(int amount){
        setHp(getHp() + amount);
        if (getHp() >MAX_HP){
            setHp(MAX_HP);
        }
    }

    public GameScreen getGameScreen(){
        return gameScreen;
    }

    public World getWorld(){
        return world;
    }

    public void setDashCooldown(float dashCooldown){
        this.dashCooldown=dashCooldown;
    }

    public float getSlashCooldown(){
        return slashCooldown;
    }

    public void setSlashCooldown(float slashCooldown){
        this.slashCooldown=slashCooldown;
    }

    public float getSlashCooldownRemaining(){
        return slashCooldownRemaining;
    }

    public void setSlashCooldownRemaining(float slashCooldownRemaining){
        this.slashCooldownRemaining=slashCooldownRemaining;
    }

    public float getDashCooldownRemaining(){
        return dashCooldownRemaining;
    }

    public void setDashCooldownRemaining(float dashCooldownRemaining){
        this.dashCooldownRemaining=dashCooldownRemaining;
    }

    public void equipCharm(CharmType type){
        switch (type){
            case DASHMASTER -> charms.add(new Dashmaster(this));
            case QUICK_SLASH -> charms.add(new QuickSlash(this));
            case QUICK_FOCUS -> charms.add(new QuickFocus(this));
            case SOUL_CATCHER -> charms.add(new SoulCatcher(this));
            case UNBREAKABLE_STRENGTH -> charms.add(new UnbreakableStrength(this));
        }
        charms.get(charms.size()-1).onEquip();
    }

    public void unEquipCharm(CharmType type){
        for (Charm c: charms){
            if (c.getType()==type){
                c.onUnEquip();
                charms.remove(c);
                break;
            }
        }
    }

    public boolean hasCharm(CharmType type){
        for (Charm c: charms){
            if (c.getType()==type){
                return true;
            }
        }
        return false;
    }

    public int charmsCount(){
        return charms.size();
    }

    public float getDamage(){
        return damage;
    }

    public void setDamage(float damage){
        this.damage=damage;
    }

    public boolean isInvincible(){
        return invincible;
    }

    public void setFocusDuration(float focusDuration){
        this.focusDuration=focusDuration;
    }

    public float getFocusDuration(){
        return focusDuration;
    }

    public void setSoulIncrease(float soulIncrease){
        this.soulIncrease=soulIncrease;
    }

    public float getSoulIncrease(){
        return soulIncrease;
    }

    public void setLastSafePos(){
        lastSafePos.set(b2Body.getPosition());
    }

    public Vector2 getLastSafePos(){
        return lastSafePos;
    }

    public void teleportToSafePos(){
        shouldTeleport=true;
    }

    public void setShouldTeleport(boolean shouldTeleport){
        this.shouldTeleport=shouldTeleport;
    }

    public boolean shouldTeleport(){
        return shouldTeleport;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setSoul(float soul) {
        this.soul = soul;
    }

    public ContactManager getContactManager() {
        return contactManager;
    }

    public void setContactManager(ContactManager contactManager) {
        this.contactManager = contactManager;
    }

    public State getState(){
        return state;
    }
}
