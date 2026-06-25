package com.HallowKnight.Controller;

import com.HallowKnight.Model.FixtureType;
import com.HallowKnight.Model.Knight;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

public class ContactController implements ContactListener {
    private Knight knight;

    public ContactController(Knight knight) {
        this.knight = knight;
    }

    @Override
    public void beginContact(Contact contact) {
        Object userDataA = contact.getFixtureA().getUserData();
        Object userDataB = contact.getFixtureB().getUserData();

        if (userDataA == FixtureType.KNIGHT && userDataB == FixtureType.DEADLY
            || userDataA == FixtureType.DEADLY && userDataB == FixtureType.KNIGHT) {
            knight.takeDamage(1);
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold manifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse contactImpulse) {

    }
}
