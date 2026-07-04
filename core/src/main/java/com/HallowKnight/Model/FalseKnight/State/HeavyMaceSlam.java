package com.HallowKnight.Model.FalseKnight.State;

import com.HallowKnight.Controller.Managers.GameAssetManager;
import com.HallowKnight.Model.FalseKnight.FalseKnight;
import com.HallowKnight.Model.FalseKnight.Shockwave;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;

import java.util.Comparator;

public class HeavyMaceSlam extends State {
    float frameDuration=1/7f;
    boolean cameraShaked=false;
    Shockwave shockwaveRight;
    Shockwave shockwaveLeft;
    public HeavyMaceSlam(FalseKnight falseKnight) {
        super(falseKnight);
        frames= GameAssetManager.falseKnight.findRegions("Jump Attack");
        frames.sort(Comparator.comparingInt(a->a.index));
        stateAnimation=new Animation<>(frameDuration,frames);
    }

    @Override
    public void enter() {
        super.enter();
        if (falseKnight.getKnight().b2Body.getPosition().x>falseKnight.b2Body.getPosition().x){
            falseKnight.setFacingRight(true);
            falseKnight.b2Body.applyLinearImpulse(FalseKnight.HEAVY_MACE_SLAM_LINEAR_IMPULSE
                ,falseKnight.b2Body.getWorldCenter(),true);
        } else {
            falseKnight.setFacingRight(false);
            falseKnight.b2Body.applyLinearImpulse(new Vector2(
                    -FalseKnight.HEAVY_MACE_SLAM_LINEAR_IMPULSE.x
                ,FalseKnight.HEAVY_MACE_SLAM_LINEAR_IMPULSE.y
                ), falseKnight.b2Body.getWorldCenter(),true);
        }
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        if (stateTime>0.1f){
            if (falseKnight.getSurroundSensors().bottomSensor>0){
                if (!cameraShaked) {
                    falseKnight.getGameScreen().getCamera().startShake();
                    cameraShaked=true;
                    shockwaveRight=new Shockwave(falseKnight.world,falseKnight,true);
                    shockwaveLeft=new Shockwave(falseKnight.world,falseKnight,false);
                    falseKnight.getGameScreen().getController().getEffects().add(shockwaveRight);
                    falseKnight.getGameScreen().getController().getEffects().add(shockwaveLeft);
                }
                if (stateTime>frameDuration*frames.size) {
                    falseKnight.setState(new Idle(falseKnight));
                }
            }
        }
    }
}
