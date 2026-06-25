package com.HallowKnight.View;

import com.HallowKnight.Controller.ContactController;
import com.HallowKnight.Controller.GameController;
import com.HallowKnight.HallowKnight;
import com.HallowKnight.Model.Knight;
import com.HallowKnight.Model.Map.MapObjectInitializer;
import com.HallowKnight.View.Modals.HUD;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen extends MenuScreen{
    GameController controller;
    HUD hud;

    World world;
    Box2DDebugRenderer b2DebugRenderer;
    OrthographicCamera camera;
    Knight knight;
    private TmxMapLoader mapLoader;
    private Viewport gameViewport;
    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;

    private MapObjectInitializer mapObjectInitializer;
    public GameScreen(HallowKnight game) {
        super(game);

        Box2D.init();
        world=new World(new Vector2(0,-10),true);
        b2DebugRenderer=new Box2DDebugRenderer();
        camera=new OrthographicCamera(viewport.getScreenWidth(),viewport.getScreenHeight());
        gameViewport=new FitViewport(1280/HallowKnight.PPM
            ,960/HallowKnight.PPM,camera);
        knight= new Knight(world);
        world.setContactListener(new ContactController(knight));
        hud=new HUD();
        mainStack.add(hud);
        controller=new GameController(knight,hud);
        //stage.setViewport(gameViewport);

        camera.update();
        gameViewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        mapLoader=new TmxMapLoader();
        map=mapLoader.load("map1/map1.tmx");
        mapRenderer=new OrthogonalTiledMapRenderer(map,1/HallowKnight.PPM);

        mapObjectInitializer=new MapObjectInitializer(world,map);
        mapObjectInitializer.InitializeGrounds();
        mapObjectInitializer.InitializeFloatingPlatforms();
        mapObjectInitializer.InitializeDeadlyBoxes();
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.3f,0.3f,0.3f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        controller.update(delta);

        // Update viewport
        gameViewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Update camera
        camera.position.x=knight.b2Body.getPosition().x;
        camera.position.y=knight.b2Body.getPosition().y;
        camera.update();

        // Set the renderer's view
        mapRenderer.setView(camera);
        mapRenderer.render();

        //rendering player
        game.getBatch().setProjectionMatrix(camera.combined);
        knight.update(delta);
        game.getBatch().begin();
        knight.draw(game.getBatch());
        game.getBatch().end();

        // Render Box2D debug
        b2DebugRenderer.render(world, camera.combined);

        world.step(1/60f,6,2);
        super.render(delta);
    }
}
