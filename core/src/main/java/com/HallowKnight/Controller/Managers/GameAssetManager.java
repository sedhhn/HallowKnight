package com.HallowKnight.Controller.Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;

public class GameAssetManager {
    public static Texture backgroundTexture;
    public static Texture logoTexture;
    public static Texture uiDivider;
    public static Skin skin;

    //========= knight ==============
    public static TextureAtlas knightIdleAtlas;
    public static TextureAtlas knightRunningAtlas;
    public static TextureAtlas knightAtlas;
    public static TextureAtlas knightSlashEffect;
    public static TextureAtlas knightJumpAtlas;

    //========= HUD =============
    public static TextureAtlas soulContainer;
    public static TextureAtlas knightHp;

    //========= enemies ==========
    public static TextureAtlas crawlidAtlas;
    public static TextureAtlas huskHornheadAtlas;
    public static TextureAtlas crystallizedAtlas;

    //========= effects ==========
    public static TextureAtlas crystalLaserAtlas;

    public static void load() {
        backgroundTexture = new Texture("MenuBackgrounds/main_menu_background.png");
        logoTexture = new Texture("MenuBackgrounds/hollow_knight_logo.png");
        uiDivider=new Texture("Ui/Ui_Divider.png");
        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
        knightIdleAtlas=new TextureAtlas("Animations/Knight/knightIdle.atlas");
        knightRunningAtlas=new TextureAtlas("Animations/Knight/knightRunning.atlas");
        knightAtlas=new TextureAtlas("Animations/Knight/knight.atlas");
        knightSlashEffect=new TextureAtlas("Animations/Knight/SlashEffect.atlas");
        knightJumpAtlas=new TextureAtlas("Animations/Knight/Jump.atlas");
        soulContainer=new TextureAtlas("Animations/HUD/SoulContainer.atlas");
        knightHp=new TextureAtlas("Animations/HUD/KnightHp.atlas");
        crawlidAtlas=new TextureAtlas("Animations/Enemies/Crawlid.atlas");
        huskHornheadAtlas=new TextureAtlas("Animations/Enemies/Husk_Hornhead.atlas");
        crystallizedAtlas=new TextureAtlas("Animations/Enemies/Crystallized.atlas");
        crystalLaserAtlas=new TextureAtlas("Animations/Effects/CrystalLaser.atlas");
    }

    public static void dispose() {
        backgroundTexture.dispose();
        logoTexture.dispose();
        uiDivider.dispose();
        skin.dispose();
    }
}
