package com.HallowKnight.Model;

import com.HallowKnight.HallowKnight;
import com.HallowKnight.Model.Knight.Knight;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class GameCamera extends OrthographicCamera {
    public static final float DEFAULT_SHAKE_DURATION=0.5f;
    public static final float DEFAULT_MAX_SHAKE_INTENSITY=0.1f;
    Knight knight;

    private float maxShakeIntensity;
    private float shakeIntensity;
    private float shakeDuration;
    private boolean shaking;

    private boolean bossRoomClamp;
    private float bossMinX;
    private float bossMaxX;
    private float bossMinY;
    private float bossMaxY;

    public GameCamera(float viewportWidth, float viewportHeight){
        super(viewportWidth, viewportHeight);

        this.shakeIntensity=0;
        this.shakeDuration=0;
        this.shaking=false;
        this.bossRoomClamp=false;

        float minX=Math.min(HallowKnight.BOSS_ROOM_MIN_X,HallowKnight.BOSS_ROOM_MAX_X);
        float maxX=Math.max(HallowKnight.BOSS_ROOM_MIN_X,HallowKnight.BOSS_ROOM_MAX_X);
        this.bossMinX=minX*8f/HallowKnight.PPM;
        this.bossMaxX=maxX*8f/HallowKnight.PPM;
        this.bossMinY=6f;
        this.bossMaxY=16.5f;
    }

    public void update(float dt) {
        super.update();
        if (knight!=null) {
            position.x = knight.b2Body.getPosition().x;
            position.y = knight.b2Body.getPosition().y;

            float kx=knight.b2Body.getPosition().x;
            float ky=knight.b2Body.getPosition().y;
            bossRoomClamp=kx>=bossMinX && kx<=bossMaxX && ky>=bossMinY && ky<=bossMaxY;
        }
        if (shaking){
            shake(dt);
        }
        if (bossRoomClamp){
            clampToBossRoom();
        }
    }

    private void clampToBossRoom(){
        float halfViewW=viewportWidth/2f;
        float halfViewH=viewportHeight/2f;

        if (bossMaxX-bossMinX>halfViewW*2f){
            position.x=MathUtils.clamp(position.x,bossMinX+halfViewW,bossMaxX-halfViewW);
        }
        if (bossMaxY-bossMinY>halfViewH*2f){
            position.y=MathUtils.clamp(position.y,bossMinY+halfViewH,bossMaxY-halfViewH);
        }
    }

    public void setKnight(Knight knight){
        this.knight=knight;
    }

    public void setBossRoomClamp(boolean clamp){
        this.bossRoomClamp=clamp;
    }

    public void startShake(){
        maxShakeIntensity=DEFAULT_MAX_SHAKE_INTENSITY;
        shakeIntensity=maxShakeIntensity;
        shakeDuration=DEFAULT_SHAKE_DURATION;
        shaking=true;
    }

    private void shake(float dt){
        shakeDuration-=dt;

        float progress = 1f - (shakeDuration / (shakeDuration + dt));
        float currentIntensity = maxShakeIntensity * (shakeDuration / (shakeDuration + dt));

        if (shakeDuration<=0 || currentIntensity <=0){
            shaking=false;
            shakeIntensity=0;
        }

        float offsetX= MathUtils.random(-1f,1f)*currentIntensity;
        float offsetY= MathUtils.random(-1f,1f)*currentIntensity;

        position.x=knight.b2Body.getPosition().x+offsetX;
        position.y=knight.b2Body.getPosition().y+offsetY;
    }
}
