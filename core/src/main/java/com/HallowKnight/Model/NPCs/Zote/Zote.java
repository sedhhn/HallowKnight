package com.HallowKnight.Model.NPCs.Zote;

import com.HallowKnight.Controller.ContactController;
import com.HallowKnight.HallowKnight;
import com.HallowKnight.Model.FixtureType;
import com.HallowKnight.Model.NPCs.NPC;
import com.HallowKnight.Model.NPCs.Zote.State.Idle;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Zote extends NPC {
    SurroundSensors surroundSensors;
    ContactManager contactManager;
    public Zote(TextureAtlas.AtlasRegion atlasRegion, World world, Vector2 position) {
        super(atlasRegion, world, position);
        defineNPC(position);
        this.contactManager=new ContactManager(this);
        ContactController.getInstance().contactListeners.add(contactManager);
        this.state=new Idle(this);
        setBounds(0, 0
            ,getWidth()/HallowKnight.PPM
            ,getHeight()/HallowKnight.PPM);
    }

    @Override
    protected void defineNPC(Vector2 position) {
        super.defineNPC(position);
        float hx=getWidth()/6f/ HallowKnight.PPM;
        float hy=getHeight()/3f/HallowKnight.PPM;

        FixtureDef fixtureDef=new FixtureDef();
        PolygonShape shape=new PolygonShape();
        shape.setAsBox(hx,hy);
        fixtureDef.shape=shape;

        b2Body.createFixture(fixtureDef).setUserData(FixtureType.NPC);
        b2Body.setUserData(this);
        surroundSensors=new SurroundSensors();
        surroundSensors.createSensors(b2Body,hx,hy);
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        setPosition(b2Body.getPosition().x-getWidth()/2f,b2Body.getPosition().y-getHeight()/2.5f);
    }

    public SurroundSensors getSurroundSensors(){
        return surroundSensors;
    }
}
