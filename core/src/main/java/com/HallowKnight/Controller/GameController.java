package com.HallowKnight.Controller;

import com.HallowKnight.Model.Knight;
import com.HallowKnight.Model.KnightState;
import com.HallowKnight.View.Modals.HUD;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

public class GameController {
    Knight knight;
    KnightController knightController;
    HUD hud;

    public GameController(Knight knight,HUD hud){
        this.knight=knight;
        knightController=knight.getController();
        this.hud=hud;
    }



    public void update(float dt){
        handleInput();
        hud.update(dt);
        hud.updateHealth(knight.getHp(), Knight.MAX_HP);
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
}
