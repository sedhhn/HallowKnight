package com.HallowKnight.Model.Enemies.Mosquito.State;

import com.HallowKnight.Controller.Managers.GameAssetManager;
import com.HallowKnight.Model.Enemies.Enemy;
import com.HallowKnight.Model.Enemies.Mosquito.Mosquito;
import com.HallowKnight.Model.Enemies.State;
import com.badlogic.gdx.graphics.g2d.Animation;

import java.util.Comparator;

public class Idle extends State {
    Mosquito mosquito;
    public Idle(Mosquito mosquito) {
        super(mosquito);
        this.mosquito=mosquito;
        frames= GameAssetManager.mosquitoAtlas.findRegions("Idle");
        frames.sort(Comparator.comparingInt(a->a.index));
        stateAnimation=new Animation<>(1/10f,frames, Animation.PlayMode.LOOP);
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        mosquito.getB2Body().setLinearVelocity(0,0);
        if (mosquito.getSurroundSensors().radarSensor>0){
            if (mosquito.getTargetPos().x>mosquito.getB2Body().getPosition().x){
                mosquito.setFacingRight(true);
            } else{
                mosquito.setFacingRight(false);
            }
            mosquito.setState(new AttackAnticipate(mosquito));
        }
    }
}
