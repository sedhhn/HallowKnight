package com.HallowKnight.View.Modals;

import com.HallowKnight.Controller.Managers.GameAssetManager;
import com.HallowKnight.HallowKnight;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Scaling;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HUD extends Stack {
    Table healthBarTable;
    Table hpContainer;

    Image healthBar;
    List<Image> hps;
    Array<TextureAtlas.AtlasRegion> frames;
    Animation<TextureRegion> healthBarAnimation;

    private TextureRegion filledHpRegion;
    private TextureRegion emptyHpRegion;

    private SoulOrbWidget soulOrbWidget;
    private TextureRegion soulOrbFullRegion;
    private float soulOrbWidth;
    private float soulOrbHeight;

    float stateTime;
    public HUD(){
        setFillParent(true);
        stateTime=0;

        healthBarTable=new Table();
        healthBarTable.top().left();
        add(healthBarTable);

        hpContainer=new Table();
        hpContainer.top().left().padTop(40).padLeft(120);
        add(hpContainer);

        frames= GameAssetManager.healthBar.findRegions("HealthBar");
        frames.sort(Comparator.comparingInt(a->a.index));
        healthBarAnimation=new Animation<>(0.2f,frames);

        healthBar=new Image(healthBarAnimation.getKeyFrame(0));
        healthBarTable.add(healthBar).top().left();

        filledHpRegion = GameAssetManager.knightHp.findRegion("FilledHealth");
        emptyHpRegion = GameAssetManager.knightHp.findRegion("EmptyHealth");

        hps=new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Image hp=new Image(filledHpRegion);
            hps.add(hp);
            hpContainer.add(hp).top().left();
        }

        soulOrbFullRegion = new TextureRegion(GameAssetManager.fullSoulOrb);
        soulOrbWidth = soulOrbFullRegion.getRegionWidth();
        soulOrbHeight = soulOrbFullRegion.getRegionHeight();

        soulOrbWidget=new SoulOrbWidget(soulOrbFullRegion);

        Table soulOrbRootTable = new Table();
        soulOrbRootTable.top().left().setSize(soulOrbWidth, soulOrbHeight);
        soulOrbRootTable.setFillParent(true);

        Table soulOrbWrapper = new Table();
// سایز این بخش رو هم متناسب با اندازه واقعی Orb قرار بده
        soulOrbRootTable.add(soulOrbWidget).size(255, 165);
        add(soulOrbRootTable);

// مقدار دهی اولیه (مثلاً با مقدار پر)
        updateSoul(0.5f, 1f);
    }

    public void update(float dt){
        stateTime+=dt;
        healthBar.setDrawable(new TextureRegionDrawable(healthBarAnimation.getKeyFrame(stateTime)));
    }

    public void updateHealth(int currentHp, int maxHp) {
        for (int i = 0; i < hps.size(); i++) {
            if (i < currentHp) {
                hps.get(i).setDrawable(new TextureRegionDrawable(filledHpRegion));
            } else {
                hps.get(i).setDrawable(new TextureRegionDrawable(emptyHpRegion));
            }
        }
    }

    public void updateSoul(float currentSoul, float maxSoul) {
        float percent = currentSoul / maxSoul;
        soulOrbWidget.setPercent(percent);
    }


}
