package com.HallowKnight.Model.Charms;

import com.HallowKnight.Model.Knight.Knight;

public class QuickFocus extends Charm{
    public QuickFocus(Knight knight) {
        super(knight, CharmType.QUICK_FOCUS);
    }

    @Override
    public void onEquip() {
        super.onEquip();
        knight.setFocusDuration(Knight.FOCUS_DURATION/2);
    }

    @Override
    public void onUnEquip() {
        super.onUnEquip();
        knight.setFocusDuration(Knight.FOCUS_DURATION);
    }
}
