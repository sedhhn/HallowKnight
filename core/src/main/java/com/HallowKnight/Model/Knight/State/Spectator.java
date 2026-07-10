package com.HallowKnight.Model.Knight.State;

import com.HallowKnight.Controller.ContactController;
import com.HallowKnight.Controller.Managers.GameAssetManager;
import com.HallowKnight.Model.GameState;
import com.HallowKnight.Model.Knight.ContactManager;
import com.HallowKnight.Model.Knight.Knight;
import com.HallowKnight.Model.Knight.SpectatorContactManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;

import java.util.Comparator;

public class Spectator extends State{
    SpectatorContactManager spectatorContactManager;
    public Spectator(Knight knight) {
        super(knight);
        frames= GameAssetManager.knightAtlas.findRegions("Idle");
        frames.sort(Comparator.comparingInt(a->a.index));
        stateAnimation=new Animation<>(1/10f,frames, Animation.PlayMode.LOOP);
        knight.b2Body.setGravityScale(0);
    }

    @Override
    public void exit() {
        super.exit();
        knight.b2Body.setGravityScale(1);
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        handleInputs();
    }

    @Override
    protected void handleInputs() {
        super.handleInputs();
        boolean moving=false;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            if (knight.b2Body.getLinearVelocity().x<Knight.MAX_MOVEMENT_SPEED) {
                knight.b2Body.applyLinearImpulse(new Vector2(0.5f, 0f), knight.b2Body.getWorldCenter(), true);
            }
            moving=true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            if (knight.b2Body.getLinearVelocity().x>-Knight.MAX_MOVEMENT_SPEED) {
                knight.b2Body.applyLinearImpulse(new Vector2(-0.5f, 0f), knight.b2Body.getWorldCenter(), true);
            }
            moving=true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)){
            if (knight.b2Body.getLinearVelocity().y<Knight.MAX_MOVEMENT_SPEED) {
                knight.b2Body.applyLinearImpulse(new Vector2(0f, 0.5f), knight.b2Body.getWorldCenter(), true);
            }
            moving=true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            if ( knight.b2Body.getLinearVelocity().y>-Knight.MAX_MOVEMENT_SPEED) {
                knight.b2Body.applyLinearImpulse(new Vector2(0f, -0.5f), knight.b2Body.getWorldCenter(), true);
            }
            moving=true;
        }
        if (!moving){
            knight.b2Body.setLinearVelocity(0,0);
        }
    }
}
