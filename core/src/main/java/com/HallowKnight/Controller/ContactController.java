package com.HallowKnight.Controller;

import com.HallowKnight.Model.Enemies.Enemy;
import com.HallowKnight.Model.FixtureType;
import com.HallowKnight.Model.Knight.Knight;
import com.HallowKnight.Model.Knight.Nail.Nail;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

import java.util.ArrayList;
import java.util.List;

public class ContactController implements ContactListener {
    private static ContactController instance;
    private Knight knight;
    public List<ContactListener> contactListeners;

    public ContactController(Knight knight) {
        this.knight = knight;
        contactListeners=new ArrayList<>();
        instance=this;
    }

    public static ContactController getInstance(){
        return instance;
    }

    @Override
    public void beginContact(Contact contact) {

        for (ContactListener listener:contactListeners){
            listener.beginContact(contact);
        }
        Object userDataA = contact.getFixtureA().getUserData();
        Object userDataB = contact.getFixtureB().getUserData();


    }

    @Override
    public void endContact(Contact contact) {

        for (ContactListener listener:contactListeners){
            listener.endContact(contact);
        }
        Object userDataA = contact.getFixtureA().getUserData();
        Object userDataB = contact.getFixtureB().getUserData();

    }

    @Override
    public void preSolve(Contact contact, Manifold manifold) {
        for (ContactListener listener:contactListeners){
            listener.preSolve(contact,manifold);
        }
        Object userDataA = contact.getFixtureA().getUserData();
        Object userDataB = contact.getFixtureB().getUserData();

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse contactImpulse) {
        for (ContactListener listener:contactListeners){
            listener.postSolve(contact,contactImpulse);
        }
    }
}
