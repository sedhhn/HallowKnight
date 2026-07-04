package com.HallowKnight.Model.FalseKnight.State;

import com.HallowKnight.Controller.Managers.GameAssetManager;
import com.HallowKnight.Model.FalseKnight.FalseKnight;
import com.badlogic.gdx.graphics.g2d.Animation;

import java.util.Comparator;

public class Dead extends State{
    public Dead(FalseKnight falseKnight) {
        super(falseKnight);
        frames= GameAssetManager.falseKnight.findRegions("DeathFall");
        frames.sort(Comparator.comparingInt(a->a.index));
        stateAnimation=new Animation<>(1/10f,frames);
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        falseKnight.b2Body.setLinearVelocity(0,falseKnight.b2Body.getLinearVelocity().y);
    }
}
