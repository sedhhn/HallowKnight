package com.HallowKnight.Model.Charms;

import com.HallowKnight.Model.Knight.Knight;

public class QuickSlash extends Charm {
    public QuickSlash(Knight knight) {
        super(knight,CharmType.QUICK_SLASH);
    }

    @Override
    public void onEquip() {
        super.onEquip();
        knight.setSlashCooldown(Knight.SLASH_COOLDOWN/4f);
    }

    @Override
    public void onUnEquip() {
        super.onUnEquip();
        knight.setSlashCooldown(Knight.SLASH_COOLDOWN);
    }
}
