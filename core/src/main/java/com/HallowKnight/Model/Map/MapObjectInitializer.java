package com.HallowKnight.Model.Map;

import com.HallowKnight.Controller.GameController;
import com.HallowKnight.HallowKnight;
import com.HallowKnight.Model.FixtureType;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.PointMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class MapObjectInitializer {
    private World world;
    private TiledMap map;
    public Body b2Body;

    BodyDef b2BodyDefine;
    FixtureDef b2FixtureDefine;

    public MapObjectInitializer(World world, TiledMap map){
        this.world=world;
        this.map=map;
        b2BodyDefine=new BodyDef();
        b2FixtureDefine=new FixtureDef();
    }

    public void InitializeGrounds(){
        PolygonShape shape=new PolygonShape();
        for (MapObject object:map.getLayers().get("floorBoxes").getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect=((RectangleMapObject)object).getRectangle();
            b2BodyDefine.type= BodyDef.BodyType.StaticBody;
            b2BodyDefine.position.set((rect.getX()+rect.getWidth()/2)/ HallowKnight.PPM
                ,(rect.getY()+rect.getHeight()/2)/HallowKnight.PPM);

            b2Body=world.createBody(b2BodyDefine);

            shape.setAsBox((rect.getWidth()/2)/HallowKnight.PPM
                , (rect.getHeight()/2)/HallowKnight.PPM);
            b2FixtureDefine.shape=shape;

            b2Body.createFixture(b2FixtureDefine).setUserData(FixtureType.GROUND);
        }
    }

    public void InitializeFloatingPlatforms(){
        PolygonShape shape=new PolygonShape();
        for (MapObject object:map.getLayers().get("float_platforms_Boxes").getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect=((RectangleMapObject)object).getRectangle();
            b2BodyDefine.type= BodyDef.BodyType.StaticBody;
            b2BodyDefine.position.set((rect.getX()+rect.getWidth()/2)/ HallowKnight.PPM
                ,(rect.getY()+rect.getHeight()/2)/HallowKnight.PPM);

            b2Body=world.createBody(b2BodyDefine);

            shape.setAsBox((rect.getWidth()/2)/HallowKnight.PPM
                , (rect.getHeight()/2)/HallowKnight.PPM);
            b2FixtureDefine.shape=shape;

            b2Body.createFixture(b2FixtureDefine).setUserData(FixtureType.PLATFORM);
        }
    }

    public void InitializeDeadlyBoxes(){
        PolygonShape shape=new PolygonShape();
        for (MapObject object:map.getLayers().get("Deadly_Boxes").getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect=((RectangleMapObject)object).getRectangle();
            b2BodyDefine.type= BodyDef.BodyType.StaticBody;
            b2BodyDefine.position.set((rect.getX()+rect.getWidth()/2)/ HallowKnight.PPM
                ,(rect.getY()+rect.getHeight()/2)/HallowKnight.PPM);

            b2Body=world.createBody(b2BodyDefine);

            shape.setAsBox((rect.getWidth()/2)/HallowKnight.PPM
                , (rect.getHeight()/2)/HallowKnight.PPM);
            b2FixtureDefine.shape=shape;
            //b2FixtureDefine.isSensor=true;

            b2Body.createFixture(b2FixtureDefine).setUserData(FixtureType.DEADLY);
        }
    }

    public void initializeEnemySpawnPoints(GameController gameController){
        for (PointMapObject point:map.getLayers().get("Enemies_Boxes").getObjects().getByType(PointMapObject.class)){
            gameController.getEnemySpawnPositions().add(new Vector2(point.getPoint().x,point.getPoint().y));
        }
        for (PointMapObject point : map.getLayers().get("EnemyStartAndEnd").getObjects().getByType(PointMapObject.class)){
            gameController.getEnemyStartAndEndPositions().add(new Vector2(point.getPoint().x,point.getPoint().y));
        }
    }
}
