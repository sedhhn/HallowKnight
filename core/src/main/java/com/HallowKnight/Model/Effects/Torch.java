package com.HallowKnight.Model.Effects;

import com.HallowKnight.Controller.Managers.GameAssetManager;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import java.util.Comparator;

public class Torch extends Effect{
    public Torch(Vector2 position) {
        super(GameAssetManager.torch.findRegion("torch"));
        frames= GameAssetManager.torch.findRegions("torch");
        frames.sort(Comparator.comparingInt(a->a.index));
        animation=new Animation<>(1/10f,frames, Animation.PlayMode.LOOP);
        setPosition(position.x,position.y);
    }
}
