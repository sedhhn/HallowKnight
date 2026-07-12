package com.HallowKnight;

import com.HallowKnight.Controller.Managers.GameAssetManager;
import com.HallowKnight.Controller.Managers.SaveManager;
import com.HallowKnight.Controller.Managers.ScreenManager;
import com.HallowKnight.Model.Settings;
import com.HallowKnight.View.MainMenuScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class HallowKnight extends Game {
    public static final float PPM=100; //Pixels Per Meter
    public static final float BOSS_ROOM_MIN_X=592;
    public static final float BOSS_ROOM_MAX_X=804;
    public static final float MAP_CENTER_X=600;
    public static final float MAP_TRANSITION_WIDTH=200;
    public static HallowKnight hallowKnight;
    public Skin skin;
    private SpriteBatch batch;
    private Settings settings;
    public SaveManager saveManager;

    @Override
    public void create() {
        GameAssetManager.load();
        settings=new Settings();
        hallowKnight =this;
        batch = new SpriteBatch();
        skin=new Skin(Gdx.files.internal("ui/uiskin.json"));
        ScreenManager.getInstance().initialize(this);
        ScreenManager.getInstance().setScreen(new MainMenuScreen(this));
        saveManager=new SaveManager();
    }

    @Override
    public void render() {
        super.render();
    }


    @Override
    public void dispose() {
        batch.dispose();
    }

    public SpriteBatch getBatch(){
        return this.batch;
    }

    public Settings getsettings(){
        return settings;
    }
}
