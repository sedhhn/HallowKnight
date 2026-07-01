package com.HallowKnight.Model.Enemies.GroundEnemy.State;

import com.HallowKnight.Controller.Managers.GameAssetManager;
import com.HallowKnight.Model.Enemies.Enemy;
import com.HallowKnight.Model.Enemies.GroundEnemy.GroundEnemy;
import com.HallowKnight.Model.Enemies.State;
import com.badlogic.gdx.graphics.g2d.Animation;

import java.util.Comparator;

public class Walking extends State {
    GroundEnemy groundEnemy;
    public Walking(GroundEnemy groundEnemy) {
        super(groundEnemy);
        this.groundEnemy=groundEnemy;
        frames= GameAssetManager.crystalCrawlerAtlas.findRegions("Walk");
        frames.sort(Comparator.comparingInt(a->a.index));
        stateAnimation=new Animation<>(1/10f,frames, Animation.PlayMode.LOOP);
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        if (groundEnemy.getSurroundSensors().leftSensor>0 || groundEnemy.getSurroundSensors().bottomLeftSensor<1){
            if (groundEnemy.getMovementSpeed()<0){
                groundEnemy.setMovementSpeed(-groundEnemy.getMovementSpeed());
                groundEnemy.setFacingRight(true);
            }
        } else if(groundEnemy.getSurroundSensors().rightSensor>0 || groundEnemy.getSurroundSensors().bottomRightSensor<1){
            if (groundEnemy.getMovementSpeed()>0){
                groundEnemy.setMovementSpeed(-groundEnemy.getMovementSpeed());
                groundEnemy.setFacingRight(false);
            }
        }
        enemy.getB2Body().setLinearVelocity(enemy.getMovementSpeed()
            ,enemy.getB2Body().getLinearVelocity().y);
    }
}
