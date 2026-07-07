package com.HallowKnight.Model.Charms;

import com.HallowKnight.Model.Knight.Knight;

public class Dashmaster extends Charm {
    public Dashmaster(Knight knight) {
        super(knight,CharmType.DASHMASTER);
    }

    @Override
    public void onEquip() {
        super.onEquip();
        knight.setDashCooldown(Knight.DASH_COOLDOWN/3f);
    }

    @Override
    public void onUnEquip() {
        super.onUnEquip();
        knight.setDashCooldown(Knight.DASH_COOLDOWN);
    }
}
