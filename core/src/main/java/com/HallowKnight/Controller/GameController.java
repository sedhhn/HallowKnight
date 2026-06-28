package com.HallowKnight.Controller;

import com.HallowKnight.HallowKnight;
import com.HallowKnight.Model.Enemies.GroundEnemy;
import com.HallowKnight.Model.Knight.Knight;
import com.HallowKnight.Model.Knight.KnightState;
import com.HallowKnight.View.Modals.HUD;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;
import java.util.List;

public class GameController {
    World world;
    Knight knight;
    KnightController knightController;
    HUD hud;
    List<Vector2> enemySpawnPositions;
    List<Vector2> enemyStartAndEndPositions;
    List<GroundEnemy> enemies;

    public GameController(World world, Knight knight,HUD hud){
        this.world=world;
        this.knight=knight;
        enemySpawnPositions=new ArrayList<>();
        enemyStartAndEndPositions=new ArrayList<>();
        knightController=knight.getController();
        this.hud=hud;

        enemies=new ArrayList<>();
    }



    public void update(float dt){
        handleInput();
        hud.update(dt);
        hud.updateHealth(knight.getHp(), Knight.MAX_HP);

        for (GroundEnemy e: enemies){
            e.update(dt);
        }
    }

    private void handleInput(){
    }

    public void defineEnemies(){
        GroundEnemy enemy=new GroundEnemy(world,enemySpawnPositions.get(0),enemyStartAndEndPositions.get(0).x,enemyStartAndEndPositions.get(1).x);
        enemies.add(enemy);
    }

    public void renderEnemies(){
        HallowKnight.hallowKnight.getBatch().begin();
        for (GroundEnemy e: enemies) {
            e.draw(HallowKnight.hallowKnight.getBatch());
        }
        HallowKnight.hallowKnight.getBatch().end();
    }

    public void processPendingActions() {
        List<GroundEnemy> toRemove = new ArrayList<>();
        for (GroundEnemy e : enemies) {
            if (e.isDead()) {
                world.destroyBody(e.getBody());
                toRemove.add(e);
            }
        }
        enemies.removeAll(toRemove);
    }

    public List<Vector2> getEnemySpawnPositions(){
        return enemySpawnPositions;
    }

    public List<Vector2> getEnemyStartAndEndPositions(){
        return enemyStartAndEndPositions;
    }
}
