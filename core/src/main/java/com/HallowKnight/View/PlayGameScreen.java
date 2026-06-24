package com.HallowKnight.View;

import com.HallowKnight.Controller.Managers.GameAssetManager;
import com.HallowKnight.Controller.Managers.ScreenManager;
import com.HallowKnight.HallowKnight;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Scaling;

public class PlayGameScreen extends MenuScreen {

    private Texture backgroundTexture;
    private Image background;
    private Label title;
    private Image divider;
    private Table profilesTable;
    private TextButton startBtn;
    private TextButton backBtn;

    public PlayGameScreen(HallowKnight game){
        super(game);

        backgroundTexture=new Texture("MenuBackgrounds/main_menu_background.png");
        background=new Image(backgroundTexture);
        background.setFillParent(true);
        stage.addActor(background);
        background.toBack();

        title=new Label("Profiles",skin);
        divider=new Image(GameAssetManager.uiDivider);
        divider.setScaling(Scaling.fit);

        profilesTable=new Table();

        //temporary
        startBtn=new TextButton("start",skin);

        backBtn=new TextButton("Back",skin);

        rootTable.add(title).row();
        rootTable.add(divider).row();
        rootTable.add(profilesTable).row();
        rootTable.add(startBtn).row();
        rootTable.add(backBtn);

        //temporary
        startBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ScreenManager.getInstance().setScreen(new GameScreen(game));
            }
        });
    }

    @Override
    public void show() {
        super.show();
        backBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ScreenManager.getInstance().setScreen(new MainMenuScreen(game));
            }
        });
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }
}
