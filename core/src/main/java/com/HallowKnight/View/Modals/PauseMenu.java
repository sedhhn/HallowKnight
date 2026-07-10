package com.HallowKnight.View.Modals;

import com.HallowKnight.Controller.GameController;
import com.HallowKnight.Controller.Managers.GameAssetManager;
import com.HallowKnight.Controller.Managers.SaveManager;
import com.HallowKnight.Controller.Managers.ScreenManager;
import com.HallowKnight.HallowKnight;
import com.HallowKnight.Model.GameState;
import com.HallowKnight.View.GameScreen;
import com.HallowKnight.View.GuideScreen;
import com.HallowKnight.View.MainMenuScreen;
import com.HallowKnight.View.OptionsScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class PauseMenu extends Stack {
    GameController gameController;
    Table buttonsWrapper;
    Label bossArenaTeleport;
    Label spectatorMode;
    Label emergencyHeal;
    Label refillSoulVessel;
    Label godMode;
    TextButton continueBtn;
    TextButton settingsBtn;
    TextButton guideBtn;
    TextButton quitBtn;
    public PauseMenu(GameController gameController){
        this.gameController=gameController;
        setFillParent(true);
        buttonsWrapper=new Table();
        buttonsWrapper.defaults().space(15);
        add(buttonsWrapper);

        bossArenaTeleport=new Label("Boss Arena Teleport: CTRL + B",GameAssetManager.skin);
        buttonsWrapper.add(bossArenaTeleport).row();

        spectatorMode=new Label("Spectator Mode: CTRL + Q", GameAssetManager.skin);
        buttonsWrapper.add(spectatorMode).row();

        emergencyHeal=new Label("Emergency Heal: CTRL + H",GameAssetManager.skin);
        buttonsWrapper.add(emergencyHeal).row();

        refillSoulVessel=new Label("Refill Soul Vessel: CTRL + R",GameAssetManager.skin);
        buttonsWrapper.add(refillSoulVessel).row();

        godMode=new Label("God Mode: CTRL + G",GameAssetManager.skin);
        buttonsWrapper.add(godMode).row();

        continueBtn=new TextButton("Continue", GameAssetManager.skin);
        continueBtn.pad(10);
        continueBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                gameController.togglePauseMenu();
            }
        });
        buttonsWrapper.add(continueBtn).row();

        settingsBtn=new TextButton("Settings",GameAssetManager.skin);
        settingsBtn.pad(10);
        settingsBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                ScreenManager.getInstance().setScreen(
                    new OptionsScreen(HallowKnight.hallowKnight,gameController.getGameScreen())
                );
            }
        });
        buttonsWrapper.add(settingsBtn).row();

        guideBtn=new TextButton("Guide", GameAssetManager.skin);
        guideBtn.pad(10);
        guideBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                ScreenManager.getInstance().setScreen(
                    new GuideScreen(HallowKnight.hallowKnight,gameController.getGameScreen())
                );
            }
        });
        buttonsWrapper.add(guideBtn).row();

        quitBtn=new TextButton("Save & Quit", GameAssetManager.skin);
        quitBtn.pad(10);
        quitBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                saveGame();
                ScreenManager.getInstance().setScreen(new MainMenuScreen(HallowKnight.hallowKnight));
                GameScreen.resetGameScreen();
            }
        });
        buttonsWrapper.add(quitBtn).row();
    }

    public void saveGame(float x, float y){
        HallowKnight.hallowKnight.saveManager.saveGame(
            GameScreen.getInstance().getGameState().save,
            x,
            y,
            gameController.getKnight().getHp(),
            (int) gameController.getKnight().getSoul(),
            gameController.getPlayTime(),
            gameController.getGameScreen().getGameState().totalDamageTaken,
            gameController.getGameScreen().getGameState().crystallized,
            gameController.getGameScreen().getGameState().mosquito,
            gameController.getGameScreen().getGameState().husk,
            gameController.getGameScreen().getGameState().crawler
        );
        GameState gameState=gameController.getGameScreen().getGameState();
        if (gameState.mosquito>0 && gameState.husk>0 && gameState.crawler>0 && gameState.crystallized>0){
            HallowKnight.hallowKnight.saveManager.saveAchievement(SaveManager.ACHIEVEMENT_TRUE_HUNTER);
        }
    }

    public void saveGame(){
        saveGame(gameController.getKnight().b2Body.getPosition().x*HallowKnight.PPM,
            gameController.getKnight().b2Body.getPosition().y*HallowKnight.PPM);
    }
}
