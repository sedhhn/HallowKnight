package com.HallowKnight.Model.Enemies.Mosquito.State;

import com.HallowKnight.Controller.Managers.GameAssetManager;
import com.HallowKnight.Model.Enemies.Enemy;
import com.HallowKnight.Model.Enemies.Mosquito.Mosquito;
import com.HallowKnight.Model.Enemies.State;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;

import java.util.Comparator;

public class Attack extends State {
    Mosquito mosquito;
    Vector2 velocity;
    public Attack(Mosquito mosquito) {
        super(mosquito);
        this.mosquito=mosquito;
        frames= GameAssetManager.mosquitoAtlas.findRegions("Attack");
        frames.sort(Comparator.comparingInt(a->a.index));
        stateAnimation=new Animation<>(1/10f,frames);
        velocity=new Vector2(0,0);
    }

    @Override
    public void enter() {
        super.enter();
        mosquito.setMovementSpeed(Mosquito.ATTACK_MOVEMENT_SPEED);
        float velocityX=mosquito.getTargetPos().x-mosquito.getB2Body().getPosition().x;
        float velocityY=mosquito.getTargetPos().y-mosquito.getB2Body().getPosition().y;
        float velocityMagnitude=(float) Math.sqrt(Math.pow(velocityX,2)+Math.pow(velocityY,2));
        velocityX=velocityX/velocityMagnitude*Mosquito.ATTACK_MOVEMENT_SPEED;
        velocityY=velocityY/velocityMagnitude*Mosquito.ATTACK_MOVEMENT_SPEED;
        velocity.set(velocityX,velocityY);
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        if (stateTime>Mosquito.MAX_ATTACK_DURATION){
            mosquito.setState(new Idle(mosquito));
        }
        if (stateTime>0.1f) {
            if (mosquito.getSurroundSensors().leftSensor > 0 || mosquito.getSurroundSensors().rightSensor > 0
                || mosquito.getSurroundSensors().topSensor > 0 || mosquito.getSurroundSensors().bottomSensor > 0) {
                mosquito.setState(new Idle(mosquito));
            }
        }
        mosquito.getB2Body().setLinearVelocity(velocity);
    }
}
