package com.HallowKnight.Controller;

import com.HallowKnight.Model.Enemies.Enemy;
import com.HallowKnight.Model.Enemies.GroundEnemy;
import com.HallowKnight.Model.FixtureType;
import com.HallowKnight.Model.Knight.Knight;
import com.HallowKnight.Model.Knight.Nail.Nail;
import com.badlogic.gdx.math.Vector2;
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

        if (userDataA == FixtureType.KNIGHT && userDataB == FixtureType.DEADLY
            || userDataA == FixtureType.DEADLY && userDataB == FixtureType.KNIGHT
            || userDataA == FixtureType.KNIGHT && userDataB == FixtureType.ENEMY
            || userDataA == FixtureType.ENEMY && userDataB == FixtureType.KNIGHT) {
            knight.takeDamage(1);
        }

        if (userDataA==FixtureType.KNIGHT_BOTTOM && userDataB==FixtureType.GROUND){
            knight.getSurroundSensors().bottomSensor++;
        } else if(userDataA==FixtureType.GROUND && userDataB==FixtureType.KNIGHT_BOTTOM){
            knight.getSurroundSensors().bottomSensor++;
        }

        if (userDataA==FixtureType.KNIGHT_BOTTOM && userDataB==FixtureType.PLATFORM){
            knight.getSurroundSensors().bottomSensor++;
        } else if(userDataA==FixtureType.PLATFORM && userDataB==FixtureType.KNIGHT_BOTTOM){
            knight.getSurroundSensors().bottomSensor++;
        }

        //knight left sensor
        if (userDataA==FixtureType.KNIGHT_LEFT && userDataB==FixtureType.GROUND){
            knight.getSurroundSensors().leftSensor++;
        } else if(userDataA==FixtureType.GROUND && userDataB==FixtureType.KNIGHT_LEFT){
            knight.getSurroundSensors().leftSensor++;
        }

        if (userDataA==FixtureType.KNIGHT_LEFT && userDataB==FixtureType.PLATFORM){
            knight.getSurroundSensors().leftSensor++;
        } else if(userDataA==FixtureType.PLATFORM && userDataB==FixtureType.KNIGHT_LEFT){
            knight.getSurroundSensors().leftSensor++;
        }

        //knight right sensor
        if (userDataA==FixtureType.KNIGHT_RIGHT && userDataB==FixtureType.GROUND){
            knight.getSurroundSensors().rightSensor++;
        } else if(userDataA==FixtureType.GROUND && userDataB==FixtureType.KNIGHT_RIGHT){
            knight.getSurroundSensors().rightSensor++;
        }

        if (userDataA==FixtureType.KNIGHT_RIGHT && userDataB==FixtureType.PLATFORM){
            knight.getSurroundSensors().rightSensor++;
        } else if(userDataA==FixtureType.PLATFORM && userDataB==FixtureType.KNIGHT_RIGHT){
            knight.getSurroundSensors().rightSensor++;
        }

        //Pogo jump
        if (userDataA==FixtureType.NAIL && userDataB==FixtureType.DEADLY){
            Nail nail= (Nail) contact.getFixtureA().getBody().getUserData();
            if (nail!=null) nail.getState().onContactWithDeadly();
        } else if(userDataA==FixtureType.DEADLY && userDataB==FixtureType.NAIL){
            Nail nail= (Nail) contact.getFixtureB().getBody().getUserData();
            if (nail!=null) nail.getState().onContactWithDeadly();
        }

        if (userDataA == FixtureType.NAIL && userDataB == FixtureType.ENEMY) {
            Enemy enemy = (Enemy) contact.getFixtureB().getBody().getUserData();
            if (enemy != null) enemy.takeDamage();
            Nail nail= (Nail) contact.getFixtureA().getBody().getUserData();
            if (nail!=null) nail.getState().onContactWithDeadly();
        } else if (userDataA == FixtureType.ENEMY && userDataB == FixtureType.NAIL) {
            Enemy enemy = (Enemy) contact.getFixtureA().getBody().getUserData();
            if (enemy != null) enemy.takeDamage();
            Nail nail= (Nail) contact.getFixtureB().getBody().getUserData();
            if (nail!=null) nail.getState().onContactWithDeadly();
        }
    }

    @Override
    public void endContact(Contact contact) {
        for (ContactListener listener:contactListeners){
            listener.endContact(contact);
        }
        Object userDataA = contact.getFixtureA().getUserData();
        Object userDataB = contact.getFixtureB().getUserData();

        if (userDataA==FixtureType.KNIGHT_BOTTOM && userDataB==FixtureType.GROUND){
            knight.getSurroundSensors().bottomSensor--;
        } else if(userDataA==FixtureType.GROUND && userDataB==FixtureType.KNIGHT_BOTTOM){
            knight.getSurroundSensors().bottomSensor--;
        }

        if (userDataA==FixtureType.KNIGHT_BOTTOM && userDataB==FixtureType.PLATFORM){
            knight.getSurroundSensors().bottomSensor--;
        } else if(userDataA==FixtureType.PLATFORM && userDataB==FixtureType.KNIGHT_BOTTOM){
            knight.getSurroundSensors().bottomSensor--;
        }

        //knight left sensor
        if (userDataA==FixtureType.KNIGHT_LEFT && userDataB==FixtureType.GROUND){
            knight.getSurroundSensors().leftSensor--;
        } else if(userDataA==FixtureType.GROUND && userDataB==FixtureType.KNIGHT_LEFT){
            knight.getSurroundSensors().leftSensor--;
        }

        if (userDataA==FixtureType.KNIGHT_LEFT && userDataB==FixtureType.PLATFORM){
            knight.getSurroundSensors().leftSensor--;
        } else if(userDataA==FixtureType.PLATFORM && userDataB==FixtureType.KNIGHT_LEFT){
            knight.getSurroundSensors().leftSensor--;
        }

        //knight right sensor
        if (userDataA==FixtureType.KNIGHT_RIGHT && userDataB==FixtureType.GROUND){
            knight.getSurroundSensors().rightSensor--;
        } else if(userDataA==FixtureType.GROUND && userDataB==FixtureType.KNIGHT_RIGHT){
            knight.getSurroundSensors().rightSensor--;
        }

        if (userDataA==FixtureType.KNIGHT_RIGHT && userDataB==FixtureType.PLATFORM){
            knight.getSurroundSensors().rightSensor--;
        } else if(userDataA==FixtureType.PLATFORM && userDataB==FixtureType.KNIGHT_RIGHT){
            knight.getSurroundSensors().rightSensor--;
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold manifold) {
        for (ContactListener listener:contactListeners){
            listener.preSolve(contact,manifold);
        }
        Object userDataA = contact.getFixtureA().getUserData();
        Object userDataB = contact.getFixtureB().getUserData();

        if (userDataA == FixtureType.KNIGHT && userDataB == FixtureType.ENEMY
            || userDataA == FixtureType.ENEMY && userDataB == FixtureType.KNIGHT) {
            contact.setEnabled(false);
        }
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse contactImpulse) {
        for (ContactListener listener:contactListeners){
            listener.postSolve(contact,contactImpulse);
        }
    }
}
