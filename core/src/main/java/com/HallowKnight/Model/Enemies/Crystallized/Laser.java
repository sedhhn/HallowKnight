package com.HallowKnight.Model.Enemies.Crystallized;

import com.HallowKnight.Controller.ContactController;
import com.HallowKnight.Controller.Managers.GameAssetManager;
import com.HallowKnight.HallowKnight;
import com.HallowKnight.Model.Enemies.Crystallized.State.Angry;
import com.HallowKnight.Model.FixtureType;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Laser extends Sprite {
    World world;
    Crystallized crystallized;
    public Body b2Body;
    List<Sprite> laserRegions;
    LaserContactManager laserContactManager;

    private Array<TextureAtlas.AtlasRegion> frames;
    private Animation<TextureRegion> laserAnimation;
    private float stateTime;

    public Laser(Crystallized crystallized) {
        super(GameAssetManager.crystalLaserAtlas.findRegion("CrystalLaser"));
        this.crystallized = crystallized;
        this.world = crystallized.getWorld();

        frames = GameAssetManager.crystalLaserAtlas.findRegions("CrystalLaser");
        frames.sort(Comparator.comparingInt(a -> a.index));
        laserAnimation = new Animation<>(Crystallized.laserAttackDuration / frames.size, frames);
        stateTime = 0;

        laserRegions=new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            Sprite sprite=new Sprite(GameAssetManager.crystalLaserAtlas.findRegion("CrystalLaser"));
            laserRegions.add(sprite);
            sprite.setBounds(0, 0
                , getWidth() / HallowKnight.PPM
                , getHeight() / HallowKnight.PPM);
            sprite.setPosition((crystallized.getB2Body().getPosition().x
                + (crystallized.isFacingRight() ? 500 : -500) / HallowKnight.PPM)+(-500+i*117)/HallowKnight.PPM
                ,crystallized.getB2Body().getPosition().y-getHeight()/3.5f);
        }
        setBounds(0, 0
            , getWidth() / HallowKnight.PPM
            , getHeight() / HallowKnight.PPM);
    }

    private void defineLaser() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(crystallized.getB2Body().getPosition().x
                + (crystallized.isFacingRight() ? 500 : -500) / HallowKnight.PPM
            , crystallized.getB2Body().getPosition().y
        );
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        b2Body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.isSensor = true;

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(500 / HallowKnight.PPM, 32 / HallowKnight.PPM);
        fixtureDef.shape = shape;
        b2Body.createFixture(fixtureDef).setUserData(FixtureType.LASER);
    }

    public void update(float dt) {
        if (stateTime>Crystallized.laserAttackDuration/5 && laserContactManager==null){
            laserContactManager=new LaserContactManager();
            ContactController.getInstance().contactListeners.add(laserContactManager);
            defineLaser();
        }
        stateTime += dt;
        for (int i = 0; i < laserRegions.size(); i++) {
            laserRegions.get(i).setRegion(laserAnimation.getKeyFrame(stateTime));
            laserRegions.get(i).setPosition((crystallized.getB2Body().getPosition().x
                    + (crystallized.isFacingRight() ? 540 : -500) / HallowKnight.PPM)+(-500+i*117)/HallowKnight.PPM
                ,crystallized.getB2Body().getPosition().y-getHeight()/3f+(laserAnimation.getKeyFrameIndex(stateTime))/HallowKnight.PPM);
        }
        if (b2Body!=null) {
            b2Body.setTransform(
                crystallized.getB2Body().getPosition().x
                    + (crystallized.isFacingRight() ? 540 : -500) / HallowKnight.PPM
                , crystallized.getB2Body().getPosition().y + 10 / HallowKnight.PPM
                , 0
            );
        }
        HallowKnight.hallowKnight.getBatch().begin();
        for (Sprite s: laserRegions){
            s.draw(HallowKnight.hallowKnight.getBatch());
        }
        HallowKnight.hallowKnight.getBatch().end();
    }

    public void destroy() {
        world.destroyBody(b2Body);
        ContactController.getInstance().contactListeners.remove(laserContactManager);
    }
}
