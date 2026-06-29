package com.HallowKnight.Model.Enemies.HuskHornhead.State;

import com.HallowKnight.Controller.Managers.GameAssetManager;
import com.HallowKnight.Model.Enemies.Enemy;
import com.HallowKnight.Model.Enemies.HuskHornhead.HuskHornhead;
import com.HallowKnight.Model.Enemies.State;
import com.badlogic.gdx.graphics.g2d.Animation;

import java.util.Comparator;

public class Angry extends State {
    HuskHornhead huskHornhead;
    public Angry(HuskHornhead huskHornhead) {
        super(huskHornhead);
        this.huskHornhead=huskHornhead;
        frames= GameAssetManager.huskHornheadAtlas.findRegions("Attack Lunge");
        frames.sort(Comparator.comparingInt(a->a.index));
        stateAnimation=new Animation<>(1/10f,frames, Animation.PlayMode.LOOP);
    }

    @Override
    public void enter() {
        super.enter();
        if (huskHornhead.isFacingRight()){
            huskHornhead.setMovementSpeed(HuskHornhead.runSpeed);
        } else {
            huskHornhead.setMovementSpeed(-HuskHornhead.runSpeed);
        }
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        if (huskHornhead.getSurroundSensors().leftSensor>0 || huskHornhead.getSurroundSensors().bottomLeftSensor<1){
            if (huskHornhead.getMovementSpeed()<0){
                huskHornhead.setFacingRight(true);
                huskHornhead.setState(new Walking(huskHornhead));
            }
        } else if(huskHornhead.getSurroundSensors().rightSensor>0 || huskHornhead.getSurroundSensors().bottomRightSensor<1){
            if (huskHornhead.getMovementSpeed()>0){
                huskHornhead.setFacingRight(false);
                huskHornhead.setState(new Walking(huskHornhead));
            }
        }
        huskHornhead.getB2Body().setLinearVelocity(huskHornhead.getMovementSpeed()
            ,huskHornhead.getB2Body().getLinearVelocity().y);
    }
}
