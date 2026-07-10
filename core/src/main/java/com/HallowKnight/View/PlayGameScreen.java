package com.HallowKnight.View;

import com.HallowKnight.Controller.Managers.GameAssetManager;
import com.HallowKnight.Controller.Managers.SaveManager;
import com.HallowKnight.Controller.Managers.ScreenManager;
import com.HallowKnight.HallowKnight;
import com.HallowKnight.Model.GameState;
import com.HallowKnight.Model.Knight.Knight;
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
    private Table save0Table;
    private Table save1Table;
    private Table save2Table;
    private Table save3Table;
    private TextButton backBtn;

    private Label save0;
    private Label save0Time;
    private Label save0Hp;
    private Label save0Soul;
    private GameState gameState0;
    private TextButton save0LoadBtn;
    private TextButton save0ResetBtn;

    private Label save1;
    private Label save1Time;
    private Label save1Hp;
    private Label save1Soul;
    private GameState gameState1;
    private TextButton save1LoadBtn;
    private TextButton save1ResetBtn;

    private Label save2;
    private Label save2Time;
    private Label save2Hp;
    private Label save2Soul;
    private GameState gameState2;
    private TextButton save2LoadBtn;
    private TextButton save2ResetBtn;

    private Label save3;
    private Label save3Time;
    private Label save3Hp;
    private Label save3Soul;
    private GameState gameState3;
    private TextButton save3LoadBtn;
    private TextButton save3ResetBtn;

    public PlayGameScreen(HallowKnight game){
        super(game);
        gameState0=game.saveManager.loadGame(0);
        gameState1=game.saveManager.loadGame(1);
        gameState2=game.saveManager.loadGame(2);
        gameState3=game.saveManager.loadGame(3);
        rootTable.defaults().space(20);

        backgroundTexture=new Texture("MenuBackgrounds/main_menu_background.png");
        background=new Image(backgroundTexture);
        background.setFillParent(true);
        stage.addActor(background);
        background.toBack();

        title=new Label("Profiles",skin);
        divider=new Image(GameAssetManager.uiDivider);
        divider.setScaling(Scaling.fit);

        profilesTable=new Table();
        profilesTable.defaults().space(10);

        //Save 0
        save0Table=new Table();
        save0Table.defaults().space(10);
        profilesTable.add(save0Table).row();
        save0=new Label("Save Slot 1",GameAssetManager.skin);
        save0Table.add(save0).spaceRight(80);

        save0Time=new Label("Play time: "+(int)gameState0.time,GameAssetManager.skin);
        save0Table.add(save0Time).spaceRight(80);

        save0Hp=new Label("Masks: "+String.valueOf(gameState0.hp),GameAssetManager.skin);
        save0Table.add(save0Hp).spaceRight(80);

        save0Soul=new Label("Soul: "+String.valueOf(gameState0.soul),GameAssetManager.skin);
        save0Table.add(save0Soul).spaceRight(80);

        save0LoadBtn=new TextButton("Load",GameAssetManager.skin);
        save0LoadBtn.pad(5).padLeft(30).padRight(30);
        save0LoadBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                ScreenManager.getInstance().setScreen(GameScreen.getInstance(game,gameState0));
            }
        });
        save0Table.add(save0LoadBtn);

        save0ResetBtn=new TextButton("Reset",GameAssetManager.skin);
        save0ResetBtn.pad(5).padLeft(30).padRight(30);
        save0ResetBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.saveManager.saveGame(0,0,0, Knight.MAX_HP,0,0,0,0,0,0,0);
                gameState0=game.saveManager.loadGame(0);
                save0Time.setText("Play time: 0");
                save0Hp.setText("Masks: "+String.valueOf(gameState0.hp));
                save0Soul.setText("Soul: "+String.valueOf(gameState0.soul));
            }
        });
        save0Table.add(save0ResetBtn);

        //Save 1
        save1Table=new Table();
        save1Table.defaults().space(10);
        profilesTable.add(save1Table).row();
        save1=new Label("Save Slot 2",GameAssetManager.skin);
        save1Table.add(save1).spaceRight(80);

        save1Time=new Label("Play time: "+(int)gameState1.time,GameAssetManager.skin);
        save1Table.add(save1Time).spaceRight(80);

        save1Hp=new Label("Masks: "+String.valueOf(gameState1.hp),GameAssetManager.skin);
        save1Table.add(save1Hp).spaceRight(80);

        save1Soul=new Label("Soul: "+String.valueOf(gameState1.soul),GameAssetManager.skin);
        save1Table.add(save1Soul).spaceRight(80);

        save1LoadBtn=new TextButton("Load",GameAssetManager.skin);
        save1LoadBtn.pad(5).padLeft(30).padRight(30);
        save1LoadBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                ScreenManager.getInstance().setScreen(GameScreen.getInstance(game,gameState1));
            }
        });
        save1Table.add(save1LoadBtn);

        save1ResetBtn=new TextButton("Reset",GameAssetManager.skin);
        save1ResetBtn.pad(5).padLeft(30).padRight(30);
        save1ResetBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.saveManager.saveGame(1,0,0, Knight.MAX_HP,0,0,0,0,0,0,0);
                gameState1=game.saveManager.loadGame(1);
                save1Time.setText("Play time: 0");
                save1Hp.setText("Masks: "+ gameState1.hp);
                save1Soul.setText("Soul: "+ gameState1.soul);
            }
        });
        save1Table.add(save1ResetBtn);

        //Save 2
        save2Table=new Table();
        save2Table.defaults().space(10);
        profilesTable.add(save2Table).row();
        save2=new Label("Save Slot 3",GameAssetManager.skin);
        save2Table.add(save2).spaceRight(80);

        save2Time=new Label("Play time: "+(int)gameState2.time,GameAssetManager.skin);
        save2Table.add(save2Time).spaceRight(80);

        save2Hp=new Label("Masks: "+ gameState2.hp,GameAssetManager.skin);
        save2Table.add(save2Hp).spaceRight(80);

        save2Soul=new Label("Soul: "+ gameState2.soul,GameAssetManager.skin);
        save2Table.add(save2Soul).spaceRight(80);

        save2LoadBtn=new TextButton("Load",GameAssetManager.skin);
        save2LoadBtn.pad(5).padLeft(30).padRight(30);
        save2LoadBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                ScreenManager.getInstance().setScreen(GameScreen.getInstance(game,gameState2));
            }
        });
        save2Table.add(save2LoadBtn);

        save2ResetBtn=new TextButton("Reset",GameAssetManager.skin);
        save2ResetBtn.pad(5).padLeft(30).padRight(30);
        save2ResetBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.saveManager.saveGame(2,0,0, Knight.MAX_HP,0,0,0,0,0,0,0);
                gameState2=game.saveManager.loadGame(2);
                save2Time.setText("Play time: 0");
                save2Hp.setText("Masks: "+ gameState2.hp);
                save2Soul.setText("Soul: "+ gameState2.soul);
            }
        });
        save2Table.add(save2ResetBtn);

        //Save 3
        save3Table=new Table();
        save3Table.defaults().space(10);
        profilesTable.add(save3Table).row();
        save3=new Label("Save Slot 4",GameAssetManager.skin);
        save3Table.add(save3).spaceRight(80);

        save3Time=new Label("Play time: "+(int)gameState3.time,GameAssetManager.skin);
        save3Table.add(save3Time).spaceRight(80);

        save3Hp=new Label("Masks: "+String.valueOf(gameState3.hp),GameAssetManager.skin);
        save3Table.add(save3Hp).spaceRight(80);

        save3Soul=new Label("Soul: "+String.valueOf(gameState3.soul),GameAssetManager.skin);
        save3Table.add(save3Soul).spaceRight(80);

        save3LoadBtn=new TextButton("Load",GameAssetManager.skin);
        save3LoadBtn.pad(5).padLeft(30).padRight(30);
        save3LoadBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                ScreenManager.getInstance().setScreen(GameScreen.getInstance(game,gameState3));
            }
        });
        save3Table.add(save3LoadBtn);

        save3ResetBtn=new TextButton("Reset",GameAssetManager.skin);
        save3ResetBtn.pad(5).padLeft(30).padRight(30);
        save3ResetBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.saveManager.saveGame(3,0,0, Knight.MAX_HP,0,0,0,0,0,0,0);
                gameState3=game.saveManager.loadGame(3);
                save3Time.setText("Play time: 0");
                save3Hp.setText("Masks: "+String.valueOf(gameState3.hp));
                save3Soul.setText("Soul: "+String.valueOf(gameState3.soul));
            }
        });
        save3Table.add(save3ResetBtn);

        backBtn=new TextButton("Back",skin);

        rootTable.add(title).row();
        rootTable.add(divider).row();
        rootTable.add(profilesTable).row();
        rootTable.add(backBtn);
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
