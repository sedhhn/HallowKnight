package com.HallowKnight.Model.Enemies.Mosquito;

import com.HallowKnight.Model.Enemies.Mosquito.Mosquito;
import com.HallowKnight.Model.FixtureType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

public class ContactManager implements ContactListener {
    Mosquito mosquito;
    public ContactManager(Mosquito mosquito){
        this.mosquito=mosquito;
    }
    @Override
    public void beginContact(Contact contact) {
        Object userDataA = contact.getFixtureA().getUserData();
        Object userDataB = contact.getFixtureB().getUserData();

        //Top Sensor
        if (userDataA== FixtureType.ENEMY_TOP && userDataB==FixtureType.GROUND){
            if (contact.getFixtureA().getBody().getUserData() instanceof Mosquito c) {
                if (c==mosquito) {
                    mosquito.getSurroundSensors().topSensor++;
                }
            }
        } else if(userDataA== FixtureType.GROUND && userDataB==FixtureType.ENEMY_TOP){
            if (contact.getFixtureB().getBody().getUserData() instanceof Mosquito c) {
                if (c==mosquito) {
                    mosquito.getSurroundSensors().topSensor++;
                }
            }
        }

        //Left Sensor
        if (userDataA== FixtureType.ENEMY_LEFT && userDataB==FixtureType.GROUND){
            if (contact.getFixtureA().getBody().getUserData() instanceof Mosquito c) {
                if (c==mosquito) {
                    mosquito.getSurroundSensors().leftSensor++;
                }
            }
        } else if(userDataA== FixtureType.GROUND && userDataB==FixtureType.ENEMY_LEFT){
            if (contact.getFixtureB().getBody().getUserData() instanceof Mosquito c) {
                if (c==mosquito) {
                    mosquito.getSurroundSensors().leftSensor++;
                }
            }
        }

        //Right Sensor
        if (userDataA== FixtureType.ENEMY_RIGHT && userDataB==FixtureType.GROUND){
            if (contact.getFixtureA().getBody().getUserData() instanceof Mosquito c) {
                if (c==mosquito) {
                    mosquito.getSurroundSensors().rightSensor++;
                }
            }
        } else if(userDataA== FixtureType.GROUND && userDataB==FixtureType.ENEMY_RIGHT){
            if (contact.getFixtureB().getBody().getUserData() instanceof Mosquito c) {
                if (c==mosquito) {
                    mosquito.getSurroundSensors().rightSensor++;
                }
            }
        }

        //Bottom Sensor
        if (userDataA== FixtureType.ENEMY_BOTTOM && userDataB==FixtureType.GROUND){
            if (contact.getFixtureA().getBody().getUserData() instanceof Mosquito c) {
                if (c==mosquito) {
                    mosquito.getSurroundSensors().bottomSensor++;
                }
            }
        } else if(userDataA== FixtureType.GROUND && userDataB==FixtureType.ENEMY_BOTTOM){
            if (contact.getFixtureB().getBody().getUserData() instanceof Mosquito c) {
                if (c==mosquito) {
                    mosquito.getSurroundSensors().bottomSensor++;
                }
            }
        }

        //Radar Sensor
        if (userDataA== FixtureType.ENEMY_CIRCULAR_RADAR && userDataB==FixtureType.KNIGHT){
            if (contact.getFixtureA().getBody().getUserData() instanceof Mosquito c) {
                if (c==mosquito) {
                    mosquito.setTargetPos(contact.getFixtureB().getBody().getPosition());
                    mosquito.getSurroundSensors().radarSensor++;
                }
            }
        } else if(userDataA== FixtureType.KNIGHT && userDataB==FixtureType.ENEMY_CIRCULAR_RADAR){
            if (contact.getFixtureB().getBody().getUserData() instanceof Mosquito c) {
                if (c==mosquito) {
                    mosquito.setTargetPos(contact.getFixtureA().getBody().getPosition());
                    mosquito.getSurroundSensors().radarSensor++;
                }
            }
        }
    }

    @Override
    public void endContact(Contact contact) {
        Object userDataA = contact.getFixtureA().getUserData();
        Object userDataB = contact.getFixtureB().getUserData();

        //Top Sensor
        if (userDataA== FixtureType.ENEMY_TOP && userDataB==FixtureType.GROUND){
            if (contact.getFixtureA().getBody().getUserData() instanceof Mosquito c) {
                if (c==mosquito) {
                    mosquito.getSurroundSensors().topSensor--;
                }
            }
        } else if(userDataA== FixtureType.GROUND && userDataB==FixtureType.ENEMY_TOP){
            if (contact.getFixtureB().getBody().getUserData() instanceof Mosquito c) {
                if (c==mosquito) {
                    mosquito.getSurroundSensors().topSensor--;
                }
            }
        }

        //Left Sensor
        if (userDataA== FixtureType.ENEMY_LEFT && userDataB==FixtureType.GROUND){
            if (contact.getFixtureA().getBody().getUserData() instanceof Mosquito c) {
                if (c==mosquito) {
                    mosquito.getSurroundSensors().leftSensor--;
                }
            }
        } else if(userDataA== FixtureType.GROUND && userDataB==FixtureType.ENEMY_LEFT){
            if (contact.getFixtureB().getBody().getUserData() instanceof Mosquito c) {
                if (c==mosquito) {
                    mosquito.getSurroundSensors().leftSensor--;
                }
            }
        }

        //Right Sensor
        if (userDataA== FixtureType.ENEMY_RIGHT && userDataB==FixtureType.GROUND){
            if (contact.getFixtureA().getBody().getUserData() instanceof Mosquito c) {
                if (c==mosquito) {
                    mosquito.getSurroundSensors().rightSensor--;
                }
            }
        } else if(userDataA== FixtureType.GROUND && userDataB==FixtureType.ENEMY_RIGHT){
            if (contact.getFixtureB().getBody().getUserData() instanceof Mosquito c) {
                if (c==mosquito) {
                    mosquito.getSurroundSensors().rightSensor--;
                }
            }
        }

        //Bottom Sensor
        if (userDataA== FixtureType.ENEMY_BOTTOM && userDataB==FixtureType.GROUND){
            if (contact.getFixtureA().getBody().getUserData() instanceof Mosquito c) {
                if (c==mosquito) {
                    mosquito.getSurroundSensors().bottomSensor--;
                }
            }
        } else if(userDataA== FixtureType.GROUND && userDataB==FixtureType.ENEMY_BOTTOM){
            if (contact.getFixtureB().getBody().getUserData() instanceof Mosquito c) {
                if (c==mosquito) {
                    mosquito.getSurroundSensors().bottomSensor--;
                }
            }
        }

        //Radar Sensor
        if (userDataA== FixtureType.ENEMY_CIRCULAR_RADAR && userDataB==FixtureType.KNIGHT){
            if (contact.getFixtureA().getBody().getUserData() instanceof Mosquito c) {
                if (c==mosquito) {
                    mosquito.getSurroundSensors().radarSensor--;
                }
            }
        } else if(userDataA== FixtureType.KNIGHT && userDataB==FixtureType.ENEMY_CIRCULAR_RADAR){
            if (contact.getFixtureB().getBody().getUserData() instanceof Mosquito c) {
                if (c==mosquito) {
                    mosquito.getSurroundSensors().radarSensor--;
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
