package com.HallowKnight.Model.Knight.State;

import com.HallowKnight.Controller.Managers.GameAssetManager;
import com.HallowKnight.Model.Knight.Knight;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;

import java.util.Comparator;

public class JumpState extends State{
    public JumpState(Knight knight) {
        super(knight);
        frames= GameAssetManager.knightJumpAtlas.findRegions("Airborne");
        frames.sort(Comparator.comparingInt(a->a.index));
        stateAnimation=new Animation<>(1/10f,frames);
    }

    @Override
    public void enter() {
        super.enter();
        knight.b2Body.applyLinearImpulse(new Vector2(0,6f),knight.b2Body.getWorldCenter()
            ,true);
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        handleInputs();
    }

    @Override
    protected void handleInputs() {
        super.handleInputs();
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && knight.b2Body.getLinearVelocity().x<=Knight.MAX_MOVEMENT_SPEED){
            knight.setFacingRight(true);
            knight.b2Body.applyLinearImpulse(new Vector2(0.3f,0f),knight.b2Body.getWorldCenter()
                ,true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)&& knight.b2Body.getLinearVelocity().x>=-Knight.MAX_MOVEMENT_SPEED){
            knight.setFacingRight(false);
            knight.b2Body.applyLinearImpulse(new Vector2(-0.3f,0f),knight.b2Body.getWorldCenter()
                ,true);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.X)){
            knight.setState(new AttackState(knight));
        }
        if(stateTime>=0.1f) {
            if (knight.isTouchingGround()) {
                knight.setState(new IdleState(knight));
            }
        }
    }
}
