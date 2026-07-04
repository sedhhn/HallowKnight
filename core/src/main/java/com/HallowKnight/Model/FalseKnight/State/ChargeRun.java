package com.HallowKnight.Model.FalseKnight.State;

import com.HallowKnight.Controller.Managers.GameAssetManager;
import com.HallowKnight.Model.FalseKnight.FalseKnight;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;

import java.util.Comparator;

public class ChargeRun extends State{
    Vector2 velocity;
    public ChargeRun(FalseKnight falseKnight) {
        super(falseKnight);
        frames= GameAssetManager.falseKnight.findRegions("Run");
        frames.sort(Comparator.comparingInt(a->a.index));
        stateAnimation=new Animation<>(1/8f,frames, Animation.PlayMode.LOOP);
    }

    @Override
    public void enter() {
        super.enter();
        if (falseKnight.getKnight().b2Body.getPosition().x>falseKnight.b2Body.getPosition().x){
            falseKnight.setFacingRight(true);
            falseKnight.setMovementSpeed(FalseKnight.CHARGE_RUN_MOVEMENT_SPEED);
        } else {
            falseKnight.setFacingRight(false);
            falseKnight.setMovementSpeed(-FalseKnight.CHARGE_RUN_MOVEMENT_SPEED);
        }
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        if (falseKnight.getSurroundSensors().leftSensor>0 || falseKnight.getSurroundSensors().rightSensor>0){
            falseKnight.setState(new Idle(falseKnight));
        }
        if (stateTime>FalseKnight.CHARGE_RUN_MAX_DURATION){
            falseKnight.setState(new Idle(falseKnight));
        }
        falseKnight.b2Body.setLinearVelocity(falseKnight.getMovementSpeed()
            ,falseKnight.b2Body.getLinearVelocity().y);
    }
}
