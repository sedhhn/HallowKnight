package com.HallowKnight.View;

import com.HallowKnight.Controller.Managers.GameAssetManager;
import com.HallowKnight.Controller.Managers.ScreenManager;
import com.HallowKnight.HallowKnight;
import com.HallowKnight.Model.GameState;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class EndGameScreen extends MenuScreen{
    Label title;
    Label playTime;
    Label totalDamageTaken;
    Label totalEnemiesKilled;
    Label husk;
    Label crystallized;
    Label mosquito;
    Label crawler;
    TextButton respawnBtn;
    TextButton backBtn;
    public EndGameScreen(HallowKnight game, GameState gameState) {
        super(game);
        rootTable.clear();
        rootTable.setFillParent(true);
        rootTable.setBackground(new TextureRegionDrawable(GameAssetManager.backgroundTexture2));
        rootTable.defaults().space(20);

        title=new Label("Congratulation",GameAssetManager.skin);
        rootTable.add(title).row();

        int minutes=(int)gameState.time/60;
        int seconds=(int)gameState.time-minutes*60;
        playTime=new Label("Play time: "+minutes+":"+seconds,GameAssetManager.skin);
        rootTable.add(playTime).row();

        totalDamageTaken=new Label("Total damage taken: "+gameState.totalDamageTaken,GameAssetManager.skin);
        rootTable.add(totalDamageTaken).row();

        totalEnemiesKilled=new Label("Total enemies killed: "
            +(gameState.husk+gameState.crawler+gameState.crystallized+gameState.mosquito)
            ,GameAssetManager.skin);
        rootTable.add(totalEnemiesKilled).row();

        husk=new Label("Husk Hornhead: "+gameState.husk,GameAssetManager.skin);
        rootTable.add(husk).row();

        crawler=new Label("Crystal Crawler: "+gameState.crawler,GameAssetManager.skin);
        rootTable.add(crawler).row();

        mosquito=new Label("Mosquito: "+gameState.mosquito,GameAssetManager.skin);
        rootTable.add(mosquito).row();

        crystallized=new Label("Crystal Guardian: "+gameState.crystallized,skin);
        rootTable.add(crystallized).row();

        respawnBtn=new TextButton("Respawn",GameAssetManager.skin);
        respawnBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                ScreenManager.getInstance().setScreen(GameScreen.getInstance(game,gameState));
            }
        });
        rootTable.add(respawnBtn).row();

        backBtn=new TextButton("Back to Main Menu",GameAssetManager.skin);
        backBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                ScreenManager.getInstance().setScreen(new MainMenuScreen(game));
            }
        });
        rootTable.add(backBtn);
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }
}
