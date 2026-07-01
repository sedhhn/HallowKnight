package com.HallowKnight.Model.NPCs.Zote.State;

import com.HallowKnight.Controller.Managers.GameAssetManager;
import com.HallowKnight.HallowKnight;
import com.HallowKnight.Model.NPCs.NPC;
import com.HallowKnight.Model.NPCs.Zote.Zote;
import com.HallowKnight.View.MenuScreen;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import java.util.Comparator;

public class Idle extends State{
    Zote zote;
    Label label;
    Table table;
    public Idle(Zote zote) {
        super(zote);
        this.zote=zote;
        frames= GameAssetManager.zoteAtlas.findRegions("Idle");
        frames.sort(Comparator.comparingInt(a->a.index));
        stateAnimation=new Animation<>(1/10f,frames, Animation.PlayMode.LOOP);
    }

    @Override
    public void enter() {
        super.enter();
        table=new Table();
        table.setFillParent(true);
        label=new Label("Talk", GameAssetManager.skin);
        MenuScreen.getInstance().getMainStack().add(table);
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        if (zote.getSurroundSensors().radarSensor>0){

        }
    }
}
