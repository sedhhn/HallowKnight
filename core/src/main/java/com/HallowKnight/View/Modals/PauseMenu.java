package com.HallowKnight.View.Modals;

import com.HallowKnight.Controller.GameController;
import com.HallowKnight.Controller.Managers.GameAssetManager;
import com.HallowKnight.Controller.Managers.ScreenManager;
import com.HallowKnight.HallowKnight;
import com.HallowKnight.View.GameScreen;
import com.HallowKnight.View.GuideScreen;
import com.HallowKnight.View.MainMenuScreen;
import com.HallowKnight.View.OptionsScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class PauseMenu extends Stack {
    GameController gameController;
    Table buttonsWrapper;
    TextButton continueBtn;
    TextButton settingsBtn;
    TextButton guideBtn;
    TextButton quitBtn;
    public PauseMenu(GameController gameController){
        this.gameController=gameController;
        setFillParent(true);
        buttonsWrapper=new Table();
        buttonsWrapper.defaults().space(10);
        add(buttonsWrapper);

        continueBtn=new TextButton("Continue", GameAssetManager.skin);
        continueBtn.pad(5);
        continueBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                gameController.togglePauseMenu();
            }
        });
        buttonsWrapper.add(continueBtn).row();

        settingsBtn=new TextButton("Settings",GameAssetManager.skin);
        settingsBtn.pad(5);
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
        guideBtn.pad(5);
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

        quitBtn=new TextButton("Quit to Main Menu", GameAssetManager.skin);
        quitBtn.pad(5);
        quitBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                ScreenManager.getInstance().setScreen(new MainMenuScreen(HallowKnight.hallowKnight));
                GameScreen.resetGameScreen();
            }
        });
        buttonsWrapper.add(quitBtn).row();
    }
}
