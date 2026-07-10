package com.HallowKnight.Model.Knight.State;

import com.HallowKnight.Controller.Managers.GameAssetManager;
import com.HallowKnight.Model.Knight.Knight;
import com.HallowKnight.Model.Knight.Nail.Nail;
import com.HallowKnight.Model.Knight.Nail.State.SlashStates;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;

import java.util.Comparator;

public class UpSlashState extends State {
    private Nail nail;
    private boolean attacking;

    public UpSlashState(Knight knight) {
        super(knight);
        frames = GameAssetManager.knightAtlas.findRegions("UpSlash");
        frames.sort(Comparator.comparingInt(a -> a.index));
        stateAnimation = new Animation<>(Knight.ATTACK_DURATION / frames.size, frames);
        attacking = true;
        nail = new Nail(knight.world, knight, SlashStates.UP);
    }

    @Override
    public void enter() {
        super.enter();
        knight.setSlashCooldownRemaining(knight.getSlashCooldown());
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        if (stateTime >= Knight.ATTACK_DURATION) {
            attacking = false;
        }
        handleInputs();
        nail.update(dt);
    }

    @Override
    protected void handleInputs() {
        super.handleInputs();
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && knight.b2Body.getLinearVelocity().x <= Knight.MAX_MOVEMENT_SPEED) {
            knight.setFacingRight(true);
            knight.b2Body.applyLinearImpulse(new Vector2(0.3f, 0f), knight.b2Body.getWorldCenter(), true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && knight.b2Body.getLinearVelocity().x >= -Knight.MAX_MOVEMENT_SPEED) {
            knight.setFacingRight(false);
            knight.b2Body.applyLinearImpulse(new Vector2(-0.3f, 0f), knight.b2Body.getWorldCenter(), true);
        }
        if (!attacking) {
            knight.setState(new IdleState(knight));
        }
    }

    @Override
    public void exit() {
        super.exit();
        nail.destroy();
    }
}
