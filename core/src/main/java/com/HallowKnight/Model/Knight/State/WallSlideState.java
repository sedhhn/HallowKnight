package com.HallowKnight.Model.Knight.State;

import com.HallowKnight.Controller.Managers.GameAssetManager;
import com.HallowKnight.Model.Knight.Knight;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;

import java.util.Comparator;

public class WallSlideState extends State {
    public WallSlideState(Knight knight) {
        super(knight);
        frames= GameAssetManager.knightAtlas.findRegions("Wall Slide");
        frames.sort(Comparator.comparingInt(a->a.index));
        stateAnimation=new Animation<>(1/8f,frames, Animation.PlayMode.LOOP);
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        knight.b2Body.applyForce(new Vector2(0,2),knight.b2Body.getWorldCenter(),true);
        handleInputs();
    }

    @Override
    protected void handleInputs() {
        super.handleInputs();
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)){
            knight.setState(new JumpState(knight));
        }
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
        if (!knight.isTouchingWall()){
            knight.setState(new IdleState(knight));
        }
    }
}
