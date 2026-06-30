package com.HallowKnight.Model.Knight.State;

import com.HallowKnight.Controller.Managers.GameAssetManager;
import com.HallowKnight.Model.Knight.Knight;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.Comparator;

public class RunState extends State {

    public RunState(Knight knight){
        super(knight);
        frames= GameAssetManager.knightRunningAtlas.findRegions("Run");
        frames.sort(Comparator.comparing(a->a.index));
        stateAnimation=new Animation<>(1/10f,frames, Animation.PlayMode.LOOP);
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        handleInputs();
    }

    @Override
    protected void handleInputs(){
        boolean isIdle=true;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            if (knight.b2Body.getLinearVelocity().x<=Knight.MAX_MOVEMENT_SPEED){
                knight.b2Body.applyLinearImpulse(new Vector2(0.3f,0f),knight.b2Body.getWorldCenter()
                    ,true);
            }
            knight.setFacingRight(true);
            isIdle=false;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            if (knight.b2Body.getLinearVelocity().x>=-Knight.MAX_MOVEMENT_SPEED) {
                knight.b2Body.applyLinearImpulse(new Vector2(-0.3f, 0f), knight.b2Body.getWorldCenter()
                    , true);
            }
            knight.setFacingRight(false);
            isIdle=false;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)){
            knight.setState(new JumpState(knight));
            isIdle=false;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.X)){
            knight.setState(new AttackState(knight));
            isIdle=false;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.C) && knight.getDashCooldown()<=0){
            knight.setState(new DashState(knight));
        }
        if (!knight.isTouchingGround()){
            knight.setState(new FallState(knight));
        }
        if (isIdle){
            knight.setState(new IdleState(knight));
        }
    }
}
