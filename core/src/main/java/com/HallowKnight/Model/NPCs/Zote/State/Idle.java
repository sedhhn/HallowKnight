package com.HallowKnight.Model.NPCs.Zote.State;

import com.HallowKnight.Controller.Managers.GameAssetManager;
import com.HallowKnight.Model.Knight.State.TalkState;
import com.HallowKnight.Model.NPCs.NPC;
import com.HallowKnight.Model.NPCs.Zote.Zote;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

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
        table=new Table();
        table.setFillParent(true);
        table.right().top().pad(50f);
        label=new Label("Press Z to talk", GameAssetManager.skin);
        table.add(label);
    }

    public Table getTable() {
        return table;
    }

    @Override
    public void enter() {
        super.enter();
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        if (zote.getSurroundSensors().radarSensor>0){
            zote.getGameScreen().getMainStack().add(table);
            handleInputs();
        } else {
            zote.getGameScreen().getMainStack().removeActor(table);
        }
    }

    @Override
    protected void handleInputs() {
        super.handleInputs();
        if (Gdx.input.isKeyJustPressed(Input.Keys.Z)){
            zote.getKnight().setState(new TalkState(zote.getKnight()));
            zote.setState(new Talk(zote));
        }
    }

    @Override
    public void exit() {
        super.exit();
        zote.getGameScreen().getMainStack().removeActor(table);
    }
}
