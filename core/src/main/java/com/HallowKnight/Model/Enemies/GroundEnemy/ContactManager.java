package com.HallowKnight.Model.Enemies.GroundEnemy;

import com.HallowKnight.Model.Enemies.GroundEnemy.GroundEnemy;
import com.HallowKnight.Model.FixtureType;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

public class ContactManager implements ContactListener {
    GroundEnemy groundEnemy;
    public ContactManager(GroundEnemy groundEnemy){
        this.groundEnemy=groundEnemy;
    }
    @Override
    public void beginContact(Contact contact) {
        Object userDataA = contact.getFixtureA().getUserData();
        Object userDataB = contact.getFixtureB().getUserData();

        //Left Sensor
        if (userDataA== FixtureType.ENEMY_LEFT && userDataB==FixtureType.GROUND){
            if (contact.getFixtureA().getBody().getUserData() instanceof GroundEnemy c) {
                if (c==groundEnemy) {
                    groundEnemy.getSurroundSensors().leftSensor++;
                }
            }
        } else if(userDataA== FixtureType.GROUND && userDataB==FixtureType.ENEMY_LEFT){
            if (contact.getFixtureB().getBody().getUserData() instanceof GroundEnemy c) {
                if (c==groundEnemy) {
                    groundEnemy.getSurroundSensors().leftSensor++;
                }
            }
        }

        //Right Sensor
        if (userDataA== FixtureType.ENEMY_RIGHT && userDataB==FixtureType.GROUND){
            if (contact.getFixtureA().getBody().getUserData() instanceof GroundEnemy c) {
                if (c==groundEnemy) {
                    groundEnemy.getSurroundSensors().rightSensor++;
                }
            }
        } else if(userDataA== FixtureType.GROUND && userDataB==FixtureType.ENEMY_RIGHT){
            if (contact.getFixtureB().getBody().getUserData() instanceof GroundEnemy c) {
                if (c==groundEnemy) {
                    groundEnemy.getSurroundSensors().rightSensor++;
                }
            }
        }

        //Bottom Left Sensor
        if (userDataA== FixtureType.ENEMY_BOTTOM_LEFT && userDataB==FixtureType.GROUND){
            if (contact.getFixtureA().getBody().getUserData() instanceof GroundEnemy c) {
                if (c==groundEnemy) {
                    groundEnemy.getSurroundSensors().bottomLeftSensor++;
                }
            }
        } else if(userDataA== FixtureType.GROUND && userDataB==FixtureType.ENEMY_BOTTOM_LEFT){
            if (contact.getFixtureB().getBody().getUserData() instanceof GroundEnemy c) {
                if (c==groundEnemy) {
                    groundEnemy.getSurroundSensors().bottomLeftSensor++;
                }
            }
        }

        //Bottom Right Sensor
        if (userDataA== FixtureType.ENEMY_BOTTOM_RIGHT && userDataB==FixtureType.GROUND){
            if (contact.getFixtureA().getBody().getUserData() instanceof GroundEnemy c) {
                if (c==groundEnemy) {
                    groundEnemy.getSurroundSensors().bottomRightSensor++;
                }
            }
        } else if(userDataA== FixtureType.GROUND && userDataB==FixtureType.ENEMY_BOTTOM_RIGHT){
            if (contact.getFixtureB().getBody().getUserData() instanceof GroundEnemy c) {
                if (c==groundEnemy) {
                    groundEnemy.getSurroundSensors().bottomRightSensor++;
                }
            }
        }
    }

    @Override
    public void endContact(Contact contact) {
        Object userDataA = contact.getFixtureA().getUserData();
        Object userDataB = contact.getFixtureB().getUserData();

        //Left Sensor
        if (userDataA== FixtureType.ENEMY_LEFT && userDataB==FixtureType.GROUND){
            if (contact.getFixtureA().getBody().getUserData() instanceof GroundEnemy c) {
                if (c==groundEnemy) {
                    groundEnemy.getSurroundSensors().leftSensor--;
                }
            }
        } else if(userDataA== FixtureType.GROUND && userDataB==FixtureType.ENEMY_LEFT){
            if (contact.getFixtureB().getBody().getUserData() instanceof GroundEnemy c) {
                if (c==groundEnemy) {
                    groundEnemy.getSurroundSensors().leftSensor--;
                }
            }
        }

        //Right Sensor
        if (userDataA== FixtureType.ENEMY_RIGHT && userDataB==FixtureType.GROUND){
            if (contact.getFixtureA().getBody().getUserData() instanceof GroundEnemy c) {
                if (c==groundEnemy) {
                    groundEnemy.getSurroundSensors().rightSensor--;
                }
            }
        } else if(userDataA== FixtureType.GROUND && userDataB==FixtureType.ENEMY_RIGHT){
            if (contact.getFixtureB().getBody().getUserData() instanceof GroundEnemy c) {
                if (c==groundEnemy) {
                    groundEnemy.getSurroundSensors().rightSensor--;
                }
            }
        }

        //Bottom Left Sensor
        if (userDataA== FixtureType.ENEMY_BOTTOM_LEFT && userDataB==FixtureType.GROUND){
            if (contact.getFixtureA().getBody().getUserData() instanceof GroundEnemy c) {
                if (c==groundEnemy) {
                    groundEnemy.getSurroundSensors().bottomLeftSensor--;
                }
            }
        } else if(userDataA== FixtureType.GROUND && userDataB==FixtureType.ENEMY_BOTTOM_LEFT){
            if (contact.getFixtureB().getBody().getUserData() instanceof GroundEnemy c) {
                if (c==groundEnemy) {
                    groundEnemy.getSurroundSensors().bottomLeftSensor--;
                }
            }
        }

        //Bottom Right Sensor
        if (userDataA== FixtureType.ENEMY_BOTTOM_RIGHT && userDataB==FixtureType.GROUND){
            if (contact.getFixtureA().getBody().getUserData() instanceof GroundEnemy c) {
                if (c==groundEnemy) {
                    groundEnemy.getSurroundSensors().bottomRightSensor--;
                }
            }
        } else if(userDataA== FixtureType.GROUND && userDataB==FixtureType.ENEMY_BOTTOM_RIGHT){
            if (contact.getFixtureB().getBody().getUserData() instanceof GroundEnemy c) {
                if (c==groundEnemy) {
                    groundEnemy.getSurroundSensors().bottomRightSensor--;
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
