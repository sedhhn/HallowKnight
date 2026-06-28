package com.HallowKnight.Model.Knight.Nail.State;

import com.HallowKnight.Controller.Managers.GameAssetManager;
import com.HallowKnight.HallowKnight;
import com.HallowKnight.Model.Knight.Knight;
import com.HallowKnight.Model.Knight.Nail.Nail;
import com.badlogic.gdx.graphics.g2d.Animation;

import java.util.Comparator;

public class NormalSlash extends SlashState{
    public NormalSlash(Nail nail) {
        super(nail);
        frames= GameAssetManager.knightSlashEffect.findRegions("SlashEffect");
        frames.sort(Comparator.comparingInt(a->a.index));
        stateAnimation=new Animation<>(Knight.ATTACK_DURATION/frames.size,frames);

        nail.setBounds(0, 0
            ,frames.get(0).originalWidth/HallowKnight.PPM
            ,frames.get(0).originalHeight/HallowKnight.PPM);
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        nail.getB2Body().setTransform(nail.getKnight().b2Body.getPosition().x
                + (knight.isFacingRight() ? 50 : -50) / HallowKnight.PPM
            , knight.b2Body.getPosition().y, 0);
    }
}
