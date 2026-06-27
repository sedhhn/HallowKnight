package com.HallowKnight.Controller;

import com.HallowKnight.HallowKnight;
import com.HallowKnight.Model.Enemies.GroundEnemy;
import com.HallowKnight.Model.Knight;
import com.HallowKnight.Model.KnightState;
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
        boolean isIdle=true;
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)){
            knight.b2Body.applyLinearImpulse(new Vector2(0,6f),knight.b2Body.getWorldCenter()
                ,true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            if (knight.b2Body.getLinearVelocity().x<= Knight.MAX_MOVEMENT_SPEED){
                knight.b2Body.applyLinearImpulse(new Vector2(0.3f,0),knight.b2Body.getWorldCenter()
                    ,true);
            }
            knightController.setFacingRight(true);
            knightController.setState(KnightState.RUNNING);
            isIdle=false;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            if (knight.b2Body.getLinearVelocity().x >= -Knight.MAX_MOVEMENT_SPEED){
                knight.b2Body.applyLinearImpulse(new Vector2(-0.3f,0),knight.b2Body.getWorldCenter()
                    ,true);
            }
            knightController.setFacingRight(false);
            knightController.setState(KnightState.RUNNING);
            isIdle=false;
        }
        if (isIdle){
            knightController.setState(KnightState.IDLE);
        }
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

    public List<Vector2> getEnemySpawnPositions(){
        return enemySpawnPositions;
    }

    public List<Vector2> getEnemyStartAndEndPositions(){
        return enemyStartAndEndPositions;
    }
}
