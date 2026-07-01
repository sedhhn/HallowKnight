package com.HallowKnight.Model.NPCs.Zote;

import com.HallowKnight.Model.FixtureType;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

public class ContactManager implements ContactListener {
    Zote zote;
    public ContactManager(Zote zote){
        this.zote=zote;
    }
    @Override
    public void beginContact(Contact contact) {
        Object userDataA = contact.getFixtureA().getUserData();
        Object userDataB = contact.getFixtureB().getUserData();

        //Radar Sensor
        if (userDataA== FixtureType.NPC_RADAR && userDataB==FixtureType.KNIGHT){
            if (contact.getFixtureA().getBody().getUserData() instanceof Zote c) {
                if (c==zote) {
                    zote.getSurroundSensors().radarSensor++;
                }
            }
        } else if(userDataA== FixtureType.KNIGHT && userDataB==FixtureType.NPC_RADAR){
            if (contact.getFixtureB().getBody().getUserData() instanceof Zote c) {
                if (c==zote) {
                    zote.getSurroundSensors().radarSensor++;
                }
            }
        }
    }

    @Override
    public void endContact(Contact contact) {
        Object userDataA = contact.getFixtureA().getUserData();
        Object userDataB = contact.getFixtureB().getUserData();

        //Radar Sensor
        if (userDataA== FixtureType.NPC_RADAR && userDataB==FixtureType.KNIGHT){
            if (contact.getFixtureA().getBody().getUserData() instanceof Zote c) {
                if (c==zote) {
                    zote.getSurroundSensors().radarSensor--;
                }
            }
        } else if(userDataA== FixtureType.KNIGHT && userDataB==FixtureType.NPC_RADAR){
            if (contact.getFixtureB().getBody().getUserData() instanceof Zote c) {
                if (c==zote) {
                    zote.getSurroundSensors().radarSensor--;
                }
            }
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
