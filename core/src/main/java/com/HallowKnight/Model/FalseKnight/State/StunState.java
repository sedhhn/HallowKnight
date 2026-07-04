package com.HallowKnight.Model.FalseKnight.State;

import com.HallowKnight.Controller.Managers.GameAssetManager;
import com.HallowKnight.Model.FalseKnight.FalseKnight;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.Comparator;

public class StunState extends State{
    Animation<TextureRegion> stateAnimation0;
    Animation<TextureRegion> stateAnimation1;
    Animation<TextureRegion> stateAnimation2;
    Animation<TextureRegion> stateAnimation3;
    public StunState(FalseKnight falseKnight) {
        super(falseKnight);
        frames= GameAssetManager.falseKnight.findRegions("DeathFall");
        frames.sort(Comparator.comparingInt(a->a.index));
        stateAnimation0=new Animation<>(1/9f,frames);

        frames=GameAssetManager.falseKnight.findRegions("DeathLand");
        frames.sort(Comparator.comparingInt(a->a.index));
        stateAnimation1=new Animation<>(1/9f,frames);

        frames=GameAssetManager.falseKnight.findRegions("Body");
        frames.sort(Comparator.comparingInt(a->a.index));
        stateAnimation2=new Animation<>(1/9f,frames, Animation.PlayMode.LOOP);

        frames=GameAssetManager.falseKnight.findRegions("Stun Recover");
        frames.sort(Comparator.comparingInt(a->a.index));
        stateAnimation3=new Animation<>(1/9f,frames);
    }

    @Override
    public void enter() {
        super.enter();
        stateAnimation=stateAnimation0;
        if (falseKnight.getKnight().b2Body.getPosition().x>falseKnight.b2Body.getPosition().x){
            falseKnight.setFacingRight(true);
        } else {
            falseKnight.setFacingRight(false);
        }
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        if (stateTime>stateAnimation.getAnimationDuration() && stateAnimation==stateAnimation0){
            stateAnimation=stateAnimation1;
            stateTime=0;
        }
        if (stateTime>stateAnimation.getAnimationDuration() && stateAnimation==stateAnimation1){
            stateAnimation=stateAnimation2;
            stateTime=0;
        }
        if (stateTime>FalseKnight.STUN_DURATION && stateAnimation==stateAnimation2){
            stateAnimation=stateAnimation3;
            stateTime=0;
        }
        if (stateTime>stateAnimation.getAnimationDuration() && stateAnimation==stateAnimation3){
            falseKnight.setState(new Idle(falseKnight));
        }
    }
}
