package com.HallowKnight.Model.Enemies.HuskHornhead.State;

import com.HallowKnight.Controller.Managers.GameAssetManager;
import com.HallowKnight.Model.Enemies.Enemy;
import com.HallowKnight.Model.Enemies.HuskHornhead.HuskHornhead;
import com.HallowKnight.Model.Enemies.State;
import com.badlogic.gdx.graphics.g2d.Animation;

import java.util.Comparator;

public class Idle extends State {
    HuskHornhead huskHornhead;
    public Idle(HuskHornhead huskHornhead) {
        super(huskHornhead);
        this.huskHornhead=huskHornhead;
        frames= GameAssetManager.huskHornheadAtlas.findRegions("Idle");
        frames.sort(Comparator.comparingInt(a->a.index));
        stateAnimation=new Animation<>(1/10f,frames, Animation.PlayMode.LOOP);
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        if (stateTime> HuskHornhead.idleTime){
            huskHornhead.setState(new Walking(huskHornhead));
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
    }
}
