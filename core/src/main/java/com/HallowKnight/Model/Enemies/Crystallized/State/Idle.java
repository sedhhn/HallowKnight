package com.HallowKnight.Model.Enemies.Crystallized.State;

import com.HallowKnight.Controller.Managers.GameAssetManager;
import com.HallowKnight.Model.Enemies.Crystallized.Crystallized;
import com.HallowKnight.Model.Enemies.Enemy;
import com.HallowKnight.Model.Enemies.State;
import com.badlogic.gdx.graphics.g2d.Animation;

import java.util.Comparator;

public class Idle extends State {
    Crystallized crystallized;
    public Idle(Crystallized crystallized) {
        super(crystallized);
        this.crystallized=crystallized;
        frames= GameAssetManager.crystallizedAtlas.findRegions("Idle");
        frames.sort(Comparator.comparingInt(a->a.index));
        stateAnimation=new Animation<>(1/10f,frames, Animation.PlayMode.LOOP);
    }

    @Override
    public void enter() {
        super.enter();
        crystallized.getB2Body().setLinearVelocity(0,crystallized.getB2Body().getLinearVelocity().y);
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        crystallized.getB2Body().setLinearVelocity(0,crystallized.getB2Body().getLinearVelocity().y);
        if (crystallized.isFacingRight()){
            if (crystallized.getSurroundSensors().rightRadarSensor>0){
                crystallized.setState(new LaserAttack(crystallized));
            }
        } else{
            if (crystallized.getSurroundSensors().leftRadarSensor>0){
                crystallized.setState(new LaserAttack(crystallized));
            }
        }
    }
}
