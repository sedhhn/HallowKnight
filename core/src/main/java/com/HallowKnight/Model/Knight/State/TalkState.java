package com.HallowKnight.Model.Knight.State;

import com.HallowKnight.Controller.Managers.GameAssetManager;
import com.HallowKnight.Model.Knight.Knight;
import com.badlogic.gdx.graphics.g2d.Animation;

import java.util.Comparator;

public class TalkState extends State{
    public TalkState(Knight knight) {
        super(knight);
        frames= GameAssetManager.knightAtlas.findRegions("Idle");
        frames.sort(Comparator.comparingInt(a->a.index));
        stateAnimation=new Animation<>(1/10f,frames, Animation.PlayMode.LOOP);
    }
}
