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


public class OptionsScreen extends MenuScreen{
    SettingsController controller;
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

    private TextButton resetBtn;

    private TextButton backBtn;
    public OptionsScreen(HallowKnight game) {
        super(game);
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
                ScreenManager.getInstance().setScreen(new MainMenuScreen(game));
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

    private void refreshAllComponents(){
        //Music Settings
        musicVolumeSlider.setValue(game.getsettings().getMusicVolume());
        musicMuteCheck.setChecked(game.getsettings().isMusicMuted());

        //SFX Settings
        sfxVolumeSlider.setValue(game.getsettings().getSfxVolume());
        sfxMuteCheck.setChecked(game.getsettings().isMusicMuted());
    }
}
