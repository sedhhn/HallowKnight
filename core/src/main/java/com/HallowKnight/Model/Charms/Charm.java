package com.HallowKnight.Model.Charms;

import com.HallowKnight.Model.Knight.Knight;

public abstract class Charm {
    Knight knight;
    CharmType type;
    public Charm(Knight knight, CharmType type){
        this.knight=knight;
        this.type=type;
    }

    public void onEquip(){

    }

    public void onUnEquip(){

    }

    public CharmType getType(){
        return type;
    }
}
