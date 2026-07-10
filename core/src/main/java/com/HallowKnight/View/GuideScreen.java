package com.HallowKnight.View;

import com.HallowKnight.Controller.Managers.GameAssetManager;
import com.HallowKnight.Controller.Managers.ScreenManager;
import com.HallowKnight.HallowKnight;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Scaling;

public class GuideScreen extends MenuScreen {

    private MenuScreen lastScreen;

    private Image background;
    private Label guideTitle;
    private Image divider;
    private Table contentTable;
    private ScrollPane scrollPane;
    private TextButton backBtn;

    //Key Bindings
    private Label keyBindingsTitle;
    Label keyBindingsLabel;
    Label attackBindingLabel;
    Label moveRightBindingLabel;
    Label moveLeftBindingLabel;
    Label jumpBindingLabel;
    Label FocusBindingLabel;
    Label interactBindingLabel;
    Label vengefulSpritBindingLabel;
    Label howlingWraithBindingLabel;
    Label dashBindingLabel;
    Label inventoryBindingLabel;
    Label pauseMenuLabel;

    //CheatCodes
    private Label cheatCodesTitle;
    Label bossArenaTeleportLabel;
    Label spectatorModeLabel;
    Label emergencyHealLabel;
    Label refillSoulVesselLabel;
    Label godModeLabel;

    private final String[][] abilities = {
        {"Masks (Health System)", "Your health is represented by Masks. Each hit from an enemy or hazard costs 1 Mask. When all Masks are lost, you respawn at the last safe platform with full health."},
        {"Soul Vessel", "Soul is gained by striking enemies with your Nail (11 Soul per hit). Max capacity is 99 Soul. Soul is used to cast spells and heal."},
        {"Focus (Heal)", "Hold the Focus key to channel Soul and heal 1 Mask. The channel takes 1.5 seconds. If you are hit or move during channeling, the process is interrupted and no Soul is consumed."},
        {"Dash", "A quick horizontal dash that allows you to evade enemies and traverse gaps. Can be used once per jump (resets upon landing or pogo)."},
        {"Mantis Claw / Wall Slide", "While airborne, hold towards a wall to slide down it slowly. Release or move away to drop."},
        {"Pogo Jump", "While airborne, press Down + Attack to strike downward. If you hit an enemy or a spike, you bounce upward and reset your dash and double-jump."},
        {"Vengeful Spirit", "A spell that fires a fast, horizontal projectile in the direction you're facing. It passes through enemies and deals damage. Costs 1 Soul Vessel (33 Soul)."},
        {"Howling Wraiths", "A spell that creates an upward burst of energy above the Knight. It hits enemies 3 times in quick succession. Costs 1 Soul Vessel (33 Soul)."},
        {"Nail (Melee Attack)", "Your primary weapon. Strike enemies in close range. Each successful hit grants Soul and deals damage."},
        {"Knockback", "Enemies are pushed back slightly when hit by your Nail, giving you breathing room in combat."},
        {"Invincibility Frames", "After taking damage, you become briefly invulnerable (flashing effect) for 1 second to prevent rapid consecutive hits."}
    };

    public GuideScreen(HallowKnight game, MenuScreen lastScreen) {
        super(game);
        this.lastScreen=lastScreen;

        background = new Image(GameAssetManager.backgroundTexture);
        stage.addActor(background);
        background.setFillParent(true);
        background.toBack();

        guideTitle = new Label("Guide", skin);
        divider = new Image(GameAssetManager.uiDivider);
        divider.setScaling(Scaling.fit);

        // ========== Key Bindings ==========
        keyBindingsTitle = new Label("Key Bindings", skin, "subtitle");
        keyBindingsLabel = new Label("Action", skin);

        attackBindingLabel = new Label("X", skin);
        moveRightBindingLabel = new Label("Right Arrow", skin);
        moveLeftBindingLabel = new Label("Left Arrow", skin);
        jumpBindingLabel = new Label("Up Arrow", skin);
        FocusBindingLabel = new Label("A", skin);
        interactBindingLabel = new Label("Z", skin);
        vengefulSpritBindingLabel = new Label("S", skin);
        howlingWraithBindingLabel = new Label("S + Up Arrow", skin);
        dashBindingLabel = new Label("C", skin);
        inventoryBindingLabel = new Label("I", skin);
        pauseMenuLabel = new Label("Esc", skin);

        // ========== Cheat Codes ==========
        cheatCodesTitle = new Label("Cheat Codes", skin, "subtitle");
        bossArenaTeleportLabel = new Label("CTRL + B", skin);
        spectatorModeLabel = new Label("CTRL + Q", skin);
        emergencyHealLabel = new Label("CTRL + H", skin);
        refillSoulVesselLabel = new Label("CTRL + R", skin);
        godModeLabel = new Label("CTRL + G", skin);

        // ========== Back Button ==========
        backBtn = new TextButton("Back", skin);
        backBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (lastScreen instanceof GameScreen gameScreen){
                    ScreenManager.getInstance().setScreen(GameScreen.getInstance(game,gameScreen.getGameState()));
                } else {
                    ScreenManager.getInstance().setScreen(new MainMenuScreen(game));
                }
            }
        });

        // ========== Layout ==========
        rootTable.clear();
        rootTable.pad(40);

        contentTable = new Table();
        contentTable.defaults().pad(6).left();

        // Title
        contentTable.add(guideTitle).colspan(2).center().padBottom(4).row();
        contentTable.add(divider).colspan(2).fillX().padBottom(16).row();

        // --- Key Bindings Section ---
        contentTable.add(keyBindingsTitle).colspan(2).padBottom(8).row();

        contentTable.add(new Label("Attack", skin)).padLeft(20);
        contentTable.add(attackBindingLabel).padLeft(40).row();

        contentTable.add(new Label("Move Right", skin)).padLeft(20);
        contentTable.add(moveRightBindingLabel).padLeft(40).row();

        contentTable.add(new Label("Move Left", skin)).padLeft(20);
        contentTable.add(moveLeftBindingLabel).padLeft(40).row();

        contentTable.add(new Label("Jump", skin)).padLeft(20);
        contentTable.add(jumpBindingLabel).padLeft(40).row();

        contentTable.add(new Label("Focus", skin)).padLeft(20);
        contentTable.add(FocusBindingLabel).padLeft(40).row();

        contentTable.add(new Label("Interact", skin)).padLeft(20);
        contentTable.add(interactBindingLabel).padLeft(40).row();

        contentTable.add(new Label("Vengeful Spirit", skin)).padLeft(20);
        contentTable.add(vengefulSpritBindingLabel).padLeft(40).row();

        contentTable.add(new Label("Howling Wraiths", skin)).padLeft(20);
        contentTable.add(howlingWraithBindingLabel).padLeft(40).row();

        contentTable.add(new Label("Dash", skin)).padLeft(20);
        contentTable.add(dashBindingLabel).padLeft(40).row();

        contentTable.add(new Label("Inventory", skin)).padLeft(20);
        contentTable.add(inventoryBindingLabel).padLeft(40).row();

        contentTable.add(new Label("Pause Menu", skin)).padLeft(20);
        contentTable.add(pauseMenuLabel).padLeft(40).row();

        contentTable.add(new Label("", skin)).padBottom(12).row();

        // --- Abilities & Features Section ---
        Image abilitiesDivider = new Image(GameAssetManager.uiDivider);
        abilitiesDivider.setScaling(Scaling.fit);
        contentTable.add(abilitiesDivider).colspan(2).fillX().padBottom(12).row();

        Label abilitiesTitle = new Label("Abilities & Features", skin, "subtitle");
        contentTable.add(abilitiesTitle).colspan(2).padBottom(8).row();

        for (String[] ability : abilities) {
            Label nameLabel = new Label(ability[0], skin);
            nameLabel.setWrap(false);
            Label descLabel = new Label(ability[1], skin);
            descLabel.setWrap(false);

            contentTable.add(nameLabel).colspan(2).padLeft(20).padTop(8).row();
            contentTable.add(descLabel).colspan(2).padLeft(20).padTop(2).width(600).row();
        }

        contentTable.add(new Label("", skin)).padBottom(12).row();

        // --- Cheat Codes Section ---
        Image cheatDivider = new Image(GameAssetManager.uiDivider);
        cheatDivider.setScaling(Scaling.fit);
        contentTable.add(cheatDivider).colspan(2).fillX().padBottom(12).row();

        contentTable.add(cheatCodesTitle).colspan(2).padBottom(8).row();

        contentTable.add(new Label("Boss Arena Teleport", skin)).padLeft(20);
        contentTable.add(bossArenaTeleportLabel).padLeft(40).row();

        contentTable.add(new Label("Spectator Mode", skin)).padLeft(20);
        contentTable.add(spectatorModeLabel).padLeft(40).row();

        contentTable.add(new Label("Emergency Heal", skin)).padLeft(20);
        contentTable.add(emergencyHealLabel).padLeft(40).row();

        contentTable.add(new Label("Refill Soul Vessel", skin)).padLeft(20);
        contentTable.add(refillSoulVesselLabel).padLeft(40).row();

        contentTable.add(new Label("God Mode", skin)).padLeft(20);
        contentTable.add(godModeLabel).padLeft(40).row();

        contentTable.add(new Label("", skin)).padBottom(16).row();

        // Back button
        contentTable.add(backBtn).colspan(2).center().row();

        scrollPane = new ScrollPane(contentTable, skin);
        rootTable.add(scrollPane).expand().fill();
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }
}
