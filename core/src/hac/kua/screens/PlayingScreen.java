package hac.kua.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.decals.CameraGroupStrategy;
import com.badlogic.gdx.graphics.g3d.decals.DecalBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.bitfire.postprocessing.PostProcessor;
import com.bitfire.utils.ShaderLoader;
import hac.kua.GameMap;
import hac.kua.hackable.Hackable;
import hac.kua.hackable.Hackable_Manager;
import hac.kua.hackable.MovingThing;
import hac.kua.desktop.CodeEditor;
import hac.kua.utils.Core;


/**
 * Created by kevin on 1/21/2017.
 * Main playing screen.
 */

public class PlayingScreen implements Screen{

    private SpriteBatch batch;

    private Viewport viewport;

    private OrthographicCamera camera;

    private PerspectiveCamera camera3D;

    private PostProcessor postProcessor;

    public static Hackable hackable1;
    public static Hackable hackable2;
    public GameMap gameMap;


    @Override
    public void show() {

        Core.setupFont();
        Core.setupAssets();
        Core.setupStage();

        camera = new OrthographicCamera();
        viewport = new FillViewport(640,480,camera);
        batch = new SpriteBatch();

        hackable1 = new MovingThing();
        hackable2 = new MovingThing();
        hackable1.interact(hackable1);
        hackable2.interact(hackable1);

        //Adding to global hackables list
        Hackable_Manager.add("Hack1", hackable1);
        //Hackable_Manager.add("Hack2", hackable2);

        camera3D = new PerspectiveCamera(80, 640, 480);
        camera3D.position.set(0,0,-5);
        camera3D.near = 1;
        camera3D.far = 30000;
        camera3D.position.set(0, 0, 128);

        gameMap = new GameMap();

        //dBatch = new DecalBatch(new CameraGroupStrategy(camera3D));
        CodeEditor editor = new CodeEditor(hackable1);
    }
    @Override
    public void render(float delta) {

        //Updates / input logic
        handleInput();
        Core.assets.update();


        //Graphics
        Gdx.gl.glClearColor(0.5f,0.45f,0.6f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        Gdx.gl.glEnable(GL20.GL_DEPTH_TEST);

        postProcessor.capture();
        postProcessor.setClearColor(0.1f, 0.2f, 0.1f, 0.9f);

        //2D drawing/////////
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        //Draw hackables
        for(ObjectMap.Entry<String, Hackable> entry : Hackable_Manager.hackables)
        {
            entry.value.draw(batch);
        }

        gameMap.draw(batch);

        batch.end();
        /////////////////////

        //3D Drawing
        camera3D.update();

        //Stage & HUD Drawing///////
        Stage stage = Core.stage;
        Gdx.input.setInputProcessor(stage);

        stage.draw();
        stage.act(delta);

        stage.getBatch().begin();
        Core.font.draw(stage.getBatch(), "FPS: " + Gdx.graphics.getFramesPerSecond(), 100,50);
        stage.getBatch().end();
        ////////////////////////
        //postProcessor.render();
    }

    public void handleInput()
    {
        if(Gdx.input.justTouched())
        {
            hackable1.interact();
        }


    }



    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        viewport.apply();
        camera.position.set(camera.viewportWidth / 2,camera.viewportHeight / 2, 0);
        Stage stage = Core.stage;
        stage.getViewport().update(width, height);
        stage.getCamera().position.set(stage.getCamera().viewportWidth / 2, stage.getCamera().viewportHeight / 2, 0);

        //Post Processing
        ShaderLoader.BasePath = "assets/shaders/";
        postProcessor = new PostProcessor(true, true,true);

       // int effectsff = Effect.TweakContrast.v | Effect.PhosphorVibrance.v | Effect.Scanlines.v | Effect.Tint.v;
        //CrtMonitor crt = new CrtMonitor(width, height, true, true, CrtScreen.RgbMode.ChromaticAberrations, effectsff );
        //postProcessor.addEffect(crt);
    }

    @Override
    public void dispose() {

    }


    //UNUSED////////////
    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }


}
