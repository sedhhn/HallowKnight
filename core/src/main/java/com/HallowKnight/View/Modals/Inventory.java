package com.HallowKnight.View.Modals;

import com.HallowKnight.Controller.Managers.GameAssetManager;
import com.HallowKnight.Model.Charms.CharmType;
import com.HallowKnight.Model.Knight.Knight;
import com.HallowKnight.View.MenuScreen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;

public class Inventory extends Stack {
    Knight knight;

    Table rootTable;

    Table dashmasterWrapper;
    Table dashmasterIconWrapper;
    Image dashmasterIcon;
    Table dashmasterTextsWrapper;
    Label dashmasterName;
    Label dashmasterDescription;

    Table heavyBlowWrapper;
    Table heavyBlowIconWrapper;
    Image heavyBlowIcon;
    Table heavyBlowTextsWrapper;
    Label heavyBlowName;
    Label heavyBlowDescription;

    Table quickFocusWrapper;
    Table quickFocusIconWrapper;
    Image quickFocusIcon;
    Table quickFocusTextsWrapper;
    Label quickFocusName;
    Label quickFocusDescription;

    Table quickSlashWrapper;
    Table quickSlashIconWrapper;
    Image quickSlashIcon;
    Table quickSlashTextsWrapper;
    Label quickSlashName;
    Label quickSlashDescription;

    Table soulCatcherWrapper;
    Table soulCatcherIconWrapper;
    Image soulCatcherIcon;
    Table soulCatcherTextsWrapper;
    Label soulCatcherName;
    Label soulCatcherDescription;

    Table unbreakableStrengthWrapper;
    Table unbreakableStrengthIconWrapper;
    Image unbreakableStrengthIcon;
    Table unbreakableStrengthTextsWrapper;
    Label unbreakableStrengthName;
    Label unbreakableStrengthDescription;

    public Inventory(Knight knight){
        this.knight=knight;
        rootTable=new Table();
        rootTable.setFillParent(true);
        Pixmap pixmap=new Pixmap(1,1, Pixmap.Format.RGBA4444);
        pixmap.setColor(0,0,0,0.5f);
        pixmap.fill();
        TextureRegionDrawable bg=new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));
        rootTable.setBackground(bg);
        rootTable.defaults().space(15);
        add(rootTable);

        //Dashmaster
        dashmasterWrapper=new Table();
        dashmasterWrapper.defaults().space(5);

        dashmasterIconWrapper=new Table();
        dashmasterIcon=new Image(GameAssetManager.dashmaster);
        dashmasterIcon.setScaling(Scaling.fit);
        if (!knight.hasCharm(CharmType.DASHMASTER)) dashmasterWrapper.setColor(1,1,1,0.4f);
        dashmasterIconWrapper.add(dashmasterIcon);
        dashmasterWrapper.add(dashmasterIcon).colspan(1);

        dashmasterTextsWrapper=new Table();
        dashmasterName=new Label("Dashmaster",GameAssetManager.skin);
        dashmasterTextsWrapper.add(dashmasterName).row();
        dashmasterDescription=new Label("Reduces Dash cooldown, allowing you to dash more frequently.",GameAssetManager.skin);
        dashmasterTextsWrapper.add(dashmasterDescription).row();
        dashmasterTextsWrapper.setWidth(300);
        dashmasterWrapper.add(dashmasterTextsWrapper).colspan(2);

        rootTable.add(dashmasterWrapper).colspan(2);
        dashmasterWrapper.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if (knight.hasCharm(CharmType.DASHMASTER)){
                    knight.unEquipCharm(CharmType.DASHMASTER);
                    dashmasterWrapper.setColor(1,1,1,0.4f);
                } else if (knight.charmsCount()<3) {
                    knight.equipCharm(CharmType.DASHMASTER);
                    dashmasterWrapper.setColor(1,1,1,1);
                }
            }
        });

        /*//Heavy Blow
        heavyBlowWrapper=new Table();
        heavyBlowWrapper.defaults().space(5);

        heavyBlowIconWrapper=new Table();
        heavyBlowIcon=new Image(GameAssetManager.heavyBlow);
        heavyBlowIcon.setScaling(Scaling.fit);
        if (!knight.hasCharm(CharmType.HEAVY_BLOW)) heavyBlowWrapper.setColor(1,1,1,0.4f);
        heavyBlowIconWrapper.add(heavyBlowIcon);
        heavyBlowWrapper.add(heavyBlowIcon).colspan(1);

        heavyBlowTextsWrapper=new Table();
        heavyBlowName=new Label("Heavy Blow",GameAssetManager.skin);
        heavyBlowTextsWrapper.add(heavyBlowName).row();
        heavyBlowDescription=new Label("Test",GameAssetManager.skin);
        heavyBlowTextsWrapper.add(heavyBlowDescription).row();
        heavyBlowTextsWrapper.setWidth(300);
        heavyBlowWrapper.add(heavyBlowTextsWrapper).colspan(2);

        rootTable.add(heavyBlowWrapper).colspan(1).row();
        heavyBlowWrapper.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if (knight.hasCharm(CharmType.HEAVY_BLOW)){
                    knight.unEquipCharm(CharmType.HEAVY_BLOW);
                    heavyBlowWrapper.setColor(1,1,1,0.4f);
                } else if (knight.charmsCount()<3) {
                    knight.equipCharm(CharmType.HEAVY_BLOW);
                    heavyBlowWrapper.setColor(1,1,1,1);
                }
            }
        });*/

        //Quick Focus
        quickFocusWrapper=new Table();
        quickFocusWrapper.defaults().space(5);

        quickFocusIconWrapper=new Table();
        quickFocusIcon=new Image(GameAssetManager.quickFocus);
        quickFocusIcon.setScaling(Scaling.fit);
        if (!knight.hasCharm(CharmType.QUICK_FOCUS)) quickFocusWrapper.setColor(1,1,1,0.4f);
        quickFocusIconWrapper.add(quickFocusIcon);
        quickFocusWrapper.add(quickFocusIcon).colspan(1);

        quickFocusTextsWrapper=new Table();
        quickFocusName=new Label("Quick Focus",GameAssetManager.skin);
        quickFocusTextsWrapper.add(quickFocusName).row();
        quickFocusDescription=new Label("Speeds up the Focus channeling time.",GameAssetManager.skin);
        quickFocusTextsWrapper.add(quickFocusDescription).row();
        quickFocusTextsWrapper.setWidth(300);
        quickFocusWrapper.add(quickFocusTextsWrapper).colspan(2);

        rootTable.add(quickFocusWrapper).colspan(2);
        quickFocusWrapper.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if (knight.hasCharm(CharmType.QUICK_FOCUS)){
                    knight.unEquipCharm(CharmType.QUICK_FOCUS);
                    quickFocusWrapper.setColor(1,1,1,0.4f);
                } else if (knight.charmsCount()<3) {
                    knight.equipCharm(CharmType.QUICK_FOCUS);
                    quickFocusWrapper.setColor(1,1,1,1);
                }
            }
        });

        //Quick Slash
        quickSlashWrapper=new Table();
        quickSlashWrapper.defaults().space(5);

        quickSlashIconWrapper=new Table();
        quickSlashIcon=new Image(GameAssetManager.quickSlash);
        quickSlashIcon.setScaling(Scaling.fit);
        if (!knight.hasCharm(CharmType.QUICK_SLASH)) quickSlashWrapper.setColor(1,1,1,0.4f);
        quickSlashIconWrapper.add(quickSlashIcon);
        quickSlashWrapper.add(quickSlashIcon).colspan(1);

        quickSlashTextsWrapper=new Table();
        quickSlashName=new Label("Quick Slash",GameAssetManager.skin);
        quickSlashTextsWrapper.add(quickSlashName).row();
        quickSlashDescription=new Label("Increases your attack speed.",GameAssetManager.skin);
        quickSlashTextsWrapper.add(quickSlashDescription).row();
        quickSlashTextsWrapper.setWidth(300);
        quickSlashWrapper.add(quickSlashTextsWrapper).colspan(2);

        rootTable.add(quickSlashWrapper).colspan(2).row();
        quickSlashWrapper.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if (knight.hasCharm(CharmType.QUICK_SLASH)){
                    knight.unEquipCharm(CharmType.QUICK_SLASH);
                    quickSlashWrapper.setColor(1,1,1,0.4f);
                } else if (knight.charmsCount()<3) {
                    knight.equipCharm(CharmType.QUICK_SLASH);
                    quickSlashWrapper.setColor(1,1,1,1);
                }
            }
        });

        //Soul Catcher
        soulCatcherWrapper=new Table();
        soulCatcherWrapper.defaults().space(5);

        soulCatcherIconWrapper=new Table();
        soulCatcherIcon=new Image(GameAssetManager.soulCatcher);
        soulCatcherIcon.setScaling(Scaling.fit);
        if (!knight.hasCharm(CharmType.SOUL_CATCHER)) soulCatcherWrapper.setColor(1,1,1,0.4f);
        soulCatcherIconWrapper.add(soulCatcherIcon);
        soulCatcherWrapper.add(soulCatcherIcon).colspan(1);

        soulCatcherTextsWrapper=new Table();
        soulCatcherName=new Label("Soul Catcher",GameAssetManager.skin);
        soulCatcherTextsWrapper.add(soulCatcherName).row();
        soulCatcherDescription=new Label("Increases Soul gained per successful Nail hit.",GameAssetManager.skin);
        soulCatcherTextsWrapper.add(soulCatcherDescription).row();
        soulCatcherTextsWrapper.setWidth(300);
        soulCatcherWrapper.add(soulCatcherTextsWrapper).colspan(2);

        rootTable.add(soulCatcherWrapper).colspan(3);
        soulCatcherWrapper.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if (knight.hasCharm(CharmType.SOUL_CATCHER)){
                    knight.unEquipCharm(CharmType.SOUL_CATCHER);
                    soulCatcherWrapper.setColor(1,1,1,0.4f);
                } else if (knight.charmsCount()<3) {
                    knight.equipCharm(CharmType.SOUL_CATCHER);
                    soulCatcherWrapper.setColor(1,1,1,1);
                }
            }
        });

        //Unbreakable Strength
        unbreakableStrengthWrapper=new Table();
        unbreakableStrengthWrapper.defaults().space(5);

        unbreakableStrengthIconWrapper=new Table();
        unbreakableStrengthIcon=new Image(GameAssetManager.unbreakableStrength);
        unbreakableStrengthIcon.setScaling(Scaling.fit);
        if (!knight.hasCharm(CharmType.UNBREAKABLE_STRENGTH)) unbreakableStrengthWrapper.setColor(1,1,1,0.4f);
        unbreakableStrengthIconWrapper.add(unbreakableStrengthIcon);
        unbreakableStrengthWrapper.add(unbreakableStrengthIcon).colspan(1);

        unbreakableStrengthTextsWrapper=new Table();
        unbreakableStrengthName=new Label("Unbreakable Strength",GameAssetManager.skin);
        unbreakableStrengthTextsWrapper.add(unbreakableStrengthName).row();
        unbreakableStrengthDescription=new Label("Boosts your Nail damage significantly.",GameAssetManager.skin);
        unbreakableStrengthTextsWrapper.add(unbreakableStrengthDescription).row();
        unbreakableStrengthTextsWrapper.setWidth(300);
        unbreakableStrengthWrapper.add(unbreakableStrengthTextsWrapper).colspan(2);

        rootTable.add(unbreakableStrengthWrapper).colspan(3).row();
        unbreakableStrengthWrapper.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if (knight.hasCharm(CharmType.UNBREAKABLE_STRENGTH)){
                    knight.unEquipCharm(CharmType.UNBREAKABLE_STRENGTH);
                    unbreakableStrengthWrapper.setColor(1,1,1,0.4f);
                } else if (knight.charmsCount()<3) {
                    knight.equipCharm(CharmType.UNBREAKABLE_STRENGTH);
                    unbreakableStrengthWrapper.setColor(1,1,1,1);
                }
            }
        });
    }
}
