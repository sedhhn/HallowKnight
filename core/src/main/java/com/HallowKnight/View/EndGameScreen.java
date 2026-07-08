package com.HallowKnight.View;

import com.HallowKnight.Controller.Managers.GameAssetManager;
import com.HallowKnight.Controller.Managers.ScreenManager;
import com.HallowKnight.HallowKnight;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class EndGameScreen extends MenuScreen{
    Label title;
    TextButton backBtn;
    public EndGameScreen(HallowKnight game) {
        super(game);
        rootTable.clear();
        rootTable.setFillParent(true);
        rootTable.setBackground(new TextureRegionDrawable(GameAssetManager.backgroundTexture2));
        rootTable.defaults().space(10);

        title=new Label("Congratulation",GameAssetManager.skin);
        rootTable.add(title).row();

        backBtn=new TextButton("Back to Main Menu",GameAssetManager.skin);
        backBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                ScreenManager.getInstance().setScreen(new MainMenuScreen(game));
            }
        });
        rootTable.add(backBtn);
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
