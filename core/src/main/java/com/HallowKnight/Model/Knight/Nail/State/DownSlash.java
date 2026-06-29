package com.HallowKnight.Model.Knight.Nail.State;

import com.HallowKnight.Controller.Managers.GameAssetManager;
import com.HallowKnight.HallowKnight;
import com.HallowKnight.Model.Knight.Knight;
import com.HallowKnight.Model.Knight.Nail.Nail;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;

import java.util.Comparator;

public class DownSlash extends SlashState {
    public DownSlash(Nail nail) {
        super(nail);
        frames= GameAssetManager.knightSlashEffect.findRegions("DownSlashEffect");
        frames.sort(Comparator.comparingInt(a->a.index));
        stateAnimation=new Animation<>(Knight.ATTACK_DURATION/frames.size,frames);

        nail.setBounds(0, 0
            ,182/ HallowKnight.PPM
            ,209/HallowKnight.PPM);
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        nail.getB2Body().setTransform(knight.b2Body.getPosition().x
            , knight.b2Body.getPosition().y-50f/HallowKnight.PPM, 0);
    }

    @Override
    public void onContactWithDeadly() {
        super.onContactWithDeadly();
        knight.b2Body.setLinearVelocity(knight.b2Body.getLinearVelocity().x,0);
        knight.b2Body.applyLinearImpulse(new Vector2(0,4f),knight.b2Body.getWorldCenter(),true);
    }
}
