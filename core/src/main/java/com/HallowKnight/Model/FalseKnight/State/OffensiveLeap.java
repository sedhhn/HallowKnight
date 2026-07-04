package com.HallowKnight.Model.FalseKnight.State;

import com.HallowKnight.Controller.Managers.GameAssetManager;
import com.HallowKnight.Model.FalseKnight.FalseKnight;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;

import java.util.Comparator;

public class OffensiveLeap extends State{
    public OffensiveLeap(FalseKnight falseKnight) {
        super(falseKnight);
        frames= GameAssetManager.falseKnight.findRegions("Jump");
        frames.sort(Comparator.comparingInt(a->a.index));
        stateAnimation=new Animation<>(1/10f,frames);
    }

    @Override
    public void enter() {
        super.enter();
        if (falseKnight.getKnight().b2Body.getPosition().x>falseKnight.b2Body.getPosition().x) {
            falseKnight.setFacingRight(true);
            falseKnight.b2Body.applyLinearImpulse(FalseKnight.OFFENSIVE_LEAP_LINEAR_IMPULSE
                ,falseKnight.b2Body.getWorldCenter(),true);
        } else {
            falseKnight.setFacingRight(false);
            falseKnight.b2Body.applyLinearImpulse(
                new Vector2(-FalseKnight.OFFENSIVE_LEAP_LINEAR_IMPULSE.x
                    , FalseKnight.OFFENSIVE_LEAP_LINEAR_IMPULSE.y)
            ,falseKnight.b2Body.getWorldCenter(), true);
        }
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        if (stateTime>0.1f){
            if (falseKnight.getSurroundSensors().bottomSensor>0){
                falseKnight.getGameScreen().getCamera().startShake();
                falseKnight.setState(new Idle(falseKnight));
            }
        }
    }
}
