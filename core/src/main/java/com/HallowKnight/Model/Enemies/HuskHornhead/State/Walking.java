package com.HallowKnight.Model.Enemies.HuskHornhead.State;

import com.HallowKnight.Controller.Managers.GameAssetManager;
import com.HallowKnight.Model.Enemies.Enemy;
import com.HallowKnight.Model.Enemies.HuskHornhead.HuskHornhead;
import com.HallowKnight.Model.Enemies.State;
import com.badlogic.gdx.graphics.g2d.Animation;

import java.util.Comparator;

public class Walking extends State {
    HuskHornhead huskHornhead;

    public Walking(HuskHornhead huskHornhead) {
        super(huskHornhead);
        this.huskHornhead=huskHornhead;
        frames= GameAssetManager.huskHornheadAtlas.findRegions("Walk");
        frames.sort(Comparator.comparingInt(a->a.index));
        stateAnimation=new Animation<>(1/10f,frames, Animation.PlayMode.LOOP);
    }

    @Override
    public void enter() {
        super.enter();
        if (huskHornhead.isFacingRight()) {
            huskHornhead.setMovementSpeed(HuskHornhead.walkSpeed);
        } else{
            huskHornhead.setMovementSpeed(-HuskHornhead.walkSpeed);
        }
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        if (huskHornhead.getSurroundSensors().leftSensor>0 || huskHornhead.getSurroundSensors().bottomLeftSensor<1){
            if (huskHornhead.getMovementSpeed()<0){
                huskHornhead.setMovementSpeed(-huskHornhead.getMovementSpeed());
                huskHornhead.setFacingRight(true);
            }
        } else if(huskHornhead.getSurroundSensors().rightSensor>0 || huskHornhead.getSurroundSensors().bottomRightSensor<1){
            if (huskHornhead.getMovementSpeed()>0){
                huskHornhead.setMovementSpeed(-huskHornhead.getMovementSpeed());
                huskHornhead.setFacingRight(false);
            }
        }
        if (huskHornhead.isFacingRight()){
            if(huskHornhead.getSurroundSensors().rightRadarSensor>0){
                huskHornhead.setState(new Angry(huskHornhead));
            }
        } else {
            if(huskHornhead.getSurroundSensors().leftRadarSensor>0){
                huskHornhead.setState(new Angry(huskHornhead));
            }
        }
        enemy.getB2Body().setLinearVelocity(enemy.getMovementSpeed()
            ,enemy.getB2Body().getLinearVelocity().y);

        if (stateTime>=HuskHornhead.walkTime){
            huskHornhead.setState(new Idle(huskHornhead));
        }
    }
}
