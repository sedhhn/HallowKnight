package com.HallowKnight.Controller.Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;

public class GameAssetManager {
    public static Texture backgroundTexture;
    public static Texture backgroundTexture2;
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
    public static TextureAtlas healthBar;
    public static TextureAtlas knightHp;
    public static Texture fullSoulOrb;

    //========== Charms ===========
    public static Texture dashmaster;
    public static Texture heavyBlow;
    public static Texture quickFocus;
    public static Texture quickSlash;
    public static Texture sharpShadow;
    public static Texture soulCatcher;
    public static Texture unbreakableStrength;
    public static Texture voidHeart;

    //========= enemies ==========
    public static TextureAtlas crawlidAtlas;
    public static TextureAtlas huskHornheadAtlas;
    public static TextureAtlas crystallizedAtlas;
    public static TextureAtlas mosquitoAtlas;
    public static TextureAtlas crystalCrawlerAtlas;

    //========= NPCs ============
    public static TextureAtlas zoteAtlas;

    //========= Bosses ============
    public static TextureAtlas falseKnight;
    public static Texture bossArenaBarrier;

    //========= effects ==========
    public static TextureAtlas crystalLaserAtlas;
    public static TextureAtlas shockwaveAtlas;
    public static TextureAtlas soulScream;
    public static TextureAtlas soulBall;
    public static TextureAtlas torch;

    //========== Sounds ============
    //Musics:
    public static String crystalPeakMusic="audio/Musics/11 - Crystal Peak.mp3";
    public static String crossroadsMusic="audio/Musics/03 - Crossroads.mp3";
    public static String zote0="audio/SFX/Zote/Zote_01.wav";
    public static String zote1="audio/SFX/Zote/Zote_02.wav";
    public static String zote2="audio/SFX/Zote/Zote_03.wav";
    public static String zote3="audio/SFX/Zote/Zote_04.wav";
    public static String zote4="audio/SFX/Zote/Zote_05.wav";
    public static String nailAttack="audio/SFX/hero_evade.wav";
    public static String knightDamage="audio/SFX/hero_damage.wav";
    public static String enemyDamage="audio/SFX/enemy_damage.wav";
    public static String soulGain="audio/SFX/spa_heal.wav";
    public static String knightFocus="audio/SFX/focus_health_charging.wav";

    public static void load() {
        backgroundTexture = new Texture("MenuBackgrounds/main_menu_background.png");
        backgroundTexture2=new Texture("MenuBackgrounds/background2.png");
        logoTexture = new Texture("MenuBackgrounds/hollow_knight_logo.png");
        uiDivider=new Texture("Ui/Ui_Divider.png");
        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
        knightIdleAtlas=new TextureAtlas("Animations/Knight/knightIdle.atlas");
        knightRunningAtlas=new TextureAtlas("Animations/Knight/knightRunning.atlas");
        knightAtlas=new TextureAtlas("Animations/Knight/knight.atlas");
        knightSlashEffect=new TextureAtlas("Animations/Knight/SlashEffect.atlas");
        knightJumpAtlas=new TextureAtlas("Animations/Knight/Jump.atlas");
        healthBar=new TextureAtlas("Animations/HUD/SoulContainer.atlas");
        knightHp=new TextureAtlas("Animations/HUD/KnightHp.atlas");
        crawlidAtlas=new TextureAtlas("Animations/Enemies/Crawlid.atlas");
        huskHornheadAtlas=new TextureAtlas("Animations/Enemies/Husk_Hornhead.atlas");
        crystallizedAtlas=new TextureAtlas("Animations/Enemies/Crystallized.atlas");
        crystalLaserAtlas=new TextureAtlas("Animations/Effects/CrystalLaser.atlas");
        mosquitoAtlas=new TextureAtlas("Animations/Enemies/Mosquito.atlas");
        crystalCrawlerAtlas=new TextureAtlas("Animations/Enemies/Crystal_Crawler.atlas");
        zoteAtlas=new TextureAtlas("Animations/NPCs/Zote.atlas");
        //========== BOSS =============
        falseKnight=new TextureAtlas("Animations/FalseKnight/FalseKnight.atlas");
        bossArenaBarrier=new Texture("Sprites/Architecture & Environment/pillar_cell.png");
        shockwaveAtlas=new TextureAtlas("Animations/Effects/Shockwave.atlas");
        fullSoulOrb=new Texture("Sprites/HUD/SoulOrb_Full.png");
        soulScream=new TextureAtlas("Animations/Effects/SoulScream.atlas");
        soulBall=new TextureAtlas("Animations/Effects/SoulBall.atlas");
        //========= Charms ==========
        dashmaster=new Texture("Sprites/Inventory & UI/Charms/Dashmaster.png");
        heavyBlow=new Texture("Sprites/Inventory & UI/Charms/Heavy Blow.png");
        quickFocus=new Texture("Sprites/Inventory & UI/Charms/Quick Focus.png");
        quickSlash=new Texture("Sprites/Inventory & UI/Charms/Quick Slash.png");
        sharpShadow=new Texture("Sprites/Inventory & UI/Charms/Sharp Shadow.png");
        soulCatcher=new Texture("Sprites/Inventory & UI/Charms/Soul Catcher.png");
        unbreakableStrength=new Texture("Sprites/Inventory & UI/Charms/Unbreakable Strength.png");
        voidHeart=new Texture("Sprites/Inventory & UI/Charms/Void Heart.png");
        //========== Effects ===========
        torch=new TextureAtlas("Animations/Effects/Torch.atlas");
    }

    public static void dispose() {
        backgroundTexture.dispose();
        logoTexture.dispose();
        uiDivider.dispose();
        skin.dispose();
    }
}
