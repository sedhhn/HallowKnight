package com.HallowKnight.Model.FalseKnight.State;

import com.HallowKnight.Controller.Managers.GameAssetManager;
import com.HallowKnight.Model.FalseKnight.FalseKnight;
import com.HallowKnight.Model.FalseKnight.Mace;
import com.badlogic.gdx.graphics.g2d.Animation;

import java.util.Comparator;

public class MaceSlam extends State{
    Mace mace;
    public MaceSlam(FalseKnight falseKnight) {
        super(falseKnight);
        frames= GameAssetManager.falseKnight.findRegions("Attack");
        frames.sort(Comparator.comparingInt(a->a.index));
        stateAnimation=new Animation<>(FalseKnight.MACE_SLAM_DEFAULT_DURATION/frames.size,frames);
    }

    @Override
    public void enter() {
        super.enter();
        if (falseKnight.getKnight().b2Body.getPosition().x>falseKnight.b2Body.getPosition().x){
            falseKnight.setFacingRight(true);
        } else {
            falseKnight.setFacingRight(false);
        }

    }

    @Override
    public void update(float dt) {
        super.update(dt);
        if (mace==null && stateTime>FalseKnight.MACE_SLAM_DEFAULT_DURATION*0.5) {
            mace = new Mace(falseKnight.world, falseKnight);
        }
        if (mace!=null) {
            mace.update(dt);
        }
        if (stateTime>FalseKnight.MACE_SLAM_DEFAULT_DURATION){
            falseKnight.getGameScreen().getCamera().startShake();
            falseKnight.setState(new Idle(falseKnight));
        }
    }

    @Override
    public void exit() {
        super.exit();
        if (mace!=null) {
            mace.destroy();
        }
    }
}
