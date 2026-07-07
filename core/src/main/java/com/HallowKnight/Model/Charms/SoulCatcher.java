package com.HallowKnight.Model.Charms;

import com.HallowKnight.Model.Knight.Knight;

public class SoulCatcher extends Charm{
    public SoulCatcher(Knight knight) {
        super(knight,CharmType.SOUL_CATCHER);
    }

    @Override
    public void onEquip() {
        super.onEquip();
        knight.setSoulIncrease(Knight.BASE_SOUL_INCREASE*2);
    }

    @Override
    public void onUnEquip() {
        super.onUnEquip();
        knight.setSoulIncrease(Knight.BASE_SOUL_INCREASE);
    }
}
