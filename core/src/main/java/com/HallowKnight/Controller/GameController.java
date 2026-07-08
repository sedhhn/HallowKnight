package com.HallowKnight.Controller;

import com.HallowKnight.HallowKnight;
import com.HallowKnight.Model.Effects.Effect;
import com.HallowKnight.Model.Enemies.Enemy;
import com.HallowKnight.Model.FalseKnight.Barrier;
import com.HallowKnight.Model.FalseKnight.FalseKnight;
import com.HallowKnight.Model.Knight.Knight;
import com.HallowKnight.Model.NPCs.NPC;
import com.HallowKnight.View.GameScreen;
import com.HallowKnight.View.Modals.HUD;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;
import java.util.List;

public class GameController {
    World world;
    Knight knight;
    FalseKnight falseKnight;
    KnightController knightController;
    HUD hud;
    List<Enemy> enemies;
    List<NPC> NPCs;
    List<Effect> effects;
    GameScreen gameScreen;
    Barrier leftBarrier;
    Barrier rightBarrier;

    public GameController(World world, Knight knight,HUD hud, GameScreen gameScreen){
        this.world=world;
        this.knight=knight;
        knightController=knight.getController();
        this.hud=hud;
        this.gameScreen=gameScreen;

        enemies=new ArrayList<>();
        NPCs=new ArrayList<>();
        effects=new ArrayList<>();
    }



    public void update(float dt){
        hud.update(dt);
        hud.updateHealth(knight.getHp(), Knight.MAX_HP);
        hud.updateSoul(knight.getSoul(),Knight.MAX_SOUL);

        for (Enemy e: enemies){
            e.update(dt);
        }
        for (NPC n: NPCs){
            n.update(dt);
        }
        for (Effect e: effects){
            e.update(dt);
        }
        falseKnight.update(dt);
    }

    public void handleInput(){
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            togglePauseMenu();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.I)){
            toggleInventory();
        }
    }

    public void renderEnemies(){
        HallowKnight.hallowKnight.getBatch().begin();
        for (Enemy e: enemies) {
            e.draw(HallowKnight.hallowKnight.getBatch());
        }
        for (NPC n: NPCs){
            n.draw(HallowKnight.hallowKnight.getBatch());
        }
        HallowKnight.hallowKnight.getBatch().end();
    }

    public void renderEffects(){
        HallowKnight.hallowKnight.getBatch().begin();
        for (Effect e:effects){
            e.draw(HallowKnight.hallowKnight.getBatch());
        }
        HallowKnight.hallowKnight.getBatch().end();
    }

    public void processPendingActions() {
        List<Enemy> toRemove = new ArrayList<>();
        for (Enemy e : enemies) {
            if (e.isDead()) {
                world.destroyBody(e.getB2Body());
                toRemove.add(e);
            }
        }
        enemies.removeAll(toRemove);

        List<Effect> toRemoveEffects=new ArrayList<>();
        for (Effect e: effects){
            if (e.isOver()){
                world.destroyBody(e.getB2Body());
                toRemoveEffects.add(e);
            }
        }
        effects.removeAll(toRemoveEffects);
        if (knight.shouldTeleport()){
            knight.b2Body.setTransform(knight.getLastSafePos(),0);
            knight.setShouldTeleport(false);
        }
        if (falseKnight.isCreateBarriers()){
            leftBarrier=new Barrier(falseKnight.getLeftBarrierPos(),world);
            rightBarrier=new Barrier(falseKnight.getRightBarrierPos(),world);
            falseKnight.setCreateBarriers(false);
        }
    }

    public List<Enemy> getEnemies(){
        return enemies;
    }

    public List<NPC> getNPCs(){
        return NPCs;
    }

    public Knight getKnight(){
        return knight;
    }

    public void setFalseKnight(FalseKnight falseKnight){
        this.falseKnight=falseKnight;
    }

    public void renderFalseKnight(){
        HallowKnight.hallowKnight.getBatch().begin();
        falseKnight.draw(HallowKnight.hallowKnight.getBatch());
        HallowKnight.hallowKnight.getBatch().end();
    }

    public void renderBarriers(){
        if (leftBarrier ==null || rightBarrier == null){
            return;
        }
        HallowKnight.hallowKnight.getBatch().begin();
        leftBarrier.draw(HallowKnight.hallowKnight.getBatch());
        rightBarrier.draw(HallowKnight.hallowKnight.getBatch());
        HallowKnight.hallowKnight.getBatch().end();
    }

    public List<Effect> getEffects(){
        return effects;
    }

    public void togglePauseMenu(){
        if (gameScreen.isPaused()) {
            gameScreen.getPauseMenu().setVisible(false);
            gameScreen.setPaused(false);
        } else {
            gameScreen.getPauseMenu().setVisible(true);
            gameScreen.setPaused(true);
        }
    }

    public void toggleInventory(){
        if (gameScreen.getInventory().isVisible()){
            gameScreen.getInventory().setVisible(false);
        } else{
            gameScreen.getInventory().setVisible(true);
        }
    }

    public GameScreen getGameScreen(){
        return gameScreen;
    }
}
