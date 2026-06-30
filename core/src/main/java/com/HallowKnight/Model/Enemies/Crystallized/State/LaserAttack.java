package com.HallowKnight.Model.Enemies.Crystallized.State;

import com.HallowKnight.Controller.Managers.GameAssetManager;
import com.HallowKnight.Model.Enemies.Crystallized.Crystallized;
import com.HallowKnight.Model.Enemies.Crystallized.Laser;
import com.HallowKnight.Model.Enemies.Enemy;
import com.HallowKnight.Model.Enemies.State;
import com.badlogic.gdx.graphics.g2d.Animation;

import java.util.Comparator;

public class LaserAttack extends State {
    Crystallized crystallized;
    Laser laser;
    public LaserAttack(Crystallized crystallized) {
        super(crystallized);
        this.crystallized=crystallized;
        frames= GameAssetManager.crystallizedAtlas.findRegions("Shoot");
        frames.sort(Comparator.comparingInt(a->a.index));
        stateAnimation=new Animation<>(Crystallized.laserAttackDuration/frames.size,frames);
        laser=new Laser(crystallized);
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        laser.update(dt);
        if (stateTime>Crystallized.laserAttackDuration){
            crystallized.setState(new Angry(crystallized));
            laser.destroy();
        }
    }
}
