package com.HallowKnight.Controller;

import com.HallowKnight.Model.Enemies.GroundEnemy;
import com.HallowKnight.Model.FixtureType;
import com.HallowKnight.Model.Knight.Knight;
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
            || userDataA == FixtureType.DEADLY && userDataB == FixtureType.KNIGHT
            || userDataA == FixtureType.KNIGHT && userDataB == FixtureType.ENEMY
            || userDataA == FixtureType.ENEMY && userDataB == FixtureType.KNIGHT) {
            knight.takeDamage(1);
        }

        if (userDataA == FixtureType.NAIL && userDataB == FixtureType.ENEMY) {
            GroundEnemy enemy = (GroundEnemy) contact.getFixtureB().getBody().getUserData();
            if (enemy != null) enemy.takeDamage();
        } else if (userDataA == FixtureType.ENEMY && userDataB == FixtureType.NAIL) {
            GroundEnemy enemy = (GroundEnemy) contact.getFixtureA().getBody().getUserData();
            if (enemy != null) enemy.takeDamage();
        }

        if (userDataA==FixtureType.KNIGHT_BOTTOM && userDataB==FixtureType.GROUND){
            knight.incrementTouchingGround();
        } else if(userDataA==FixtureType.GROUND && userDataB==FixtureType.KNIGHT_BOTTOM){
            knight.incrementTouchingGround();
        }
    }

    @Override
    public void endContact(Contact contact) {
        Object userDataA = contact.getFixtureA().getUserData();
        Object userDataB = contact.getFixtureB().getUserData();

        if (userDataA==FixtureType.KNIGHT_BOTTOM && userDataB==FixtureType.GROUND){
            knight.decrementTouchingGround();
        } else if(userDataA==FixtureType.GROUND && userDataB==FixtureType.KNIGHT_BOTTOM){
            knight.decrementTouchingGround();
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold manifold) {
        Object userDataA = contact.getFixtureA().getUserData();
        Object userDataB = contact.getFixtureB().getUserData();

        if (userDataA == FixtureType.KNIGHT && userDataB == FixtureType.ENEMY
            || userDataA == FixtureType.ENEMY && userDataB == FixtureType.KNIGHT) {
            contact.setEnabled(false);
        }
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse contactImpulse) {

    }
}
