package com.HallowKnight.Model.NPCs.Zote.State;

import com.HallowKnight.Model.NPCs.NPC;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public abstract class State {
    NPC npc;
    Animation<TextureRegion> stateAnimation;
    Array<TextureAtlas.AtlasRegion> frames;
    float stateTime=0;

    public State(NPC npc){
        this.npc=npc;
    }

    public void enter(){

    }

    public void update(float dt){
        if (npc.isFacingRight() && !stateAnimation.getKeyFrame(stateTime).isFlipX()){
            stateAnimation.getKeyFrame(stateTime).flip(true,false);
        } else if(!npc.isFacingRight() && stateAnimation.getKeyFrame(stateTime).isFlipX()){
            stateAnimation.getKeyFrame(stateTime).flip(true,false);
        }
        npc.setRegion(stateAnimation.getKeyFrame(stateTime));
        stateTime+=dt;
    }

    public void exit(){

    }

    protected void handleInputs(){

    }
}
