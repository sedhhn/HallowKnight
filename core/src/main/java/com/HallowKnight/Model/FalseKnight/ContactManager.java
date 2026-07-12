package com.HallowKnight.Model.FalseKnight;

import com.HallowKnight.Controller.Managers.AudioManager;
import com.HallowKnight.Controller.Managers.GameAssetManager;
import com.HallowKnight.Model.Charms.CharmType;
import com.HallowKnight.Model.Enemies.HuskHornhead.HuskHornhead;
import com.HallowKnight.Model.FixtureType;
import com.HallowKnight.Model.Knight.Knight;
import com.HallowKnight.Model.Map.MapObjectInitializer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

public class ContactManager implements ContactListener {
    FalseKnight falseKnight;

    public ContactManager(FalseKnight falseKnight){
        this.falseKnight=falseKnight;
    }
    @Override
    public void beginContact(Contact contact) {
        Object userDataA = contact.getFixtureA().getUserData();
        Object userDataB = contact.getFixtureB().getUserData();

        //Left Sensor
        if (userDataA== FixtureType.BOSS_LEFT && userDataB==FixtureType.GROUND){
            if (contact.getFixtureA().getBody().getUserData() instanceof FalseKnight c) {
                if (c==falseKnight) {
                    falseKnight.getSurroundSensors().leftSensor++;
                }
            }
        } else if(userDataA== FixtureType.GROUND && userDataB==FixtureType.BOSS_LEFT){
            if (contact.getFixtureB().getBody().getUserData() instanceof FalseKnight c) {
                if (c==falseKnight) {
                    falseKnight.getSurroundSensors().leftSensor++;
                }
            }
        }

        //Right Sensor
        if (userDataA== FixtureType.BOSS_RIGHT && userDataB==FixtureType.GROUND){
            if (contact.getFixtureA().getBody().getUserData() instanceof FalseKnight c) {
                if (c==falseKnight) {
                    falseKnight.getSurroundSensors().rightSensor++;
                }
            }
        } else if(userDataA== FixtureType.GROUND && userDataB==FixtureType.BOSS_RIGHT){
            if (contact.getFixtureB().getBody().getUserData() instanceof FalseKnight c) {
                if (c==falseKnight) {
                    falseKnight.getSurroundSensors().rightSensor++;
                }
            }
        }

        //Bottom Sensor
        if (userDataA== FixtureType.BOSS_BOTTOM && userDataB==FixtureType.GROUND){
            if (contact.getFixtureA().getBody().getUserData() instanceof FalseKnight c) {
                if (c==falseKnight) {
                    falseKnight.getSurroundSensors().bottomSensor++;
                }
            }
        } else if(userDataA== FixtureType.GROUND && userDataB==FixtureType.BOSS_BOTTOM){
            if (contact.getFixtureB().getBody().getUserData() instanceof FalseKnight c) {
                if (c==falseKnight) {
                    falseKnight.getSurroundSensors().bottomSensor++;
                }
            }
        }

        //Radar Sensor
        if (userDataA== FixtureType.BOSS_RADAR && userDataB==FixtureType.KNIGHT){
            if (contact.getFixtureA().getBody().getUserData() instanceof FalseKnight c) {
                if (c==falseKnight) {
                    falseKnight.getSurroundSensors().radarSensor++;
                }
            }
        } else if(userDataA== FixtureType.KNIGHT && userDataB==FixtureType.BOSS_RADAR){
            if (contact.getFixtureB().getBody().getUserData() instanceof FalseKnight c) {
                if (c==falseKnight) {
                    falseKnight.getSurroundSensors().radarSensor++;
                }
            }
        }

        //Contact between Knight and FalseKnight
        if (userDataA== FixtureType.BOSS && userDataB==FixtureType.KNIGHT){
            if (contact.getFixtureA().getBody().getUserData() instanceof FalseKnight c) {
                if (c==falseKnight) {
                    if (falseKnight.b2Body.getPosition().x>falseKnight.getKnight().b2Body.getPosition().x && !falseKnight.getKnight().isInvincible()){
                        falseKnight.getKnight().b2Body.applyLinearImpulse(new Vector2(-8f,2f),falseKnight.getKnight().b2Body.getWorldCenter(),true);
                    } else if(falseKnight.b2Body.getPosition().x<falseKnight.getKnight().b2Body.getPosition().x && !falseKnight.getKnight().isInvincible()){
                        falseKnight.getKnight().b2Body.applyLinearImpulse(new Vector2(+8f,2f),falseKnight.getKnight().b2Body.getWorldCenter(),true);
                    }
                    falseKnight.getKnight().takeDamage(1);
                }
            }
        } else if(userDataA== FixtureType.KNIGHT && userDataB==FixtureType.BOSS){
            if (contact.getFixtureB().getBody().getUserData() instanceof FalseKnight c) {
                if (c==falseKnight) {
                    if (falseKnight.b2Body.getPosition().x>falseKnight.getKnight().b2Body.getPosition().x && !falseKnight.getKnight().isInvincible()){
                        falseKnight.getKnight().b2Body.applyLinearImpulse(new Vector2(-8f,2f),falseKnight.getKnight().b2Body.getWorldCenter(),true);
                    } else if(falseKnight.b2Body.getPosition().x<falseKnight.getKnight().b2Body.getPosition().x && !falseKnight.getKnight().isInvincible()){
                        falseKnight.getKnight().b2Body.applyLinearImpulse(new Vector2(+8f,2f),falseKnight.getKnight().b2Body.getWorldCenter(),true);
                    }
                    falseKnight.getKnight().takeDamage(1);
                }
            }
        }

        //Contact between Nail and FalseKnight
        if (userDataA== FixtureType.BOSS && userDataB==FixtureType.NAIL){
            if (contact.getFixtureA().getBody().getUserData() instanceof FalseKnight c) {
                if (c==falseKnight) {
                    AudioManager.getInstance().playSFX(GameAssetManager.enemyDamage);
                    falseKnight.takeDamage(5);
                    if (falseKnight.getKnight().hasCharm(CharmType.SOUL_CATCHER)){
                        falseKnight.getKnight().increaseSoul(Knight.BASE_SOUL_INCREASE*2);
                    } else {
                        falseKnight.getKnight().increaseSoul(Knight.BASE_SOUL_INCREASE);
                    }
                    AudioManager.getInstance().playSFX(GameAssetManager.soulGain);
                }
            }
        } else if(userDataA== FixtureType.NAIL && userDataB==FixtureType.BOSS){
            if (contact.getFixtureB().getBody().getUserData() instanceof FalseKnight c) {
                if (c==falseKnight) {
                    AudioManager.getInstance().playSFX(GameAssetManager.enemyDamage);
                    falseKnight.takeDamage(5);
                    if (falseKnight.getKnight().hasCharm(CharmType.SOUL_CATCHER)){
                        falseKnight.getKnight().increaseSoul(Knight.BASE_SOUL_INCREASE*2);
                    } else {
                        falseKnight.getKnight().increaseSoul(Knight.BASE_SOUL_INCREASE);
                    }
                    AudioManager.getInstance().playSFX(GameAssetManager.soulGain);
                }
            }
        }

        //Contact between Mace and knight
        if (userDataA== FixtureType.KNIGHT && userDataB==FixtureType.MACE){
            falseKnight.getKnight().takeDamage(1);
        } else if(userDataA== FixtureType.MACE && userDataB==FixtureType.KNIGHT){
            falseKnight.getKnight().takeDamage(1);
        }

        //Contact between Shockwave and knight
        if (userDataA== FixtureType.KNIGHT && userDataB==FixtureType.SHOCKWAVE){
            falseKnight.getKnight().takeDamage(2);
        } else if(userDataA== FixtureType.SHOCKWAVE && userDataB==FixtureType.KNIGHT){
            falseKnight.getKnight().takeDamage(2);
        }

        //Knight entered boss arena
        if (userDataA== FixtureType.KNIGHT && userDataB==FixtureType.BOSS_ARENA_RADAR){
            falseKnight.createBarriers();
        } else if(userDataA== FixtureType.BOSS_ARENA_RADAR && userDataB==FixtureType.KNIGHT){
            falseKnight.createBarriers();
        }
    }

    @Override
    public void endContact(Contact contact) {
        Object userDataA = contact.getFixtureA().getUserData();
        Object userDataB = contact.getFixtureB().getUserData();

        //Left Sensor
        if (userDataA== FixtureType.BOSS_LEFT && userDataB==FixtureType.GROUND){
            if (contact.getFixtureA().getBody().getUserData() instanceof FalseKnight c) {
                if (c==falseKnight) {
                    falseKnight.getSurroundSensors().leftSensor--;
                }
            }
        } else if(userDataA== FixtureType.GROUND && userDataB==FixtureType.BOSS_LEFT){
            if (contact.getFixtureB().getBody().getUserData() instanceof FalseKnight c) {
                if (c==falseKnight) {
                    falseKnight.getSurroundSensors().leftSensor--;
                }
            }
        }

        //Right Sensor
        if (userDataA== FixtureType.BOSS_RIGHT && userDataB==FixtureType.GROUND){
            if (contact.getFixtureA().getBody().getUserData() instanceof FalseKnight c) {
                if (c==falseKnight) {
                    falseKnight.getSurroundSensors().rightSensor--;
                }
            }
        } else if(userDataA== FixtureType.GROUND && userDataB==FixtureType.BOSS_RIGHT){
            if (contact.getFixtureB().getBody().getUserData() instanceof FalseKnight c) {
                if (c==falseKnight) {
                    falseKnight.getSurroundSensors().rightSensor--;
                }
            }
        }

        //Bottom Sensor
        if (userDataA== FixtureType.BOSS_BOTTOM && userDataB==FixtureType.GROUND){
            if (contact.getFixtureA().getBody().getUserData() instanceof FalseKnight c) {
                if (c==falseKnight) {
                    falseKnight.getSurroundSensors().bottomSensor--;
                }
            }
        } else if(userDataA== FixtureType.GROUND && userDataB==FixtureType.BOSS_BOTTOM){
            if (contact.getFixtureB().getBody().getUserData() instanceof FalseKnight c) {
                if (c==falseKnight) {
                    falseKnight.getSurroundSensors().bottomSensor--;
                }
            }
        }

        //Radar Sensor
        if (userDataA== FixtureType.BOSS_RADAR && userDataB==FixtureType.KNIGHT){
            if (contact.getFixtureA().getBody().getUserData() instanceof FalseKnight c) {
                if (c==falseKnight) {
                    falseKnight.getSurroundSensors().radarSensor--;
                }
            }
        } else if(userDataA== FixtureType.KNIGHT && userDataB==FixtureType.BOSS_RADAR){
            if (contact.getFixtureB().getBody().getUserData() instanceof FalseKnight c) {
                if (c==falseKnight) {
                    falseKnight.getSurroundSensors().radarSensor--;
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
