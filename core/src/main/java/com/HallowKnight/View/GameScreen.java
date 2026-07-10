package com.HallowKnight.View;

import com.HallowKnight.Controller.ContactController;
import com.HallowKnight.Controller.GameController;
import com.HallowKnight.Controller.Managers.AudioManager;
import com.HallowKnight.Controller.Managers.GameAssetManager;
import com.HallowKnight.HallowKnight;
import com.HallowKnight.Model.GameCamera;
import com.HallowKnight.Model.GameState;
import com.HallowKnight.Model.Knight.Knight;
import com.HallowKnight.Model.Map.MapObjectInitializer;
import com.HallowKnight.View.Modals.HUD;
import com.HallowKnight.View.Modals.Inventory;
import com.HallowKnight.View.Modals.PauseMenu;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
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

    private static GameScreen instance;
    private boolean paused;
    private PauseMenu pauseMenu;
    private Inventory inventory;
    private GameState gameState;

    public static GameScreen getInstance(HallowKnight game,GameState gameState){
        if (instance==null){
            instance=new GameScreen(game,gameState);
        }
        return instance;
    }

    public static GameScreen getInstance(){
        return instance;
    }

    public static void resetGameScreen(){
        if (instance!=null) {
            instance.dispose();
            instance = null;
        }
    }

    GameController controller;
    HUD hud;

    World world;
    Box2DDebugRenderer b2DebugRenderer;
    GameCamera camera;
    Knight knight;
    private TmxMapLoader mapLoader;
    private Viewport gameViewport;
    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;

    private MapObjectInitializer mapObjectInitializer;
    private GameScreen(HallowKnight game, GameState gameState) {
        super(game);
        this.gameState = gameState;

        Box2D.init();
        world=new World(new Vector2(0,-10),true);
        world.setContactListener(new ContactController(knight));
        b2DebugRenderer=new Box2DDebugRenderer();
        camera=new GameCamera(viewport.getScreenWidth(),viewport.getScreenHeight());
        gameViewport=new FitViewport(1280/HallowKnight.PPM
            ,960/HallowKnight.PPM,camera);

        camera.update();
        gameViewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        mapLoader=new TmxMapLoader();
        map=mapLoader.load("map1/map1.tmx");
        mapRenderer=new OrthogonalTiledMapRenderer(map,1/HallowKnight.PPM);

        mapObjectInitializer=new MapObjectInitializer(world,map);
        mapObjectInitializer.InitializeGrounds();
        mapObjectInitializer.InitializeFloatingPlatforms();
        mapObjectInitializer.InitializeDeadlyBoxes();
        knight=mapObjectInitializer.initializeKnight(this,gameState);
        camera.setKnight(knight);
        hud=new HUD();
        mainStack.add(hud);
        controller=new GameController(world,knight,hud,this,gameState.time);
        mapObjectInitializer.initializeHuskHornheads(controller);
        mapObjectInitializer.initializeCrystallizeds(controller);
        mapObjectInitializer.initializeMosquitoes(controller);
        mapObjectInitializer.initializeCrystalCrawlers(controller);
        mapObjectInitializer.initializeZote(controller,this);
        mapObjectInitializer.initializeFalseKnight(controller,this);
        mapObjectInitializer.initializeTorch(controller);

        paused=false;
        pauseMenu=new PauseMenu(controller);
        mainStack.add(pauseMenu);
        pauseMenu.setVisible(false);

        inventory=new Inventory(knight);
        mainStack.add(inventory);
        inventory.setVisible(false);
    }

    @Override
    public void show() {
        super.show();
        AudioManager.getInstance().playMusic(GameAssetManager.crystalPeakMusic,true,game.getsettings().getMusicVolume());
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.3f,0.3f,0.3f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        controller.handleInput();
        if (!paused) controller.update(delta);

        // Update viewport
        gameViewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Update camera
        camera.update(delta);

        // Set the renderer's view
        mapRenderer.setView(camera);
        mapRenderer.render();

        //rendering enemies
        controller.renderEnemies();

        //rendering false knight
        controller.renderFalseKnight();

        //rendering player
        game.getBatch().setProjectionMatrix(camera.combined);
        if (!paused) knight.update(delta);
        game.getBatch().begin();
        knight.draw(game.getBatch());
        game.getBatch().end();

        //rendering effects
        controller.renderEffects();

        //rendering barriers
        controller.renderBarriers();

        // Render Box2D debug
        //b2DebugRenderer.render(world, camera.combined);

        if (!paused) {
            world.step(1 / 60f, 6, 2);
            if (instance!=null) {
                controller.processPendingActions();
            }
        }
        super.render(delta);
    }

    public GameController getController(){
        return controller;
    }

    public GameCamera getCamera(){
        return camera;
    }

    public PauseMenu getPauseMenu(){
        return pauseMenu;
    }

    public Inventory getInventory(){
        return inventory;
    }

    public void setPaused(boolean paused){
        this.paused=paused;
    }

    public boolean isPaused(){
        return paused;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
}
