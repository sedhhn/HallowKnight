package com.HallowKnight.View;

import com.HallowKnight.HallowKnight;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public abstract class MenuScreen extends ScreenAdapter {
    protected HallowKnight game;
    protected ScreenViewport viewport;
    protected Stage stage;
    protected Skin skin;
    protected Table rootTable;
    protected Stack mainStack;

    public MenuScreen(HallowKnight game){
        this.game=game;
        viewport=new ScreenViewport();
        skin=game.skin;
        stage=new Stage(viewport);
        rootTable=new Table();
        rootTable.setFillParent(true);
        mainStack=new Stack();
        mainStack.setFillParent(true);
        mainStack.add(rootTable);
        stage.addActor(mainStack);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height,true);
    }
}
