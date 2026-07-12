package com.HallowKnight.Model.NPCs.Zote.State;

import com.HallowKnight.Controller.Managers.AudioManager;
import com.HallowKnight.Controller.Managers.GameAssetManager;
import com.HallowKnight.Model.Knight.Knight;
import com.HallowKnight.Model.Knight.State.IdleState;
import com.HallowKnight.Model.NPCs.NPC;
import com.HallowKnight.Model.NPCs.Zote.Zote;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Talk extends State{
    List<String> dialogs=Arrays.asList(
        """
            Ah, another clueless warrior seeking glory...
            Let me warn you: beyond that door lies a brutish, mindless beast known as the 'False Knight.'
            But fear not! For I, the great Zote, know all the secrets to his defeat."""
        , """
            That dull-witted monster only knows how to swing a club and stomp around.
            I could easily crush him in the blink of an eye, of course...
            But alas, I am rather tired today. Last night, I single-handedly saved an entire village from fiery giants.
            So, I shall graciously bestow upon you the honor of fighting in my place."""
        , """
            The key is... when he raises his club, you must do something...
            Hmm, I can't quite remember what exactly. But it was definitely something clever.
            And if he roars, that means he's weakened. Or maybe enraged. One of the two.
            So go forth and claim glory—for me! I mean, for yourself."""
        , """
            Remember, if you fail, tell everyone that the great Zote trained you!
            That way, at least you'll die with honor and dignity.
            But seriously, if things get tough, just shout my name. I might send a cheer your way from afar."""
    );
    Zote zote;
    Table table;
    int nextDialogIdx=0;
    Label dialog;
    private static final String[] zoteSounds = {
        GameAssetManager.zote0, GameAssetManager.zote1, GameAssetManager.zote2,
        GameAssetManager.zote3, GameAssetManager.zote4
    };
    private final Random random = new Random();
    public Talk(Zote zote) {
        super(zote);
        this.zote=zote;
        frames= GameAssetManager.zoteAtlas.findRegions("Talk");
        frames.sort(Comparator.comparingInt(a->a.index));
        stateAnimation=new Animation<>(1/10f,frames, Animation.PlayMode.LOOP);
    }

    @Override
    public void enter() {
        super.enter();
        Pixmap bgPixmap=new Pixmap(1,1,Pixmap.Format.RGB565);
        bgPixmap.setColor(Color.BLACK);
        bgPixmap.fill();

        TextureRegionDrawable textureRegionDrawableBg
            =new TextureRegionDrawable(new TextureRegion(new Texture(bgPixmap)));
        table=new Table();
        table.setFillParent(true);
        table.top().padTop(60);
        Table table1=new Table();
        table1.setBackground(textureRegionDrawableBg);
        table1.pad(50);
        table.add(table1).top();
        dialog=new Label(dialogs.getFirst(),GameAssetManager.skin);
        nextDialogIdx++;
        table1.add(dialog);
        zote.getGameScreen().getMainStack().add(table);
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        handleInputs();
    }

    @Override
    protected void handleInputs() {
        super.handleInputs();
        if (Gdx.input.isKeyJustPressed(Input.Keys.Z)){
            AudioManager.getInstance().playSFX(zoteSounds[random.nextInt(zoteSounds.length)]);
            if (nextDialogIdx>=dialogs.size()){
                zote.getKnight().setState(new IdleState(zote.getKnight()));
                zote.setState(new Idle(zote));
            } else {
                dialog.setText(dialogs.get(nextDialogIdx));
                nextDialogIdx++;
            }
        }
    }

    @Override
    public void exit() {
        super.exit();
        zote.getGameScreen().getMainStack().removeActor(table);
    }
}
