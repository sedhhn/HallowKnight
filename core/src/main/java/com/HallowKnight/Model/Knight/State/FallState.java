package com.HallowKnight.Model.Knight.State;

import com.HallowKnight.Controller.Managers.GameAssetManager;
import com.HallowKnight.Model.Knight.Knight;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;

import java.util.Comparator;

public class FallState extends State {
    public FallState(Knight knight) {
        super(knight);
        frames= GameAssetManager.knightAtlas.findRegions("Fall");
        frames.sort(Comparator.comparingInt(a->a.index));
        stateAnimation=new Animation<>(1/10f,frames, Animation.PlayMode.LOOP);
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        handleInputs();
    }

    @Override
    protected void handleInputs() {
        super.handleInputs();
        boolean movingRightOrLeft=false;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            if (knight.b2Body.getLinearVelocity().x<=Knight.MAX_MOVEMENT_SPEED){
                knight.b2Body.applyLinearImpulse(new Vector2(0.3f,0f),knight.b2Body.getWorldCenter()
                    ,true);
            }
            knight.setFacingRight(true);
            if (knight.isTouchingWall()){
                knight.setState(new WallSlideState(knight));
            }
            movingRightOrLeft=true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            if (knight.b2Body.getLinearVelocity().x>=-Knight.MAX_MOVEMENT_SPEED){
                knight.b2Body.applyLinearImpulse(new Vector2(-0.3f,0f),knight.b2Body.getWorldCenter()
                    ,true);
            }
            knight.setFacingRight(false);
            if (knight.isTouchingWall()){
                knight.setState(new WallSlideState(knight));
            }
            movingRightOrLeft=true;
        }
        if (!movingRightOrLeft){
            knight.b2Body.setLinearVelocity(0,knight.b2Body.getLinearVelocity().y);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.X)){
            if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
                knight.setState(new DownSlashState(knight));
            } else {
                knight.setState(new AttackState(knight));
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)){
            knight.setState(new DoubleJumpState(knight));
        }
            if (knight.isTouchingGround()) {
                knight.setState(new IdleState(knight));
            }
    }
}
