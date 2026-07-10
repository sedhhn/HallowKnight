package com.HallowKnight.View;

import com.HallowKnight.Controller.Managers.GameAssetManager;
import com.HallowKnight.Controller.Managers.SaveManager;
import com.HallowKnight.Controller.Managers.ScreenManager;
import com.HallowKnight.HallowKnight;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;

public class AchievementScreen extends MenuScreen {

    private Label title;
    private Image divider;
    private Table achievementsTable;
    private TextButton backBtn;
    private TextButton resetBtn;

    public AchievementScreen(HallowKnight game) {
        super(game);

        rootTable.clear();
        rootTable.defaults().space(10);
        rootTable.background(new TextureRegionDrawable(GameAssetManager.backgroundTexture));

        title = new Label("Achievements", GameAssetManager.skin);
        rootTable.add(title).center().row();

        divider = new Image(GameAssetManager.uiDivider);
        divider.setScaling(Scaling.fit);
        rootTable.add(divider).padTop(5).padBottom(15).row();

        achievementsTable = new Table();
        achievementsTable.defaults().pad(5).left();
        rootTable.add(achievementsTable).expandX().fillX().padLeft(30).padRight(30).row();

        constructAchievementTable();

        resetBtn=new TextButton("Reset", GameAssetManager.skin);
        resetBtn.pad(10);
        resetBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                HallowKnight.hallowKnight.saveManager.resetAchievements();
                achievementsTable.clear();
                constructAchievementTable();
            }
        });
        rootTable.add(resetBtn).padTop(20).row();

        backBtn = new TextButton("Back", GameAssetManager.skin);
        backBtn.pad(10);
        backBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                ScreenManager.getInstance().setScreen(new MainMenuScreen(game));
            }
        });
        rootTable.add(backBtn).padTop(20);
    }

    private void constructAchievementTable(){
        addAchievementRow(achievementsTable,
            "Game Completion",
            "Complete Game",
            game.saveManager.hasAchievement(SaveManager.ACHIEVEMENT_COMPLETION));

        addAchievementRow(achievementsTable,
            "Speed Run",
            "Complete game under 3 minutes",
            game.saveManager.hasAchievement(SaveManager.ACHIEVEMENT_SPEEDRUN));

        addAchievementRow(achievementsTable,
            "True Hunter",
            "Kill at least one of every enemy type",
            game.saveManager.hasAchievement(SaveManager.ACHIEVEMENT_TRUE_HUNTER));

        addAchievementRow(achievementsTable,
            "False Knight",
            "Defeat False Knight",
            game.saveManager.hasAchievement(SaveManager.ACHIEVEMENT_DEFEAT_FALSE_KNIGHT));

        addAchievementRow(achievementsTable,
            "No Damage",
            "Complete game without taking any damage",
            game.saveManager.hasAchievement(SaveManager.ACHIEVEMENT_NO_DAMAGE));
    }

    private void addAchievementRow(Table parent, String titleText, String descText, boolean unlocked) {
        // هر ردیف یک Table جداگانه خواهد داشت تا تنظیمات چیدمان داخلی آن آسان‌تر باشد
        Table row = new Table();
        row.defaults().space(5);

        // عنوان با فونت بزرگ‌تر و رنگ پررنگ‌تر
        Label titleLabel = new Label(titleText, GameAssetManager.skin);

        // توضیحات با فونت کوچک‌تر و رنگ ملایم‌تر
        Label descLabel = new Label(descText, GameAssetManager.skin);
        descLabel.setColor(0.8f, 0.8f, 0.8f, 1f);

        // چینش: عنوان در سمت چپ، توضیحات در سمت راست
        row.add(titleLabel).expandX().left();
        row.add(descLabel).right();

        // اگر دستاورد قفل است، کل ردیف را کدر می‌کنیم
        if (!unlocked) {
            row.setColor(1, 1, 1, 0.4f);
        }

        // افزودن ردیف به جدول والد با عرض کامل
        parent.add(row).fillX().row();
    }
}
