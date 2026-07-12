package com.HallowKnight.Model.Knight.State;

import com.HallowKnight.Controller.Managers.GameAssetManager;
import com.HallowKnight.Model.Effects.SoulBall;
import com.HallowKnight.Model.GameCamera;
import com.HallowKnight.Model.Knight.Knight;
import com.badlogic.gdx.graphics.g2d.Animation;

import java.util.Comparator;

public class FireBallCast extends State{
    SoulBall soulBall;

    public FireBallCast(Knight knight) {
        super(knight);
        frames= GameAssetManager.knightAtlas.findRegions("Fireball Cast");
        frames.sort(Comparator.comparingInt(a->a.index));
        stateAnimation=new Animation<>(1/9f,frames, Animation.PlayMode.LOOP);
    }

    @Override
    public void enter() {
        super.enter();
        knight.decreaseSoul(SoulBall.SOUL_COST);
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        knight.b2Body.setLinearVelocity(0,0);
        if (stateTime>Knight.FIREBALL_CAST_DURATION/2f && soulBall==null){
            soulBall=new SoulBall(GameAssetManager.soulBall.findRegion("SoulBall"),knight);
            knight.getGameScreen().getController().getEffects().add(soulBall);
            knight.getGameScreen().getCamera().startShake(
                GameCamera.DEFAULT_LIGHT_SHAKE_MAX_INTENSITY,
                GameCamera.DEFAULT_LIGHT_SHAKE_DURATION
            );
        }
        if (stateTime>Knight.FIREBALL_CAST_DURATION){
            knight.setState(new IdleState(knight));
        }
    }
}
