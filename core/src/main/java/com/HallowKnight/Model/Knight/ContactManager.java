package com.HallowKnight.Model.Knight;

import com.HallowKnight.Model.Effects.Effect;
import com.HallowKnight.Model.Enemies.Enemy;
import com.HallowKnight.Model.FalseKnight.FalseKnight;
import com.HallowKnight.Model.FixtureType;
import com.HallowKnight.Model.Knight.Nail.Nail;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

public class ContactManager implements ContactListener {
    Knight knight;
    public ContactManager(Knight knight){
        this.knight=knight;
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


        //Enemy and SoulScream
        if (userDataA== FixtureType.SOUL_SCREAM && userDataB==FixtureType.ENEMY){
            Enemy enemy = (Enemy) contact.getFixtureB().getBody().getUserData();
            if (enemy != null) {
                enemy.takeDamage();
            }
        } else if(userDataA== FixtureType.ENEMY && userDataB==FixtureType.SOUL_SCREAM){
            Enemy enemy = (Enemy) contact.getFixtureA().getBody().getUserData();
            if (enemy != null) {
                enemy.takeDamage();
            }
        }

        //Boss and SoulScream
        if (userDataA== FixtureType.SOUL_SCREAM && userDataB==FixtureType.BOSS){
            FalseKnight falseKnight = (FalseKnight) contact.getFixtureB().getBody().getUserData();
            if (falseKnight != null) {
                falseKnight.takeDamage(10);
            }
        } else if(userDataA== FixtureType.BOSS && userDataB==FixtureType.SOUL_SCREAM){
            FalseKnight falseKnight = (FalseKnight) contact.getFixtureA().getBody().getUserData();
            if (falseKnight != null) {
                falseKnight.takeDamage(10);
            }
        }

        //Boss and SoulBall
        if (userDataA== FixtureType.SOUL_BALL && userDataB==FixtureType.BOSS){
            FalseKnight falseKnight = (FalseKnight) contact.getFixtureB().getBody().getUserData();
            if (falseKnight != null) {
                falseKnight.takeDamage(10);
            }
        } else if(userDataA== FixtureType.BOSS && userDataB==FixtureType.SOUL_BALL){
            FalseKnight falseKnight = (FalseKnight) contact.getFixtureA().getBody().getUserData();
            if (falseKnight != null) {
                falseKnight.takeDamage(10);
            }
        }

        //Enemy and SoulBall
        if (userDataA== FixtureType.SOUL_BALL && userDataB==FixtureType.ENEMY){
            Enemy enemy = (Enemy) contact.getFixtureB().getBody().getUserData();
            if (enemy != null) {
                enemy.takeDamage();
            }
        } else if(userDataA== FixtureType.ENEMY && userDataB==FixtureType.SOUL_BALL){
            Enemy enemy = (Enemy) contact.getFixtureA().getBody().getUserData();
            if (enemy != null) {
                enemy.takeDamage();
            }
        }

        //Ground and SoulBall
        if (userDataA== FixtureType.SOUL_BALL && userDataB==FixtureType.GROUND){
            Effect soulBall = (Effect) contact.getFixtureA().getBody().getUserData();
            if (soulBall != null) {
                soulBall.setOver(true);
            }
        } else if(userDataA== FixtureType.GROUND && userDataB==FixtureType.SOUL_BALL){
            Effect soulBall = (Effect) contact.getFixtureB().getBody().getUserData();
            if (soulBall != null) {
                soulBall.setOver(true);
            }
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
            if (enemy != null) {
                enemy.takeDamage();
                knight.increaseSoul(8);
            }
            Nail nail= (Nail) contact.getFixtureA().getBody().getUserData();
            if (nail!=null) nail.getState().onContactWithDeadly();
        } else if (userDataA == FixtureType.ENEMY && userDataB == FixtureType.NAIL) {
            Enemy enemy = (Enemy) contact.getFixtureA().getBody().getUserData();
            if (enemy != null) {
                enemy.takeDamage();
                knight.increaseSoul(8);
            }
            Nail nail= (Nail) contact.getFixtureB().getBody().getUserData();
            if (nail!=null) nail.getState().onContactWithDeadly();
        }
    }

    @Override
    public void endContact(Contact contact) {
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
    public void preSolve(Contact contact, Manifold oldManifold) {
        Object userDataA = contact.getFixtureA().getUserData();
        Object userDataB = contact.getFixtureB().getUserData();

        if (userDataA == FixtureType.KNIGHT && userDataB == FixtureType.ENEMY
            || userDataA == FixtureType.ENEMY && userDataB == FixtureType.KNIGHT) {
            contact.setEnabled(false);
        }
        if (userDataA == FixtureType.KNIGHT && userDataB == FixtureType.NPC
            || userDataA == FixtureType.NPC && userDataB == FixtureType.KNIGHT) {
            contact.setEnabled(false);
        }
        if (userDataA == FixtureType.KNIGHT && userDataB == FixtureType.BOSS
            || userDataA == FixtureType.BOSS && userDataB == FixtureType.KNIGHT) {
            contact.setEnabled(false);
        }
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
