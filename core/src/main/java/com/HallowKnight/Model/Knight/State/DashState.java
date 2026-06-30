package com.HallowKnight.Model.Knight.State;

import com.HallowKnight.Controller.Managers.GameAssetManager;
import com.HallowKnight.Model.Knight.Knight;
import com.badlogic.gdx.graphics.g2d.Animation;

import java.util.Comparator;

public class DashState extends State{
    public DashState(Knight knight) {
        super(knight);
        frames= GameAssetManager.knightAtlas.findRegions("Dash");
        frames.sort(Comparator.comparingInt(a->a.index));
        stateAnimation=new Animation<>(Knight.DASH_TIME/frames.size,frames);
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        if (stateTime>Knight.DASH_TIME){
            knight.setState(new IdleState(knight));
        }
        if (knight.isFacingRight()) {
            knight.b2Body.setLinearVelocity(Knight.DASH_SPEED,0);
        } else{
            knight.b2Body.setLinearVelocity(-Knight.DASH_SPEED,0);
        }
    }

    @Override
    public void exit() {
        super.exit();
        knight.resetDashCooldown();
    }
}
