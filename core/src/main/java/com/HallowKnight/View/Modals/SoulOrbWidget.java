package com.HallowKnight.View.Modals;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;

public class SoulOrbWidget extends Widget {
    private final TextureRegion fullRegion;
    private float percent = 1f;

    public SoulOrbWidget(TextureRegion fullRegion) {
        this.fullRegion = fullRegion;
    }

    public void setPercent(float percent) {
        this.percent = Math.min(1f, Math.max(0f, percent));
        invalidate(); // به ویجت می‌گوید نیاز به بازخوانی دارد
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        validate();

        // موقعیت و ابعاد واقعی ویجت در جدول (توسط Table تعیین می‌شود)
        float x = getX();
        float y = getY();
        float width = getWidth();
        float height = getHeight();

        // ۱. ابعاد بافت اصلی
        int texWidth = fullRegion.getRegionWidth();
        int texHeight = fullRegion.getRegionHeight();

        // ۲. محاسبه ارتفاع بریده‌شده بافت
        int visibleTexHeight = Math.max(1, (int) (texHeight * percent));

        // ۳. نقطه شروع برش بافت از بالا
        int srcY = fullRegion.getRegionY() + (texHeight - visibleTexHeight);
        int srcX = fullRegion.getRegionX();

        // ۴. محاسبه ارتفاع نهایی رندر روی صفحه (متناسب با درصد)
        float drawHeight = height * percent;

        // ۵. رسم بافت:
        // مایع باید همیشه کفِ Orb باقی بماند، پس y (موقعیت رسم) تغییر نمی‌کند، فقط ارتفاعش (drawHeight) کم و زیاد می‌شود.
        batch.draw(
            fullRegion.getTexture(),
            x, y,                               // موقعیت روی صفحه (چسبیده به کف سلول)
            width, drawHeight,                  // ابعادی که روی صفحه رندر می‌شود
            srcX, srcY,                         // پیکسل شروع برش از بافت اصلی
            texWidth, visibleTexHeight,         // مقدار پیکسلی که باید از بافت برداشته شود
            false, false                        // فلیپ نشدن عکس
        );
    }
}
