package com.HallowKnight.Model.Enemies.Crystallized;

import com.HallowKnight.Model.FixtureType;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

public class ContactManager implements ContactListener {
    Crystallized crystallized;
    public ContactManager(Crystallized crystallized){
        this.crystallized=crystallized;
    }

    @Override
    public void beginContact(Contact contact) {
        Object userDataA = contact.getFixtureA().getUserData();
        Object userDataB = contact.getFixtureB().getUserData();

        //Left Sensor
        if (userDataA== FixtureType.ENEMY_LEFT && userDataB==FixtureType.GROUND){
            if (contact.getFixtureA().getBody().getUserData() instanceof Crystallized c) {
                if (c==crystallized) {
                    crystallized.getSurroundSensors().leftSensor++;
                }
            }
        } else if(userDataA== FixtureType.GROUND && userDataB==FixtureType.ENEMY_LEFT){
            if (contact.getFixtureB().getBody().getUserData() instanceof Crystallized c) {
                if (c==crystallized) {
                    crystallized.getSurroundSensors().leftSensor++;
                }
            }
        }

        //Right Sensor
        if (userDataA== FixtureType.ENEMY_RIGHT && userDataB==FixtureType.GROUND){
            if (contact.getFixtureA().getBody().getUserData() instanceof Crystallized c) {
                if (c==crystallized) {
                    crystallized.getSurroundSensors().rightSensor++;
                }
            }
        } else if(userDataA== FixtureType.GROUND && userDataB==FixtureType.ENEMY_RIGHT){
            if (contact.getFixtureB().getBody().getUserData() instanceof Crystallized c) {
                if (c==crystallized) {
                    crystallized.getSurroundSensors().rightSensor++;
                }
            }
        }

        //Bottom Left Sensor
        if (userDataA== FixtureType.ENEMY_BOTTOM_LEFT && userDataB==FixtureType.GROUND){
            if (contact.getFixtureA().getBody().getUserData() instanceof Crystallized c) {
                if (c==crystallized) {
                    crystallized.getSurroundSensors().bottomLeftSensor++;
                }
            }
        } else if(userDataA== FixtureType.GROUND && userDataB==FixtureType.ENEMY_BOTTOM_LEFT){
            if (contact.getFixtureB().getBody().getUserData() instanceof Crystallized c) {
                if (c==crystallized) {
                    crystallized.getSurroundSensors().bottomLeftSensor++;
                }
            }
        }

        //Bottom Right Sensor
        if (userDataA== FixtureType.ENEMY_BOTTOM_RIGHT && userDataB==FixtureType.GROUND){
            if (contact.getFixtureA().getBody().getUserData() instanceof Crystallized c) {
                if (c==crystallized) {
                    crystallized.getSurroundSensors().bottomRightSensor++;
                }
            }
        } else if(userDataA== FixtureType.GROUND && userDataB==FixtureType.ENEMY_BOTTOM_RIGHT){
            if (contact.getFixtureB().getBody().getUserData() instanceof Crystallized c) {
                if (c==crystallized) {
                    crystallized.getSurroundSensors().bottomRightSensor++;
                }
            }
        }

        //Right Radar Sensor
        if (userDataA== FixtureType.ENEMY_RIGHT_RADAR && userDataB==FixtureType.KNIGHT){
            if (contact.getFixtureA().getBody().getUserData() instanceof Crystallized c) {
                if (c==crystallized) {
                    crystallized.getSurroundSensors().rightRadarSensor++;
                }
            }
        } else if(userDataA== FixtureType.KNIGHT && userDataB==FixtureType.ENEMY_RIGHT_RADAR){
            if (contact.getFixtureB().getBody().getUserData() instanceof Crystallized c) {
                if (c==crystallized) {
                    crystallized.getSurroundSensors().rightRadarSensor++;
                }
            }
        }

        //Left Radar Sensor
        if (userDataA== FixtureType.ENEMY_LEFT_RADAR && userDataB==FixtureType.KNIGHT){
            if (contact.getFixtureA().getBody().getUserData() instanceof Crystallized c) {
                if (c==crystallized) {
                    crystallized.getSurroundSensors().leftRadarSensor++;
                }
            }
        } else if(userDataA== FixtureType.KNIGHT && userDataB==FixtureType.ENEMY_LEFT_RADAR) {
            if (contact.getFixtureB().getBody().getUserData() instanceof Crystallized c) {
                if (c==crystallized) {
                    crystallized.getSurroundSensors().leftRadarSensor++;
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
            if (contact.getFixtureA().getBody().getUserData() instanceof Crystallized c) {
                if (c==crystallized) {
                    crystallized.getSurroundSensors().leftSensor--;
                }
            }
        } else if(userDataA== FixtureType.GROUND && userDataB==FixtureType.ENEMY_LEFT){
            if (contact.getFixtureB().getBody().getUserData() instanceof Crystallized c) {
                if (c==crystallized) {
                    crystallized.getSurroundSensors().leftSensor--;
                }
            }
        }

        //Right Sensor
        if (userDataA== FixtureType.ENEMY_RIGHT && userDataB==FixtureType.GROUND){
            if (contact.getFixtureA().getBody().getUserData() instanceof Crystallized c) {
                if (c==crystallized) {
                    crystallized.getSurroundSensors().rightSensor--;
                }
            }
        } else if(userDataA== FixtureType.GROUND && userDataB==FixtureType.ENEMY_RIGHT){
            if (contact.getFixtureB().getBody().getUserData() instanceof Crystallized c) {
                if (c==crystallized) {
                    crystallized.getSurroundSensors().rightSensor--;
                }
            }
        }

        //Bottom Left Sensor
        if (userDataA== FixtureType.ENEMY_BOTTOM_LEFT && userDataB==FixtureType.GROUND){
            if (contact.getFixtureA().getBody().getUserData() instanceof Crystallized c) {
                if (c==crystallized) {
                    crystallized.getSurroundSensors().bottomLeftSensor--;
                }
            }
        } else if(userDataA== FixtureType.GROUND && userDataB==FixtureType.ENEMY_BOTTOM_LEFT){
            if (contact.getFixtureB().getBody().getUserData() instanceof Crystallized c) {
                if (c==crystallized) {
                    crystallized.getSurroundSensors().bottomLeftSensor--;
                }
            }
        }

        //Bottom Right Sensor
        if (userDataA== FixtureType.ENEMY_BOTTOM_RIGHT && userDataB==FixtureType.GROUND){
            if (contact.getFixtureA().getBody().getUserData() instanceof Crystallized c) {
                if (c==crystallized) {
                    crystallized.getSurroundSensors().bottomRightSensor--;
                }
            }
        } else if(userDataA== FixtureType.GROUND && userDataB==FixtureType.ENEMY_BOTTOM_RIGHT){
            if (contact.getFixtureB().getBody().getUserData() instanceof Crystallized c) {
                if (c==crystallized) {
                    crystallized.getSurroundSensors().bottomRightSensor--;
                }
            }
        }

        //Right Radar Sensor
        if (userDataA== FixtureType.ENEMY_RIGHT_RADAR && userDataB==FixtureType.KNIGHT){
            if (contact.getFixtureA().getBody().getUserData() instanceof Crystallized c) {
                if (c==crystallized) {
                    crystallized.getSurroundSensors().rightRadarSensor--;
                }
            }
        } else if(userDataA== FixtureType.KNIGHT && userDataB==FixtureType.ENEMY_RIGHT_RADAR){
            if (contact.getFixtureB().getBody().getUserData() instanceof Crystallized c) {
                if (c==crystallized) {
                    crystallized.getSurroundSensors().rightRadarSensor--;
                }
            }
        }

        //Left Radar Sensor
        if (userDataA== FixtureType.ENEMY_LEFT_RADAR && userDataB==FixtureType.KNIGHT){
            if (contact.getFixtureA().getBody().getUserData() instanceof Crystallized c) {
                if (c==crystallized) {
                    crystallized.getSurroundSensors().leftRadarSensor--;
                }
            }
        } else if(userDataA== FixtureType.KNIGHT && userDataB==FixtureType.ENEMY_LEFT_RADAR){
            if (contact.getFixtureB().getBody().getUserData() instanceof Crystallized c) {
                if (c==crystallized) {
                    crystallized.getSurroundSensors().leftRadarSensor--;
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
