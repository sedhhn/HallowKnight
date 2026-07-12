package com.HallowKnight.Model.Effects;

import com.HallowKnight.Controller.Managers.GameAssetManager;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;

import java.util.Comparator;

public class GrimmFlame extends Effect{
    public GrimmFlame(Vector2 position){
        super(GameAssetManager.grimmFlame.findRegion("Grimmflame"));
        frames= GameAssetManager.grimmFlame.findRegions("Grimmflame");
        frames.sort(Comparator.comparingInt(a->a.index));
        animation=new Animation<>(1/10f,frames, Animation.PlayMode.LOOP);
        setPosition(position.x,position.y);
    }
}
