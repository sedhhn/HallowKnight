package com.HallowKnight.Model.Enemies.Crystallized;

import com.HallowKnight.Model.FixtureType;
import com.HallowKnight.Model.Knight.Knight;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

public class LaserContactManager implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Object userDataA = contact.getFixtureA().getUserData();
        Object userDataB = contact.getFixtureB().getUserData();

        if (userDataA== FixtureType.KNIGHT && userDataB== FixtureType.LASER){
            Knight knight=(Knight) contact.getFixtureA().getBody().getUserData();
            if (knight!=null){
                knight.takeDamage(1);
            }
        } else if(userDataA==FixtureType.LASER && userDataB==FixtureType.KNIGHT){
            Knight knight=(Knight) contact.getFixtureB().getBody().getUserData();
            if (knight!=null){
                knight.takeDamage(1);
            }
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
