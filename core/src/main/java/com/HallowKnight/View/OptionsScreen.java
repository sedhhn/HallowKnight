package com.HallowKnight.View;

import com.HallowKnight.Controller.Managers.GameAssetManager;
import com.HallowKnight.Controller.Managers.ScreenManager;
import com.HallowKnight.Controller.SettingsController;
import com.HallowKnight.HallowKnight;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.Array;


public class OptionsScreen extends MenuScreen{
    SettingsController controller;
    private MenuScreen lastScreen;
    private Table contentTable;

    Image background;
    private Label titleLabel;
    private Image uiDivider;

    private Label musicVolumeLabel;
    private Slider musicVolumeSlider;
    private CheckBox musicMuteCheck;

    private Label sfxVolumeLabel;
    private Slider sfxVolumeSlider;
    private CheckBox sfxMuteCheck;

    private Label resolutionLabel;
    private SelectBox<String> resolutionSelect;

    private CheckBox fullscreenCheck;

    private TextButton resetBtn;

    private TextButton backBtn;

    private static final int[][] RESOLUTIONS = {
        {640, 480},
        {800, 600},
        {1024, 768},
        {1280, 720},
        {1280, 960},
        {1366, 768},
        {1600, 900},
        {1920, 1080}
    };

    public OptionsScreen(HallowKnight game, MenuScreen lastScreen) {
        super(game);
        this.lastScreen=lastScreen;
        //controller
        controller=new SettingsController(game);

        //Title
        titleLabel=new Label("Settings",skin);
        uiDivider=new Image(GameAssetManager.uiDivider);
        background=new Image(GameAssetManager.backgroundTexture);

        //Music Settings
        musicVolumeLabel=new Label("Music",skin);
        musicVolumeSlider=new Slider(0f,1f,0.05f,false,skin);
        musicVolumeSlider.setValue(game.getsettings().getMusicVolume());
        musicVolumeSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                controller.setMusicVolume(musicVolumeSlider.getValue());
            }
        });
        musicMuteCheck=new CheckBox("Mute Music",skin);
        musicMuteCheck.setChecked(game.getsettings().isMusicMuted());
        musicMuteCheck.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                controller.setMusicMuted(musicMuteCheck.isChecked());
            }
        });

        //SFX Settings
        sfxVolumeLabel=new Label("SFX",skin);
        sfxVolumeSlider=new Slider(0f,1f,0.05f,false,skin);
        sfxVolumeSlider.setValue(game.getsettings().getSfxVolume());
        sfxVolumeSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                controller.setSfxVolume(sfxVolumeSlider.getValue());
            }
        });
        sfxMuteCheck=new CheckBox("Mute SFX",skin);
        sfxMuteCheck.setChecked(game.getsettings().isMusicMuted());
        sfxMuteCheck.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                controller.setSfxMuted(sfxMuteCheck.isChecked());
            }
        });

        //Resolution Settings
        resolutionLabel=new Label("Resolution",skin);
        Array<String> resolutionItems = new Array<>();
        for (int[] res : RESOLUTIONS) {
            resolutionItems.add(res[0] + " x " + res[1]);
        }
        resolutionSelect=new SelectBox<>(skin);
        resolutionSelect.setItems(resolutionItems);
        resolutionSelect.setSelected(getCurrentResolutionString());
        resolutionSelect.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                int index = resolutionSelect.getSelectedIndex();
                if (index >= 0 && index < RESOLUTIONS.length) {
                    controller.setResolution(RESOLUTIONS[index][0], RESOLUTIONS[index][1]);
                }
            }
        });

        //Fullscreen Settings
        fullscreenCheck=new CheckBox("Fullscreen",skin);
        fullscreenCheck.setChecked(game.getsettings().isFullscreen());
        fullscreenCheck.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                controller.setFullscreen(fullscreenCheck.isChecked());
            }
        });

        //Reset Button
        resetBtn=new TextButton("Reset",skin);
        resetBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.resetSettings();
                refreshAllComponents();
            }
        });

        //Back Button
        backBtn=new TextButton("Back",skin);
        backBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (lastScreen instanceof GameScreen gameScreen){
                    ScreenManager.getInstance().setScreen(GameScreen.getInstance(game,gameScreen.getGameState()));
                } else {
                    ScreenManager.getInstance().setScreen(new MainMenuScreen(game));
                }
            }
        });

        //background
        stage.addActor(background);
        background.setFillParent(true);
        background.toBack();

        //=======Table arrangement=========
        rootTable.clear();
        rootTable.pad(60);
        contentTable=new Table();
        rootTable.add(contentTable).expand().fill();
        contentTable.defaults().pad(8);
        contentTable.add(titleLabel).colspan(8).row();
        uiDivider.setScaling(Scaling.fit);
        contentTable.add(uiDivider).colspan(8).row();
        //music settings
        contentTable.add(musicVolumeLabel).colspan(1);
        contentTable.add(musicVolumeSlider).width(400).colspan(6);
        contentTable.add(musicMuteCheck).colspan(1).row();
        //sfx settings
        contentTable.add(sfxVolumeLabel).colspan(1);
        contentTable.add(sfxVolumeSlider).width(400).colspan(6);
        contentTable.add(sfxMuteCheck).colspan(1).row();
        //resolution settings
        contentTable.add(resolutionLabel).colspan(1);
        contentTable.add(resolutionSelect).width(400).colspan(7).row();
        //fullscreen settings
        contentTable.add(fullscreenCheck).colspan(8).row();

        contentTable.add(resetBtn).colspan(8).row();
        contentTable.add(backBtn).colspan(8);
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }

    private String getCurrentResolutionString() {
        int w = game.getsettings().getGameResolutionWidth();
        int h = game.getsettings().getGameResolutionHeight();
        return w + " x " + h;
    }

    private void refreshAllComponents(){
        //Music Settings
        musicVolumeSlider.setValue(game.getsettings().getMusicVolume());
        musicMuteCheck.setChecked(game.getsettings().isMusicMuted());

        //SFX Settings
        sfxVolumeSlider.setValue(game.getsettings().getSfxVolume());
        sfxMuteCheck.setChecked(game.getsettings().isMusicMuted());

        //Resolution Settings
        resolutionSelect.setSelected(getCurrentResolutionString());

        //Fullscreen Settings
        fullscreenCheck.setChecked(game.getsettings().isFullscreen());
    }
}
