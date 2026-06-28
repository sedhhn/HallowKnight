package com.HallowKnight.Model.Knight.State;

import com.HallowKnight.Controller.Managers.GameAssetManager;
import com.HallowKnight.Model.Knight.Knight;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;

import java.util.Comparator;

public class IdleState extends State{
    public IdleState(Knight knight) {
        super(knight);
        frames= GameAssetManager.knightIdleAtlas.findRegions("Idle");
        frames.sort(Comparator.comparingInt(a->a.index));
        stateAnimation=new Animation<>(1/10f,frames, Animation.PlayMode.LOOP);
    }

    @Override
    public void enter() {
        super.enter();
        knight.b2Body.setLinearVelocity(0,knight.b2Body.getLinearVelocity().y);
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        handleInputs();
    }

    @Override
    protected void handleInputs() {
        super.handleInputs();
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            knight.setState(new RunState(knight));
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)){
            knight.setState(new JumpState(knight));
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.X)){
            knight.setState(new AttackState(knight));
        }
        if (!knight.isTouchingGround() && knight.isTouchingWall()){
            knight.setState(new WallSlideState(knight));
        }
    }
}
