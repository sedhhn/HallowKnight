package com.HallowKnight.Model.Enemies.Mosquito.State;

import com.HallowKnight.Controller.Managers.GameAssetManager;
import com.HallowKnight.Model.Enemies.Enemy;
import com.HallowKnight.Model.Enemies.Mosquito.Mosquito;
import com.HallowKnight.Model.Enemies.State;
import com.badlogic.gdx.graphics.g2d.Animation;

import java.util.Comparator;

public class AttackAnticipate extends State {
    Mosquito mosquito;
    public AttackAnticipate(Mosquito mosquito) {
        super(mosquito);
        this.mosquito=mosquito;
        frames= GameAssetManager.mosquitoAtlas.findRegions("Attack Anticipate");
        frames.sort(Comparator.comparingInt(a->a.index));
        stateAnimation=new Animation<>(Mosquito.ATTACK_ANTICIPATE_DURATION/frames.size,frames);
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        if (stateTime>Mosquito.ATTACK_ANTICIPATE_DURATION){
            mosquito.setState(new Attack(mosquito));
        }
    }
}
