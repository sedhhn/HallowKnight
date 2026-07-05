package com.HallowKnight.Model.Knight.State;

import com.HallowKnight.Controller.Managers.GameAssetManager;
import com.HallowKnight.HallowKnight;
import com.HallowKnight.Model.Effects.SoulScream;
import com.HallowKnight.Model.Knight.Knight;
import com.badlogic.gdx.graphics.g2d.Animation;

import java.util.Comparator;

public class Scream extends State{
    SoulScream soulScream0;
    boolean scream1Casted;
    SoulScream soulScream1;
    boolean scream2Casted;
    SoulScream soulScream2;
    public Scream(Knight knight) {
        super(knight);
        frames= GameAssetManager.knightAtlas.findRegions("Scream");
        frames.sort(Comparator.comparingInt(a->a.index));
        stateAnimation=new Animation<>(1/9f,frames);

        scream1Casted=false;
        scream2Casted=false;
        soulScream0=new SoulScream(GameAssetManager.soulScream.findRegion("SoulScream"),knight);
        knight.getGameScreen().getController().getEffects().add(soulScream0);
    }

    @Override
    public void enter() {
        super.enter();
        knight.decreaseSoul(SoulScream.SOUL_COST);
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        if (stateTime>Knight.SCREAM_DURATION/3 && !scream1Casted){
            soulScream1=new SoulScream(GameAssetManager.soulScream.findRegion("SoulScream"),knight);
            knight.getGameScreen().getController().getEffects().add(soulScream1);
            scream1Casted=true;
        }
        if (stateTime>2*Knight.SCREAM_DURATION/3 && !scream2Casted){
            soulScream2=new SoulScream(GameAssetManager.soulScream.findRegion("SoulScream"),knight);
            knight.getGameScreen().getController().getEffects().add(soulScream2);
            scream2Casted=true;
        }
        knight.b2Body.setLinearVelocity(0,0);
        if (stateTime> Knight.SCREAM_DURATION){
            knight.setState(new IdleState(knight));
        }
    }
}
