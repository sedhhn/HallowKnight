package com.HallowKnight.View;

import com.HallowKnight.Controller.Managers.AudioManager;
import com.HallowKnight.Controller.Managers.ScreenManager;
import com.HallowKnight.HallowKnight;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Scaling;

public class MainMenuScreen extends MenuScreen {
    private final Texture backgroundTexture=new Texture("MenuBackgrounds/main_menu_background.png");
    private final Texture gameLogoTexture=new Texture("MenuBackgrounds/hollow_knight_logo.png");

    private Image background;

    private Table logoTable;
    private Image gameLogo;

    private Table buttonsTable;
    private TextButton startGameBtn;
    private TextButton optionsBtn;
    private TextButton achievementsBtn;
    private TextButton guideBtn;
    private TextButton quitGameBtn;

    public MainMenuScreen(HallowKnight game){
        super(game);

        this.background=new Image(backgroundTexture);
        this.background.setFillParent(true);

        stage.addActor(background);
        background.toBack();

        logoTable=new Table();
        rootTable.add(logoTable).row();

        gameLogo=new Image(gameLogoTexture);
        gameLogo.setScaling(Scaling.fit);
        logoTable.add(gameLogo);

        buttonsTable=new Table();
        rootTable.add(buttonsTable);
        buttonsTable.center().bottom();
        buttonsTable.defaults().space(10);

        startGameBtn=new TextButton("Start Game",skin);
        optionsBtn=new TextButton("Options",skin);
        achievementsBtn=new TextButton("Achievements",skin);
        guideBtn=new TextButton("Guide",skin);
        quitGameBtn=new TextButton("Quit Game",skin);

        buttonsTable.add(startGameBtn).row();
        buttonsTable.add(optionsBtn).row();
        buttonsTable.add(achievementsBtn).row();
        buttonsTable.add(guideBtn).row();
        buttonsTable.add(quitGameBtn);


        startGameBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ScreenManager.getInstance().setScreen(new PlayGameScreen(game));
            }
        });

        optionsBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ScreenManager.getInstance().setScreen(new OptionsScreen(game));
            }
        });

        guideBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ScreenManager.getInstance().setScreen(new GuideScreen(game));
            }
        });

        quitGameBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
    }

    @Override
    public void show() {
        super.show();
        AudioManager.getInstance().playMusic("audio/Musics/53-Silksong.mp3",true,0.7f);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }
}
