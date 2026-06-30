package com.HallowKnight.Model.Enemies.Crystallized.State;

import com.HallowKnight.Controller.Managers.GameAssetManager;
import com.HallowKnight.Model.Enemies.Crystallized.Crystallized;
import com.HallowKnight.Model.Enemies.Enemy;
import com.HallowKnight.Model.Enemies.State;
import com.badlogic.gdx.graphics.g2d.Animation;

import java.util.Comparator;

public class Angry extends State {
    Crystallized crystallized;
    public Angry(Crystallized crystallized) {
        super(crystallized);
        this.crystallized=crystallized;

        frames= GameAssetManager.crystallizedAtlas.findRegions("Run");
        frames.sort(Comparator.comparingInt(a->a.index));
        stateAnimation=new Animation<>(1/10f,frames, Animation.PlayMode.LOOP);
    }

    @Override
    public void enter() {
        super.enter();
        if (crystallized.isFacingRight()) {
            crystallized.setMovementSpeed(Crystallized.angerSpeed);
        } else {
            crystallized.setMovementSpeed(-Crystallized.angerSpeed);
        }
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        if (crystallized.getSurroundSensors().leftSensor>0 || crystallized.getSurroundSensors().bottomLeftSensor<1){
            if (crystallized.getMovementSpeed()<0){
                crystallized.setFacingRight(true);
                crystallized.setState(new Idle(crystallized));
            }
        } else if(crystallized.getSurroundSensors().rightSensor>0 || crystallized.getSurroundSensors().bottomRightSensor<1){
            if (crystallized.getMovementSpeed()>0){
                crystallized.setFacingRight(false);
                crystallized.setState(new Idle(crystallized));
            }
        }
        if (stateTime>=Crystallized.angerDuration){
            crystallized.setState(new Idle(crystallized));
        }
        crystallized.getB2Body().setLinearVelocity(crystallized.getMovementSpeed()
            ,crystallized.getB2Body().getLinearVelocity().y);
    }
}
