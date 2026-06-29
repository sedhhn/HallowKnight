package com.HallowKnight.Model.Enemies.HuskHornhead;

import com.HallowKnight.Model.Enemies.Enemy;
import com.HallowKnight.Model.FixtureType;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

public class ContactManager implements ContactListener {
    HuskHornhead huskHornhead;
    public ContactManager(HuskHornhead huskHornhead){
        this.huskHornhead=huskHornhead;
    }
    @Override
    public void beginContact(Contact contact) {
        Object userDataA = contact.getFixtureA().getUserData();
        Object userDataB = contact.getFixtureB().getUserData();

        //Left Sensor
        if (userDataA== FixtureType.ENEMY_LEFT && userDataB==FixtureType.GROUND){
            huskHornhead.getSurroundSensors().leftSensor++;
        } else if(userDataA== FixtureType.GROUND && userDataB==FixtureType.ENEMY_LEFT){
            huskHornhead.getSurroundSensors().leftSensor++;
        }

        //Right Sensor
        if (userDataA== FixtureType.ENEMY_RIGHT && userDataB==FixtureType.GROUND){
            huskHornhead.getSurroundSensors().rightSensor++;
        } else if(userDataA== FixtureType.GROUND && userDataB==FixtureType.ENEMY_RIGHT){
            huskHornhead.getSurroundSensors().rightSensor++;
        }

        //Bottom Left Sensor
        if (userDataA== FixtureType.ENEMY_BOTTOM_LEFT && userDataB==FixtureType.GROUND){
            huskHornhead.getSurroundSensors().bottomLeftSensor++;
        } else if(userDataA== FixtureType.GROUND && userDataB==FixtureType.ENEMY_BOTTOM_LEFT){
            huskHornhead.getSurroundSensors().bottomLeftSensor++;
        }

        //Bottom Right Sensor
        if (userDataA== FixtureType.ENEMY_BOTTOM_RIGHT && userDataB==FixtureType.GROUND){
            huskHornhead.getSurroundSensors().bottomRightSensor++;
        } else if(userDataA== FixtureType.GROUND && userDataB==FixtureType.ENEMY_BOTTOM_RIGHT){
            huskHornhead.getSurroundSensors().bottomRightSensor++;
        }

        //Right Radar Sensor
        if (userDataA== FixtureType.ENEMY_RIGHT_RADAR && userDataB==FixtureType.KNIGHT){
            huskHornhead.getSurroundSensors().rightRadarSensor++;
        } else if(userDataA== FixtureType.KNIGHT && userDataB==FixtureType.ENEMY_RIGHT_RADAR){
            huskHornhead.getSurroundSensors().rightRadarSensor++;
        }

        //Left Radar Sensor
        if (userDataA== FixtureType.ENEMY_LEFT_RADAR && userDataB==FixtureType.KNIGHT){
            huskHornhead.getSurroundSensors().leftRadarSensor++;
        } else if(userDataA== FixtureType.KNIGHT && userDataB==FixtureType.ENEMY_LEFT_RADAR){
            huskHornhead.getSurroundSensors().leftRadarSensor++;
        }
    }

    @Override
    public void endContact(Contact contact) {
        Object userDataA = contact.getFixtureA().getUserData();
        Object userDataB = contact.getFixtureB().getUserData();

        //Left Sensor
        if (userDataA== FixtureType.ENEMY_LEFT && userDataB==FixtureType.GROUND){
            huskHornhead.getSurroundSensors().leftSensor--;
        } else if(userDataA== FixtureType.GROUND && userDataB==FixtureType.ENEMY_LEFT){
            huskHornhead.getSurroundSensors().leftSensor--;
        }

        //Right Sensor
        if (userDataA== FixtureType.ENEMY_RIGHT && userDataB==FixtureType.GROUND){
            huskHornhead.getSurroundSensors().rightSensor--;
        } else if(userDataA== FixtureType.GROUND && userDataB==FixtureType.ENEMY_RIGHT){
            huskHornhead.getSurroundSensors().rightSensor--;
        }

        //Bottom Left Sensor
        if (userDataA== FixtureType.ENEMY_BOTTOM_LEFT && userDataB==FixtureType.GROUND){
            huskHornhead.getSurroundSensors().bottomLeftSensor--;
        } else if(userDataA== FixtureType.GROUND && userDataB==FixtureType.ENEMY_BOTTOM_LEFT){
            huskHornhead.getSurroundSensors().bottomLeftSensor--;
        }

        //Bottom Right Sensor
        if (userDataA== FixtureType.ENEMY_BOTTOM_RIGHT && userDataB==FixtureType.GROUND){
            huskHornhead.getSurroundSensors().bottomRightSensor--;
        } else if(userDataA== FixtureType.GROUND && userDataB==FixtureType.ENEMY_BOTTOM_RIGHT){
            huskHornhead.getSurroundSensors().bottomRightSensor--;
        }

        //Right Radar Sensor
        if (userDataA== FixtureType.ENEMY_RIGHT_RADAR && userDataB==FixtureType.KNIGHT){
            huskHornhead.getSurroundSensors().rightRadarSensor--;
        } else if(userDataA== FixtureType.KNIGHT && userDataB==FixtureType.ENEMY_RIGHT_RADAR){
            huskHornhead.getSurroundSensors().rightRadarSensor--;
        }

        //Left Radar Sensor
        if (userDataA== FixtureType.ENEMY_LEFT_RADAR && userDataB==FixtureType.KNIGHT){
            huskHornhead.getSurroundSensors().leftRadarSensor--;
        } else if(userDataA== FixtureType.KNIGHT && userDataB==FixtureType.ENEMY_LEFT_RADAR){
            huskHornhead.getSurroundSensors().leftRadarSensor--;
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
