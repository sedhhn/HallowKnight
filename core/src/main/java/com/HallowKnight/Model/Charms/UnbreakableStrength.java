package com.HallowKnight.Model.Charms;

import com.HallowKnight.Model.Knight.Knight;

public class UnbreakableStrength extends Charm{
    public UnbreakableStrength(Knight knight) {
        super(knight, CharmType.UNBREAKABLE_STRENGTH);
    }

    @Override
    public void onEquip() {
        super.onEquip();
        knight.setDamage(Knight.BASE_NAIL_DAMAGE*2);
    }

    @Override
    public void onUnEquip() {
        super.onUnEquip();
        knight.setDamage(Knight.BASE_NAIL_DAMAGE);
    }
}
