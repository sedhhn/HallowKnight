package com.HallowKnight.View.Modals;

import com.HallowKnight.Controller.Managers.GameAssetManager;
import com.HallowKnight.HallowKnight;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HUD extends Stack {
    Table soulContainerTable;
    Table hpContainer;

    Image soulContainer;
    List<Image> hps;
    Array<TextureAtlas.AtlasRegion> frames;
    Animation<TextureRegion> soulContainerAnimation;

    float stateTime;
    public HUD(){
        setFillParent(true);
        stateTime=0;

        soulContainerTable=new Table();
        soulContainerTable.top().left();
        add(soulContainerTable);

        hpContainer=new Table();
        hpContainer.top().left().padTop(40).padLeft(120);
        add(hpContainer);


        frames= GameAssetManager.soulContainer.findRegions("HealthBar");
        frames.sort(Comparator.comparingInt(a->a.index));
        soulContainerAnimation=new Animation<>(0.1f,frames);

        soulContainer=new Image(soulContainerAnimation.getKeyFrame(0));
        soulContainerTable.add(soulContainer).top().left();

        hps=new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Image hp=new Image(GameAssetManager.knightHp.findRegion("FilledHealth"));
            hps.add(hp);
            hpContainer.add(hp).top().left();
        }
    }

    public void update(float dt){
        stateTime+=dt;
        soulContainer.setDrawable(new TextureRegionDrawable(soulContainerAnimation.getKeyFrame(stateTime)));
    }
}
